(***************************************************************
*
* CSCI 305 - ML Programming Lab
*
* <Ian> <Hecker>
* <ianhecker@yahoo.com>
*
***************************************************************)

(* Define your data type and functions here *)

fun f [] = [] (* a *)
  | f (x::xs) = (x + 1) :: (f xs); (* b *)

(*custom datatype for sets*)
datatype 'e set = Set of 'e * 'e set
| Empty;

(*Looks recusively for an element inside a set;
  Progressively looks through a list one element smaller each time*)
fun isMember e Empty = false
|   isMember e (Set(left, right)) =
      if e = left then true
      else isMember e right;

(*Checks to see if element 'e' is in a list;
modified from recursion example given for this assignment*)
fun inList e [] = false
|   inList e (x::xs) =
      if e = x then true
      else inList e xs;

(*Helper function for list2Set;
recursively creates list from 2-inf items*)
fun makeList2 (x::xs) s =
  let
    val s = Set(x, s)
  in
    if null xs then s
    else makeList2 xs s
  end;

(*Helper function for list2Set;
makes lists of one item long at most
and initializes set, then calls makeList2*)
fun makeList (x::xs) =
  let
    val s = Set(x, Empty)
  in
    if null xs then s
    else makeList2 xs s
  end;

(*Reverses list using list library function*)
fun reverse list =
    rev(list);

(*Below removeDup method taken and modified from:
https://stackoverflow.com/questions/21077272/remove-duplicates-from-a-list-in-sml*)
fun removeDup [] = []
  | removeDup (x::xs) =
      x::removeDup(List.filter (fn y => y <> x) xs);

(*Checks for empty list and returns Empty
Checks for list > 2 elements and refers to listMaker*)
fun list2Set [] = Empty
|   list2Set list =  makeList(reverse(removeDup(list)));

(*Converts a set to a list
empty list must be provided on method call*)
fun set2List Empty list = list
|   set2List (Set(left, right)) list =
      set2List right (left::list);


(*returns all unique elements in both sets*)
fun union Empty s2 = s2
|   union (Set(left, right)) s2 =
      if isMember left s2 then union right s2
      else union right (Set(left, s2));

(*compares two lists to see if they possess same elements
Then concatenates found value to head of list*)
(*Below function found and modified from:
https://mathcs.clarku.edu/~djoyce/cs170/mlexample3.html*)
fun intersectHelp [] l2 = []
|   intersectHelp (x::xs) l2 =
      if inList x l2 then x::intersectHelp xs l2
      else intersectHelp xs l2;

(*returns the element occuring in both sets*)
(*converts sets to lists for easier comparison
and concatenation functions*)
fun intersect s1 s2 =
      list2Set (intersectHelp (set2List s1 []) (set2List s2 []));

(**************************************************************)

(* Simple function to stringify the contents of a Set of characters *)
fun stringifyCharSet Empty = ""
  | stringifyCharSet (Set(y, ys)) = Char.toString(y) ^ " " ^ stringifyCharSet(ys);

(* Simple function to stringify the contents of a Set of ints *)
fun stringifyIntSet Empty = ""
  | stringifyIntSet (Set(w, ws)) = Int.toString(w) ^ " " ^ stringifyIntSet(ws);

(* Simple function to stringify the contents of a Set of strings *)
fun stringifyStringSet Empty = ""
  | stringifyStringSet (Set(z, zs)) = z ^ " " ^ stringifyStringSet(zs);

(* Simple function that prints a set of integers *)
fun print_int x = print ("{ " ^ stringifyIntSet(x) ^ "}\n");

(* Simple function that prints a set of strings *)
fun print_str x = print ("{ " ^ stringifyStringSet(x) ^ "}\n");

(* Simple function that prints a set of characters *)
fun print_chr x = print ("{ " ^ stringifyCharSet(x) ^ "}\n");


list2Set [1, 3, 2];
list2Set [#"a", #"b", #"c"];
list2Set [];
list2Set [6, 2, 2];
list2Set ["x", "y", "z", "x"];

(* Question 1 *)
f [3, 1, 4, 1, 5, 9];

(* Question 5 *)
val quest5 = isMember "one" (list2Set ["1", "2", "3", "4"]);
print ("\nQuestion 5: " ^ Bool.toString(quest5) ^ "\n");

(* Question 7 *)
val quest7 = list2Set ["it", "was", "the", "best", "of", "times", "it", "was", "the", "worst", "of", "times"];
print "\nQuestion 7: ";
print_str quest7;
print "\n";

(* Question 9 *)
print "\nQuestion 9: ";
print_str (union (list2Set ["green", "eggs", "and"]) (list2Set ["ham"]));

(* Question 10 *)
print "\nQuestion 10: ";
print_str (intersect (list2Set ["stewed", "tomatoes", "and", "macaroni"]) (list2Set ["macaroni", "and", "cheese"]));
