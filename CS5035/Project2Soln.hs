--Converts an integer to a list of integers
digitToArray :: Int -> [Int]
digitToArray x
      |x == 0 = []
      |otherwise = digitToArray (x `div` 10) ++ [x `mod` 10] 


--Doubles the value of every second element from the right of an list and returns the resulting list
doubleEveryOther :: [Int] -> [Int]
doubleEveryOther xs = 
    zipWith (*) xs cycle12
      where 
         cycle12
           | even (length xs) = tail (cycle [1,2])
           | otherwise = cycle [1,2]


--adds each digit of the input number and returns the result
addDigits :: Int -> Int
addDigits x 
        |x == 0 = 0
        |otherwise = addDigits(x `div` 10) + (x `mod` 10) 

--adds the digits of each element of the list and returns the sum
total :: [Int] -> Int
total = sum . map addDigits


--takes an integer, doubles the value of every second digit from the right and adds it all together
checkSum :: Int -> Int
checkSum = total . doubleEveryOther . digitToArray

--takes an integer and returns a boolean value indicating if the checksum modulo with 10 is 0
isValid :: Int -> Bool
isValid n = checkSum n `mod` 10 == 0


--returns a list of boolean values by calling isValid function on each element of the list
testCC :: [Bool]
testCC = map isValid [79927398713, 79927398714] 