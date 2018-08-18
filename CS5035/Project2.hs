--Converts an integer to a list of integers
toDigits :: Int -> [Int]
toDigits n = map (\c -> read [c]) (show n)

--returns an infinite list consisting of 1,2
cycle12 :: [Int]
cycle12 = cycle [1,2]


--Doubles the value of every second element from the right of an list and returns the resulting list
doubleEveryOther :: [Int] -> [Int]
doubleEveryOther ds = zipWith (*) (reverse ds) (cycle [1,2])

--adds all the digits of an array together
sumDigits :: [Int] -> Int
sumDigits = sum . concat . map toDigits

--takes an integer, doubles the value of every second digit from the right 
--and adds all the digits in the list together
checkSum :: Int -> Int
checkSum = sumDigits `compose` doubleEveryOther `compose` toDigits 

--takes an integer and returns a boolean value indicating if the checksum modulo with 10 is 0
isValid :: Int -> Bool
isValid n = checkSum n `mod` 10 == 0

--returns a list of boolean values by calling isValid function on each element of the list 
testCC :: [Bool]
testCC = map isValid [79927398713, 79927398714] -- => [True, False]

lamTestCC = (\xs -> map isValid xs) [79927398713, 79927398714]
  
compose :: (b -> c) -> (a -> b) -> (a -> c)
g `compose` f=  \x-> g (f x)