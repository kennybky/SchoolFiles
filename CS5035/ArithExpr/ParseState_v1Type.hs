module ParseState_v1Type (ParseState_v1, evalArith) where

import DataDeclrs  
import Tokenizer
import ParseStateClass  

---------------------------------------------------------------------
-- Define  ParseState_v1  to be a pair of [Elmt] lists.
-- Define how  ParseState_v1  implements  Eq  and  ParseState.  
---------------------------------------------------------------------

-- <Declare ParseState_v1 value constructor to be Pair, Pair takes two Elmt List as paramaters>
data ParseState_v1 = Pair [Elmt] [Elmt]


-- Define how to compare two ParseState_v1 objects for equality.
instance Eq ParseState_v1 where 
  Pair list11 list12 == Pair list21 list22 
                             = list11 == list21 && list12 == list22


---------------------------------------------------------------------
-- evalArith is the top-level function. 
-- Because it generates an intermediate ParseState, 
-- it must be defined for each ParseState type.
---------------------------------------------------------------------

-- <Takes in an arithmetic expression as a string, parses and returns a triple which contains
-- the input string, the parses expression and the value of the expression.>
evalArith :: String -> (String, Elmt, Int) 
evalArith string = 
  let parseState = (parse . tokenize $ string) :: ParseState_v1
      (left, window, _) = segment parseState
  in case window of
     [] -> error $ ('"'):string ++ "\" => " ++ show (reverse left)
     [expr] -> (string, expr, evalExpr expr)



-- Define how to perform the ParseState functions on ParseState_v1 objects
instance ParseState ParseState_v1 where
  -- <Your comment. What does this rule do? If Parenthesis surround an expresion 
  -- or element remove the parenthesis>
  applyARule (Pair left (LPar : e : RPar : right)) = Pair left (e : right)
  -- <Your comment. What does this rule do? If the first five elements consist of two Elmt on each side
  -- of an operator, and that operator has higher precedence than the operators on both sides,
  --contruct an Expression.>
  applyARule (Pair left (elmt0 : e1 : op@(Op opfn c _) : e2 : elmt3 : right))
     | higherPrec elmt0 op elmt3 =
                       let newExpr = Expr e1 (Optr opfn c) e2
                       in  Pair left (elmt0 : newExpr : elmt3 : right)
  -- <Your comment. Why is this defined? If the above cases fails this returns the input variable unchanged>
  applyARule parseState = parseState


  -- <This function determines whether the expression should continue to be parsed or not>
  continueParse state
    | Pair _  []  <- state = False -- Empty Exression, stop parsing
    | Pair [] [_] <- state = False --Single Expression, stop parsing 
    | otherwise            = True


  -- <This creates a new state.>
  newParseState tokens = Pair [] tokens


  -- <divides the right list of the pair into two and returns three lists> 
  segment (Pair left right) = 
    let (window, rest) = splitAt 5 right
    in (left, window, rest)


  -- <> 
  showState prefix parseState = 
    let (left, window, rest) = segment parseState
    in prefix ++ (insideString . reverse) left 
       ++ "  << " ++ replace "," " , " (insideString window) ++ " >>  " 
       ++ insideString rest
    where 
      -- <Takes a list, converts it to a string and removes the square brackets > 
      insideString :: Show a => [a] -> String
      insideString = init . tail . show 
    

  -- <Takes an integer and a pair of Elmts and shift Elmts to the left or right
  --depending on the value of the integer>
  slideWindow n (Pair left right) 
    |    n == 0 
      || n < 0 && null left 
      || n > 0 && null right = Pair left right
  slideWindow n (Pair (x:xs) right) 
    | n < 0 = slideWindow (n+1) (Pair xs (x:right))
  slideWindow n (Pair left (x:xs)) 
    | n > 0 = slideWindow (n-1) (Pair (x:left) xs)
