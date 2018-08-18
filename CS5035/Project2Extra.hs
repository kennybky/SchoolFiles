{-Takes a list of Integers Doubles the value of every second Integer from the right,
adds the digits of the result together then  
adds the all elements in the list together and returns the result-}
doubleAndSum :: [Int] -> Int
doubleAndSum = 
  fst . 
  foldr (\i (acc, even) -> (acc + nextStep even i, not even)) (0, False)
  where 
  {-This takes two parameters; a boolean and an integer, 
   performs a function on the integer and returns the result 
  if the boolean is true, else it returns the integer  -}
    nextStep even i
        | even      = (uncurry' (+) . (`divMod` 10) . (*2)) i
        | otherwise = i

 

{-This takes an Integer, convertsfi it to a list of integers, 
doubles the value of every second element from the right, and sums all the digits
returns a boolean value indiating if the sum modulo 10 is 0
-}
myLuhn :: Int -> Bool
myLuhn = (0 ==) . (`mod` 10) . doubleAndSum . (map (read . (: ""))) . show

{-this takes a function as a parameter and returns a function that takes a tuple as a parameter,
performs that function on the two elements of the tuple and returns the result-}
uncurry' :: (t2 -> t1 -> t) -> (t2, t1) -> t
uncurry' f = \(x, y) -> f x y

--this performs the myLuhn function on each members of a list and returns a list of boolean values
testCC :: [Bool]
testCC = 
  map myLuhn [49927398716, 49927398717, 1234567812345678, 1234567812345670]
  -- => [True, False, False, True]
