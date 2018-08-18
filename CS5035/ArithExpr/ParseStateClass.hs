module ParseStateClass
( ParseState (..)
, higherPrec
, replace
) where

import DataDeclrs  

import Debug.Trace


-----------------------------------------------------------------------
-- The ParseState TypeClass. A TypeClass is like an Interface in Java.
-- Define a  ParseState  as supporting these functions. Also, require 
-- that every  ParseState  satisfies (i.e., implements)  Eq.
-----------------------------------------------------------------------

-- These functions will depend on the specific implementation
-- of a ParseState.
class Eq parseState => ParseState parseState where
  applyARule :: parseState -> parseState
  continueParse :: parseState -> Bool
  newParseState :: [Elmt] -> parseState
  segment :: parseState -> ([Elmt], [Elmt], [Elmt])
  showState :: String -> parseState -> String
  slideWindow :: Int -> parseState -> parseState


  -- Define  parse  and  parseAStep. They are implementation-
  -- independent and can be used by all  ParseState  instances.
  -- <Your comment>
  parse :: [Elmt] -> parseState  
  parse tokens = 
    let newPState = newParseState tokens
    in  --trace (showState "   " newPState) $
       while newPState
             continueParse
             parseAStep

  -- <Takes a parseState, and parses it, and returns a new parseState>
  parseAStep :: parseState -> parseState
  parseAStep pState = 
    let updatedState = applyARule pState
        newState     = 
           -- If a rule applies, pull three Elmts back
           -- into the window from the left. Otherwise, 
           -- slide the window right by one element.
          if updatedState /= pState
          then slideWindow (-3) updatedState 
          else slideWindow 1 updatedState 
    in -- trace (showState " ==> " newState) 
             newState 


-- -----------------------------------------------------------------------
-- -- Utility functions used by ParseState functions.
-----------------------------------------------------------------------

-- <Returns a boolean value that indicates if the second parameter has a higher precedence 
  --than the first and third parameter>
higherPrec :: Elmt -> Elmt -> Elmt -> Bool  
higherPrec leftOp op rightOp =
  higherPrecThanLeft leftOp op && higherPrecThanRight op rightOp  



-- <Returns a boolean value that indicates if the second parameter has a higher precedence 
  --than the first parameter>
higherPrecThanLeft :: Elmt -> Elmt -> Bool
higherPrecThanLeft LPar        (Op _ _ _)  = True
higherPrecThanLeft (Op _ _ p0) (Op _ _ p1) = p0 < p1
higherPrecThanLeft _           _           = False


-- <Returns a boolean value that indicates if the first parameter has a higher precedence 
  --than the second parameter>
higherPrecThanRight :: Elmt -> Elmt -> Bool
higherPrecThanRight (Op _ _ _)  RPar        = True
higherPrecThanRight (Op _ _ p1) (Op _ _ p2) = p1 >= p2
higherPrecThanRight _           _           = False


-- <Takes three lists, replaces any occurence of the first list in the third list with the second list>
replace :: Eq a => [a] -> [a] -> [a] -> [a]
replace xs ys zs 
  | xs == take (length xs) zs
               = ys ++ replace xs ys (drop (length xs) zs)
  | w:ws <- zs = w : replace xs ys ws
  | null zs    = []


-- <this takes an initial value a test function, and a body function. It applies the body function
--to the state, each time computing a new state until the test function fails> 
while :: state -> (state -> Bool) -> (state -> state) -> state
while state continueTest bodyFn = wh state
  -- This auxiliary function is not necessary. Defining it
  -- just avoids repeatedly passing  continueTest  and  bodyFn.
  where wh st
           | not (continueTest st) = st
           | otherwise             = wh (bodyFn st) 
