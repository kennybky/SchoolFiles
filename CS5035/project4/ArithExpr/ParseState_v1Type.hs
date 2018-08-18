module ParseState_v1Type (ParseState_v1, evalArith) where

import DataDeclrs (Elmt, evalExpr)
import Tokenizer (tokenize)
import ParseStateClass (ParseState(..), replace) 

---------------------------------------------------------------------
-- Define  ParseState_v1  to be a PairOfLists of [Elmt] lists.
-- Define how  ParseState_v1  implements  ParseState.  
---------------------------------------------------------------------

-- <This defines a type of parseState_v1 as a pair of Elmt lists, that implement show and Eq>
data ParseState_v1 = PairOfLists [Elmt] [Elmt] deriving (Eq, Show)

---------------------------------------------------------------------
-- evalArith is the top-level function. 
-- Because it generates an intermediate ParseState, 
-- it must be defined for each ParseState type.
---------------------------------------------------------------------

-- <This takes an arithmetic expression as a string, evalutes it, and returns the original expression 
--along with its evaluated result, and the value of the expression>
evalArith :: String -> (String, Elmt, Int) 
evalArith string = 
  let parseState = (parse . tokenize $ string) :: ParseState_v1
      (left, window, _) = segment parseState
  in case window of
     []     -> error $ show string ++ " => " ++ (replace "," ", " $ show left)
     [expr] -> (string, expr, evalExpr expr)


-- Define how to perform the ParseState functions on ParseState_v1 objects
instance ParseState ParseState_v1 where

  -- <This creates a new parse state>
  initialParseState tokens = PairOfLists [] tokens


  -- <This takes a parse state, splits the right list into two lists, and returns the left list and the two split lists as a triple> 
  segment (PairOfLists left right) = 
    let (window, rest) = splitAt 5 right
    in (reverse left, window, rest)


  -- <this moves values from left to the right or vice versa depending on the integer paramter and list states>
  slideWindow n (PairOfLists left right) 
    |    n == 0 
      || n < 0 && null left 
      || n > 0 && null right = PairOfLists left right
  slideWindow n (PairOfLists (x:xs) right) 
    | n < 0 = slideWindow (n+1) (PairOfLists xs (x:right))
  slideWindow n (PairOfLists left (x:xs)) 
    | n > 0 = slideWindow (n-1) (PairOfLists (x:left) xs)


  -- <This takes a triple of lists, combimes the last two together into one list, reverses the first, and returns a Parsestate with the lists>
  unSegment  (left, window, right) = PairOfLists (reverse left) (window ++ right)

