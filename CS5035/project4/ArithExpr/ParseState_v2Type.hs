module ParseState_v2Type (ParseState_v2, evalArith) where

import DataDeclrs (Elmt, evalExpr)
import Tokenizer (tokenize)
import ParseStateClass (ParseState(..), replace) 

---------------------------------------------------------------------
-- Define  ParseState_v1  to be an [Elmt] list with an index for the
-- start of the window. Define how  ParseState_v2  implements  ParseState.  
---------------------------------------------------------------------

-- <This defines a type of parseState_v2 as an index and a list, that implement show and Eq>
data ParseState_v2 = IndexAndList Int [Elmt] deriving (Eq, Show)

---------------------------------------------------------------------
-- evalArith is the top-level function. 
-- Because it generates an intermediate ParseState, 
-- it must be defined for each ParseState type.
---------------------------------------------------------------------

-- <This takes an arithmetic expression as a string, evalutes it, and returns the original expression 
--along with its evaluated result, and the value of the expression>
evalArith :: String -> (String, Elmt, Int) 
evalArith string = 
  let parseState = (parse . tokenize $ string) :: ParseState_v2
      (left, window, _) = segment parseState
  in case window of
     []     -> error $ show string ++ " => " ++ (replace "," ", " $ show left)
     [expr] -> (string, expr, evalExpr expr)


-- Define how to perform the ParseState functions on ParseState_v2 objects
instance ParseState ParseState_v2 where

  -- <This creates a new parse state>
  initialParseState tokens = IndexAndList 0 tokens


  -- <This takes a parse state, splits the list into three lists, and returns the  split lists as a triple> 
  segment (IndexAndList index list) = 
    let (window, rest) = splitAt 5 . drop index $ list
    in (take index list, window, rest)


  -- <This increments the index bases on the integer parameter and index value>
  slideWindow n (IndexAndList index list) 
    |    n == 0 
      || n < 0 && index == 0 
      || n > 0 && index > length list = IndexAndList index list
  slideWindow n (IndexAndList index list) 
    | n < 0 = IndexAndList (max 0 (index + n)) list -- n < 0
  slideWindow n (IndexAndList index list) 
    | n > 0 = IndexAndList (min (length list) (index + n)) list


  -- <this moves values from left to the right or vice versa depending on the integer paramter>
  unSegment  (left, window, right) = IndexAndList (length left) (left ++ window ++ right)

