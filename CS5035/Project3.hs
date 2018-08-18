module Project3
( myZipWith,
  myFoldl,
  myFoldr,
  myCycle,
  compose,
  functionPairs,
  functionPairs2,
  while,
  myMap3,
  myWhileFoldl,
  nFibs,
  nPrimes,
  doubleWhile) where

--takes a function and two lists, and then zips the two lists by 
--applying the function between corresponding elements
myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith _ _ [] = []
myZipWith _ [] _ = []
myZipWith f (x:xs) (y:ys) = (f x y) : myZipWith f xs ys


{-this takes a function, a starting value of type a and a list. It uses the function to 
fold up the list from the left and returns the result type a-}
myFoldl :: (b -> a -> b) -> b -> [a] -> b
myFoldl _ x [] = x
myFoldl f x y = myFoldl f (f x (head y)) (tail y)





{-this takes a function, a starting value of type a and a list. It uses the function to 
fold up the list from the right and returns the result type a-}
myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr _ x [] = x
myFoldr f x y = f (head y) acc 
    where 
       acc = myFoldr f x (tail y)

--this takes a list and creates an inifinte list consiting of the elements in the original list
myCycle :: [a] -> [a]
myCycle xs = xs ++ myCycle xs


{-this takes two functions and returns a function 
that performs the right associated function first on an input
and performs the left function with the result as a parameter-}
compose :: (b -> c) -> (a -> b) -> (a -> c)
g `compose` f=  \x-> g (f x)


{-This takes a function and returns a function that takes a list, performs the first 
function on each element and returns the result in a list of tuples-}
functionPairs :: (a -> b) -> [a] -> [(a, b)]
functionPairs f = \xs -> zip xs (map f xs)

functionPairs2 :: (t1 -> t) -> [t1] -> [(t1, t)]
functionPairs2 f = \xs-> map (\x-> (x, f x)) xs


{-this takes an initial value a test function, and a body function. It applies the body function
to the state, each time computing a new state until the test function fails-}
while :: state -> (state -> Bool) -> (state -> state) -> state
while s c f = wh s
  where 
    wh s 
     | c s = wh (f s)
     | otherwise = s



--Extra credit below

{-this takes a function and a list, performs the function on each element of the list
 and returns the resulting list-}
myMap3 :: (a -> b) -> [a] -> [b]
myMap3 f xs = 
  reverse . fst $ 
  while ([],xs) (\(new, old)-> not $ null old) (\(new, (x:xs))-> (f x : new, xs))



{-this takes a function, a starting value of type a and a list. It uses the function to 
fold up the list from the left and returns the result type a-}
myWhileFoldl :: (b -> a -> b) -> b -> [a] -> b
myWhileFoldl f init xs = 
  fst $ while (init, xs)
              (\(_,xs)-> not $ null xs)
              (\(init, x:xs)-> (f init x, xs))



{-this takes an integer n and returns a list of the first n fibonacci numbers
-}
nFibs :: Int -> [Int]
nFibs n = take n $ tail  $ reverse  $ while ([1,0])
                            (\xs-> length xs <= n)
                            (\l@(x:y:xs)-> (x + y) : l) 

--this takes a integer n and returns a list of the first n primes
nPrimes :: Int -> [Int]
nPrimes n = 
       reverse $ fst $
               while ([],[2..]) 
                       (\(xs,_)-> length xs < n ) 
                       (\(xs,(y:ys))-> (y : xs, filter(\a-> a `mod` y /=0 ) ys ))  



--Extra extra credit below

--takes two integers and returs a list of tuples consisting of integers in the bounds of the integer parameters
doubleWhile:: Integer -> Integer -> [(Integer, Integer)]
doubleWhile outer inner = 
  reverse $ fst $ 
    while ([], 1) 
            (\(xs, x) -> x <= outer) 
            (\(xs, x)->
              (fst $ while (xs, 1) (\(xs, y)-> y <= inner) (\(xs, y)-> ((x,y) : xs, y+1) ), x+1))