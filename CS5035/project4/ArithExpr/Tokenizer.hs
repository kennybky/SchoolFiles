module Tokenizer (tokenize) where

import DataDeclrs (Elmt(..), Optr(Optr), evalExpr) 

import Data.Map as M (fromList, lookup)


---------------------------------------------------------------------
-- These functions convert a string into a list of Elmts.
-- The main function is tokenize.
---------------------------------------------------------------------

-- <This takes a string, and add spaces around characters that are not integers or spaces>
addSpaces :: String  -> String
addSpaces = foldr (\c str -> if c `elem` " 0123456789" 
                             then c:str 
                             else [' ', c, ' '] ++ str) 
                  []

addSpaces2 :: String  -> String
addSpaces2 s = 
    reverse $ doFold [] s
   where
     doFold acc [] = acc
     doFold [] (y:ys) = doFold (y:[]) ys
     doFold acc@(x:xs) (y:ys)
        |(x `elem` " 0123456789" && y `elem` " 0123456789")
           || (not (x `elem` " 0123456789") && not (y `elem` " 0123456789")) = doFold (y:acc) ys
        |(not (x `elem` " 0123456789") && y `elem` " 0123456789")
           ||(x `elem` " 0123456789" && not (y `elem` " 0123456789")) = doFold ([y,' '] ++ acc) ys



-- <This takes a string, and converts it to an Elmt type>
-- < What does makeToken assume about the input String? It assumes the String has only one character>
-- < The symbol for division if '/', but the operation performed is `div`.
--   How can you tell that from this table? The function in the value constructor Op id div >
makeToken :: String -> Elmt
makeToken str = 
  case M.lookup str $ fromList [ ( "(", LPar),             ( ")", RPar) 
                               , ( "+", Op (+) '+' 1 'L'), ( "-", Op (-) '-' 1 'R') 
                               , ( "*", Op (*) '*' 2 'L'), ( "/", Op div '/' 2 'L') 
                               , ( "^", Op (^) '^' 3 'R')
                               ] of
  Just op -> op
  Nothing -> Nbr (read str) -- How do we know we should perform: read str? If the string isn't in the map it must be an integer
                                                                            --else it shouldn't be here
                            -- How do we know what type  read  returns? Because Nbr takes an int


-- <This takes an expression string, and converts each character to an Elmt, returns a list of Elmts>
tokenize :: String -> [Elmt]
tokenize string =  
  concat [[LPar], map makeToken . words . addSpaces $ string, [RPar]]

