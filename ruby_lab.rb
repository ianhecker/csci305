#!/usr/bin/ruby

###############################################################
#
# CSCI 305 - Ruby Programming Lab
#
# <Ian> <Hecker>
# <ianhecker@yahoo.com>
#
###############################################################


$bigrams = Hash.new # The Bigram data structure
$name = "<Ian> <Hecker>"
$file_name = ARGV[0].to_s


#Title Extraction/Cleanup methods
##################################
def cleanup_title title
	#I'm curious if this is kosher to do with lots of
	#logic statements, I hope it's easy to read <3

	title = remove_garbage title
	title = remove_brackets title
	title = remove_feat_artist title
	title = remove_punctuation title
	title = remove_non_english title

	return title
end

#Child methods of cleanup_title
###############################
def remove_garbage str
		#Below 'literal regexp with corresponding names per capture' idea was found at:
		#https://ruby-doc.org/core-2.2.0/Regexp.html
		/(?<garbage>\w+)\<SEP\>(?<garbage2>\w+)\<SEP\>(?<artist>.*)\<SEP\>(?<title>.*)/ =~ str
		return title
end

def remove_brackets str
		#Sets parts of string to variables
		#Returns only bracket-free section of title
	/(?<bracket_free>[^(\(|\)|\[|\]|\{|\}|\\|\/|\|)]+)(?<extra>.*)/ =~ str
	return bracket_free
end

def remove_feat_artist str
	begin
		if (str =~ /^\s*$/) || (str == nil)
				#Returns non-English song
				#remove_non_english method will skip this
			return "skíp thís söng"
		elsif str.match(/(?<basic_title>.*)(feat\..*)/) == nil
				#If there is NO FEAT., returns string as-is
			return str
		else
				#Set parts of strings to variables,
				#return only title section
			/(?<basic_title>.*)(feat\..*)/ =~ str
			return basic_title
		end
	rescue
			#Returns non-English song
			#remove_non_english method will skip this
			#I had toruble with a corrupted unique_tracks file,
			#and added this as a safety net
		return "skíp thís söng"
	end
end

def remove_punctuation str
		#Search for following punctuation and remove
	str.gsub!(/_|-|:|"|`|\+|=|\*|\?|¿|!|¡|;|\.|&|@|%|#/, '')
	return str
end

def remove_non_english str
		#Search for hit of non: word/space/digit/' character
		#Which is therefore a non-English char at this point
	if str == str.gsub(/[^\w|\s|'|\d]/, '')
		#If str is unchanged by gsub, there are no non-English chars
		str = str.downcase
		return str
	else
		#There exists a non-English char
		#Skip this song title
		return nil
	end
end
#End child methods of cleanup_title
###################################

#Bigrams Methods
##################################
#Creates a bigram of all words from a string
def construct_bigrams str
	words = str.split(' ')

	#below modified solution from https://stackoverflow.com/questions/10034678/how-can-i-delete-one-element-from-an-array-by-value/22465445
	words = words - ['a'] - ['an'] - ['and'] - ['by'] - ['for']- ['from'] - ['in']
	words = words - ['of'] - ['on'] - ['or'] - ['out'] - ['the'] - ['to'] - ['with']

	i = 0
	array_length = words.length

  #Check if title is longer than one word
  if array_length > 1

			#Loop until i is second to last word in array
			#We do not want to test very last word as last
			#word's next word will be nil; end of array
  	until i > (array_length - 2) do

  		#add new bigram values with nested hashes
  		hash = words[i]
  		nested_hash = words[i+1]
				#Check if bigrams' hash exists
  		if $bigrams.has_key?(hash)
					#Check if bigrams' nested hash exists
        if $bigrams[hash].has_key?(nested_hash)
          #If both hashes already exist
					#increment count
          $bigrams[hash][nested_hash] += 1
        else
          #no nested_hash exists
          #Create new nested_hash in existing hash
          $bigrams[hash][nested_hash] = 1
        end

      else
        #Create new hash and new nested hash
        $bigrams[hash] = {
          nested_hash => 1
        }
      end
      i+=1
    end
  end
end


def mcw word
	if $bigrams.has_key?(word)
		#below "to_a"
		#returns 2D array with unsorted subarrays
		#formatted as [[hash, #],[hash, #],[hash, #], ..etc]
		words_and_frequencies = $bigrams[word].to_a
			#'ease of reading' variables
		num_pos = 1
		word_pos = 0

		i = 0
		highest_frequency = 0
		array_length = words_and_frequencies.length
			#Loop until array list of words & frequencies is empty
		until i > (array_length - 1) do
				#If frequency is greater than recorded highest
			if words_and_frequencies[i][num_pos] > highest_frequency
					#Store new most common word & new highest frequency
				most_common_word = words_and_frequencies[i][word_pos]
				highest_frequency = words_and_frequencies[i][num_pos]

			elsif words_and_frequencies[i][num_pos] == highest_frequency
				#Else if frequency current and the highest recorded match, perform
				#random selection of either choices
				most_common_word = random_selection(most_common_word, words_and_frequencies[i][word_pos])
			end
			i+=1
		end
		#No following word exists for inputted word
	else
		return nil
	end
	return most_common_word
end


def random_selection str1, str2
	#When max is an Integer, rand returns a random integer
	#greater than or equal to zero and less than (max).
	#Max in this case is "10"
	random_num = Random.new

	#50/50 chance of either word
	if random_num.rand(10) > 4
		return str1
	else
		return str2
	end
end

#Creates new title based on succesive words
def create_title word
	i = 0
	new_title = word
	current_word = word
	next_word = ""

	#Until title is 20 words long, do:
	until i >= 19 do
		next_word = mcw(current_word)

		if next_word == nil
				#If word doesnt have following word, return
			return new_title

		elsif new_title =~ /#{next_word}/
				#If title string already contains a word, return
			return new_title

		else
				#Else add word to new title string
			new_title.concat(" ", next_word)
			current_word = next_word
		end
		i+=1
	end
	return new_title
end
#End Bigrams methods
####################


# function to process each line of a file and extract the song titles
def process_file(file_name)
	puts "Processing File.... "

	begin
		IO.foreach(file_name, encoding: "utf-8") do |line|
			# do something for each line
			title = cleanup_title(line)
				#if return isnt nil, attempt to parse words
				#and add to bigrams data structure
			if title != nil
				construct_bigrams(title)
			end
		end
		puts "Finished. Bigram model built.\n"
	rescue
			#This rescue hid so many errors from me
			#Commented it out to figure out problems ALOT!
		STDERR.puts "Could not open file"
	  exit 4
	end
end

# Executes the program
def main_loop()
	puts "CSCI 305 Ruby Lab submitted by #{$name}"

	if ARGV.length < 1
		puts "You must specify the file name as the argument."
		exit 4
	end

	# process the file
	process_file(ARGV[0])
	# Get user input
	input = nil
		#While user does not enter 'q' loop
	while(input != 'q') do
		print "Enter a word [Enter 'q' to quit]: "
			#$stdin gets user input vs print out of program
			#chomp removes endline char
		input = $stdin.gets.chomp
			#If entered str isnt 'q', return custom title
		if input != 'q'
			puts create_title input
		end
	end
end

if __FILE__==$0
	main_loop()
end
