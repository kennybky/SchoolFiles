module ParseStateClass (ParseState (..), replace) where

import DataDeclrs (Elmt(..), Optr (Optr)) 

import Debug.Trace


-----------------------------------------------------------------------
-- The ParseState TypeClass. A TypeClass is like an Interface in Java.
-- Define a  ParseState  as supporting these functions. Also, require 
-- that every  ParseState  satisfies (i.e., implements)  Eq.
-----------------------------------------------------------------------

-- These functions will depend on the specific implementation
-- of a ParseState.
class Eq parseState => ParseState parseState where
  initialParseState :: [Elmt] -> parseState
  segment :: parseState -> ([Elmt], [Elmt], [Elmt])
  slideWindow :: Int -> parseState -> parseState
  unSegment :: ([Elmt], [Elmt], [Elmt]) -> parseState


  -- <This takes a parseState and checks if the parsing should continue>
  continueParse :: parseState -> Bool
  continueParse pState =
    case segment pState of
    (_,  [],  []) -> False   -- Parse failed. Stop.
    ([], [_], []) -> False   -- Parse succeeded. Stop.
    otherwise     -> True    -- Parse not finished. Continue.


  -- Parse  is implementation-independent and can be used by 
  -- all  ParseState  instances.
  -- <This takes a list of elements and attempts to parse it into an expression>
  parse :: [Elmt] -> parseState  
  parse tokens = 
    let initialPState = initialParseState tokens
    in perhapsTrace ("     " ++ showParseState initialPState) $
       while initialPState
             continueParse
             parseAStep


  showParseState :: parseState -> String
  showParseState pState = 
    let (left, window, right) = segment pState   
    in replace "," ", " $ show left ++ "   " ++ show window ++ "    " ++ show right


-- -----------------------------------------------------------------------
-- -- Utility functions used by ParseState functions.
-----------------------------------------------------------------------

-- <Your comment>
applyARule :: [Elmt] -> [Elmt]
-- <Your comment. What does this rule do? 
                      --If the first elements are an LPar, an Expr or Nbr and a Rpar in that order, discard the two parenthesis>
applyARule window
  | [LPar, e, RPar] <- take 3 window
  , isOperand e                       = [e] ++ drop 3 window
-- <Your comment. What does this rule do? if the first four elements are an LPar, a minus operator, 
  --an Expr or Nbr and a Rpar in that order, put a 0 in from of the minus operator>
applyARule window
  | [LPar, op@(Op opfn '-' _ _), e, RPar] <- take 4 window 
  , isOperand e                       = [LPar, Nbr 0, op, e, RPar] ++ drop 4 window
-- <Your comment. What does this rule do? if the window consists of an Elmt, an Expr or Nbr an operator, 
  --an Expr or Nbr and an Elmt in that order, check if the Operator has the higher preedene, if it does construct a tree.>
applyARule [elmt0, e1, op@(Op opfn c _ _), e2, elmt3]
    | isOperand e1 && isOperand e2
      && higherPrec elmt0 op elmt3 =
                       let newExpr = Expr e1 (Optr opfn c) e2
                       in  [elmt0, newExpr, elmt3]
-- <Your comment. Why is this defined? If everything else fails just return the list as is>
applyARule window = window


-- <This returns a boolean indicating if op has a higher precedence that leftOp and rightOp>
higherPrec :: Elmt -> Elmt -> Elmt -> Bool  
higherPrec leftOp op rightOp =
  higherPrecThanLeft leftOp op && higherPrecThanRight op rightOp  


-- <This returns a boolean indicating if the right operator has a higher precedence than the left>
higherPrecThanLeft :: Elmt -> Elmt -> Bool
higherPrecThanLeft LPar          _  = True
higherPrecThanLeft (Op _ _ p0 a) (Op _ _ p1 _)
               |a =='L'   = p0 < p1
               |otherwise = p0 <= p1
higherPrecThanLeft _             _             = False
  

-- <This returns a boolean indicating if the left operator has a higher precedence than the right>
higherPrecThanRight :: Elmt -> Elmt -> Bool
higherPrecThanRight _             RPar          = True
higherPrecThanRight (Op _ _ p1 a) (Op _ _ p2 _)
            |a =='R'   = p1 > p2
            |otherwise = p1 >= p2
higherPrecThanRight _           _               = False


isOperand :: Elmt -> Bool
isOperand (Nbr _)      = True
isOperand (Expr _ _ _) = True
isOperand _            = False


-- <This takes a parse state and parses a step in the expression, returns the new parse state>
parseAStep :: ParseState parseState => parseState -> parseState
parseAStep pState = 
  let (left, window, right) = segment pState
      updatedWindow = applyARule window
      updatedState  = unSegment (left, updatedWindow, right)
      newState      = 
         -- If a rule applied, pull three Elmts back
         -- into the window from the left. Otherwise, 
         -- slide the window right by one element.
        if updatedWindow /= window
        then slideWindow (-3) updatedState 
        else slideWindow 1 updatedState 
  in perhapsTrace (" ==> "  ++ showParseState newState) 
     newState 


-- <This takes three lists, replaces all occurences of the first list in the third with the second.>
replace :: Eq a => [a] -> [a] -> [a] -> [a]
replace xs ys zs 
  | xs == take (length xs) zs
               = ys ++ replace xs ys (drop (length xs) zs)
  | w:ws <- zs = w : replace xs ys ws
  | null zs    = []


-- <This performs a function to a state while a condition holds> 
while :: state -> (state -> Bool) -> (state -> state) -> state
while state continueTest bodyFn = wh state
  -- This auxiliary function is not necessary. Defining it
  -- just avoids repeatedly passing  continueTest  and  bodyFn.
  where 
    wh st
      | continueTest st = wh (bodyFn st)
      | otherwise       = st 

-- ---------------------------------------------------------
--  Trace stuff
-- ---------------------------------------------------------

perhapsTrace :: String -> a ->  a
perhapsTrace str e = if   shouldTrace 
                     then trace str e
                     else e
  
  where shouldTrace = True