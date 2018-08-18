module ParseState_v2Type (ParseState_v2, evalArith) where

import DataDeclrs  
import Tokenizer
import ParseStateClass  

---------------------------------------------------------------------
-- Define  ParseState_v2  to be <your choice>.
-- As currently written it is the same as ParseState_V1.
-- Define how  ParseState_v2  implements  Eq  and  ParseState.  
---------------------------------------------------------------------

-- <Your comment>
data ParseState_v2 = Pair [Elmt] [Elmt]


-- Define how to compare two ParseState_v2 objects for equality.
instance Eq ParseState_v2 where 
  Pair list11 list12 == Pair list21 list22 
                             = list11 == list21 && list12 == list22


---------------------------------------------------------------------
-- evalArith is the top-level function. 
-- Because it generates an intermediate ParseState, 
-- it must be defined for each ParseState type.
---------------------------------------------------------------------

-- <Your comment>
evalArith :: String -> (String, Elmt, Int) 
evalArith string = 
  let parseState = (parse . tokenize $ string) :: ParseState_v2
      (left, window, _) = segment parseState
  in case window of
     [] -> error $ ('"'):string ++ "\" => " ++ show (reverse left)
     [expr] -> (string, expr, evalExpr expr)



-- Define how to perform the ParseState functions on ParseState_v2 objects
instance ParseState ParseState_v2 where
  -- <Your comment. What does this rule do?>
  applyARule (Pair left (LPar : e : RPar : right)) = Pair left (e : right)
  -- <Your comment. What does this rule do?>
  applyARule (Pair left (elmt0 : e1 : op@(Op opfn c _) : e2 : elmt3 : right))
     | checkHigherPrec elmt0 op elmt3 =
                       let newExpr = Expr e1 (Optr opfn c) e2
                       in  Pair left (elmt0 : newExpr : elmt3 : right)
  -- <Your comment. Why is this defined?>
  applyARule parseState = parseState


  -- <Your comment>
  continueParse state
    | Pair _  []  <- state = False 
    | Pair [] [_] <- state = False 
    | otherwise            = True


  -- <Your comment>
  newParseState tokens = Pair [] tokens


  -- <Your comment> 
  segment (Pair left right) = 
    let (window, rest) = splitAt 5 right
    in (left, window, rest)


  -- <Your comment> 
  showState prefix parseState = 
    let (left, window, rest) = segment parseState
    in prefix ++ (insideString . reverse) left 
       ++ "  << " ++ replace "," " , " (insideString window) ++ " >>  " 
       ++ insideString rest
    where 
      -- <Your comment> 
      insideString :: Show a => [a] -> String
      insideString = init . tail . show 
    

  -- <Your comment>
  slideWindow n (Pair left right) 
    |    n == 0 
      || n < 0 && null left 
      || n > 0 && null right = Pair left right
  slideWindow n (Pair (x:xs) right) 
    | n < 0 = slideWindow (n+1) (Pair xs (x:right))
  slideWindow n (Pair left (x:xs)) 
    | n > 0 = slideWindow (n-1) (Pair (x:left) xs)




checkHigherPrec leftOp op rightOp 
  |op `elem` [Op (^) '^' 3, Op (-) '-' 1] = higherPrecRightAssoc leftOp op rightOp
  |otherwise = higherPrec leftOp op rightOp

higherPrecRightAssoc :: Elmt -> Elmt -> Elmt -> Bool  
higherPrecRightAssoc leftOp op rightOp =
            higherPrecThanLeftAlt leftOp op && higherPrecThanRightAlt op rightOp


-- <Your comment>
higherPrecThanLeftAlt :: Elmt -> Elmt -> Bool
higherPrecThanLeftAlt LPar        (Op _ _ _)  = True
higherPrecThanLeftAlt (Op _ _ p0) (Op _ _ p1) = p0 <= p1
higherPrecThanLeftAlt _           _           = False


-- <Your comment>
higherPrecThanRightAlt :: Elmt -> Elmt -> Bool
higherPrecThanRightAlt (Op _ _ _)  RPar        = True
higherPrecThanRightAlt (Op _ _ p1) (Op _ _ p2) = p1 > p2
higherPrecThanRightAlt _           _           = False
