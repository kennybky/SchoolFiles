--This returns the prime divisors of a number
primeDivisors :: Int -> [Int]
primeDivisors n = [d | d <- takeWhile (\x -> x^2 <= n) primes, n `mod` d == 0]


--This contains the list of primes
primes :: [Int]
primes = 2 : [n | n <- oddsFrom3, null (primeDivisors n)]


--This contains all the odd numbers from 3
oddsFrom3 :: [Int]
oddsFrom3 = [3, 5 .. ]



--This checks if an element is prime or not. It returns True if prime and false if not
isPrime :: Int -> Bool 
isPrime n = if n < 2 then False
             else [] == primeDivisors n




--computes the double squares of numbers from 1 to the number given
doubleSquares :: [Int]
doubleSquares =  [2* (x^2) | x<-[1,2..]]



--This determines whether a number passes Goldbach's second Conjecture. It returns True if it passes and false if it fails
goldbach :: Int-> Bool
goldbach n =
      --[] /= filter (\x-> isPrime (n-x)) (takeWhile (\x ->  x < n) (doubleSquares n))
         not (null [x | x <-  takeWhile(\i-> i < n) doubleSquares, isPrime(n-x)])

findGoldbachFails :: [Int]
findGoldbachFails = take 2 [x| x <- oddsFrom3, not (goldbach x), not(isPrime x)]