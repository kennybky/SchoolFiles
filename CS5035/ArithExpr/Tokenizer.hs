module Tokenizer (tokenize) where

import DataDeclrs  

import Data.Map as M (fromList, lookup)


---------------------------------------------------------------------
-- These functions convert a string into a list of Elmts.
-- The main function is tokenize.
---------------------------------------------------------------------

-- <This takes a string and sperates non-digits with a space>
addSpaces :: String  -> String
addSpaces = foldr (\c str -> if c `elem` " 0123456789" 
                             then c:str 
                             else [' ', c, ' '] ++ str) 
                  []


-- <Your comment>
{- < What does makeToken assume about the input String? It assumes str will either be an integer
     or a key in the list>-}
makeToken :: String -> Elmt
makeToken str = 
  case M.lookup str $ fromList [  ( "(", LPar),         ( ")", RPar) 
                                , ( "+", Op (+) '+' 1), ( "-", Op (-) '-' 1) 
                                , ( "*", Op (*) '*' 2), ( "/", Op div '/' 2) 
                                , ( "^", Op (^) '^' 3)
                               ] of
  Just op -> op
  Nothing -> Nbr (read str) -- How do we know we should perform: read str? 
                            -- How do we know what type  read  returns? 


-- <Your comment>
tokenize :: String -> [Elmt]
tokenize string =  
  concat [[LPar], map makeToken . words . addSpaces $ string, [RPar]]

