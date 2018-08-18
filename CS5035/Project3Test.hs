import Project3

--finds the first n squares
nSquares:: Int -> [Int] 
nSquares n = 
    reverse . snd $ -- Get the second element of the final state-- and reverse it.
    while (1, []) 
          (\(index, _) -> index <= n) -- n is the argument.
          (\(index, list) -> (index + 1, index^2 : list))


--returns a list consisting of 1 to n
countToN:: Int -> [Int]
countToN n = snd $ while (1, []) (\(x, _) -> x <= n) (\(x, xs) -> (x + 1, xs ++ [x]))


--Checks if all the elements in the list are true
test:: [Bool] -> Bool
test xs = take (length xs) (cycle[True]) == xs


--performs a list of functions and returns a list of booleans
testCases  =   [
                 myZipWith (*) [1,2,3,4] [1,2,1,2,1,2,1,2,1,2] == [1,4,3,8],
                 myZipWith (*) [1,2,3] [9,8,8,6,7] == zipWith (*) [1,2,3] [9,8,8,6,7],
                 myFoldl (+) 0 [1,2,3,4] == 10,
                 myFoldl (-) 0 [1,2,3,4] == foldl (-) 0 [1,2,3,4],
                 myFoldr (+) 0 [1,2,3,4,5,6] == 21,
                 myFoldr (*) 0 [1,2,3,4,5,6] == foldr (*) 0 [1,2,3,4,5,6],
                 (take (20) $ myCycle [1,2] ) == (take (20) $ cycle [1,2]),
                 ((+3) `compose` (*2)) 10 == 23,
                 (sum `compose` map (+3)) [1,2,3,4] == (sum . map (+3)) [1,2,3,4],
                 functionPairs (*2) [1,2,3,4] == [(1,2), (2,4), (3,6), (4,8)],
                 functionPairs (^3) [1,2,3] == [(1,1), (2,8), (3,27)],
                 nSquares 15 == [1,4,9,16,25,36,49,64,81,100,121,144,169,196,225],
                 countToN 20 == [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
                 myMap3 (\c-> [c] ) "12345" == ["1", "2", "3", "4", "5"],
                 myMap3 (`divMod` 2) [1, 2,3,4,5] == map (`divMod` 2) [1, 2,3,4,5],
                 myWhileFoldl (*) 1 [1,2,3,4] == 24,
                 myWhileFoldl (+) 1 [1,2,3,4] == foldl (+) 1 [1,2,3,4],
                 nFibs 10 == [1,1,2,3,5,8,13,21,34,55],
                 nPrimes 15 == [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47],
                 nPrimes 0 == [],
                 doubleWhile 3 5 == doubleLoop 3 5,
                 doubleWhile 2 2 == [(1,1), (1,2), (2,1), (2,2)]
                ]-- All elements should be true


--takes two integers and returs a list of tuples consisting of integers in the bounds of the integer parameters
doubleLoop:: Integer -> Integer -> [(Integer, Integer)]
doubleLoop outer inner = [(oIndex, iIndex) | oIndex <- [1..outer], iIndex <- [1..inner]]


myMap1 _ [] =  []
myMap1 f (x:xs) =  (f x) : myMap1 f xs
