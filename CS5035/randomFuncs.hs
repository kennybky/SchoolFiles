
import Data.Map as M (fromList, lookup)
import Debug.Trace

bmiTell :: (RealFloat a) => a -> a -> String  
bmiTell weight height  
    | weight / height ^ 2 <= 18.5 = "You're underweight, you emo, you!"  
    | weight / height ^ 2 <= 25.0 = "You're supposedly normal. Pffft, I bet you're ugly!"  
    | weight / height ^ 2 <= 30.0 = "You're fat! Lose some weight, fatty!"  
    | otherwise                 = "You're a whale, congratulations!"


toDigits :: [Int]
toDigits = map (\c -> read [c]) "123456"

digs 0 = []
digs x = digs (x `div` 10) ++ [x `mod` 10]


nextStep even i
        | even      =  i *2
        | otherwise = i

head' :: [a] -> a  
head' xs = case xs of [] -> error "No head for empty lists!"  
                      (x:_) -> x  

doFunc n (x:xs)
      | n == 0 = 0
doFunc n xs
      | n == 0 = 1
doFunc n (x:y:xs)
      | n < 0 = -1

parses n [] = 1
parses n [_] = 2
parses n xs = 4

