import java.util.Scanner;
class labOne {
    public static void main(String[] args) {
    int firstQuestion=  201;
    double castIntToDouble = (double) firstQuestion ;
    double secondQuestion = 3.14159;
    int castDoubleToInt = (int) secondQuestion;
    int thirdQuestion = 2147483647;
    System.out.println("The value of the integer before additon is " + thirdQuestion);
    thirdQuestion++;
    System.out.println("The value of the integer after addition is" + thirdQuestion);
    boolean tall;
    double heightInInches;
    Scanner in = new Scanner (System.in);
    System.out.println("Enter your height in Inches");
    heightInInches = in.nextDouble();
    if (heightInInches >= 70) {
        tall = true;
    } else {
        tall = false;
    }
    System.out.println("Are you tall? Answer= " + tall);
  long fifthQuestion = 2147483647;
System.out.println("The value of the long variable before addition is " + fifthQuestion);
fifthQuestion++;
System.out.println("The value of the long variable after addtion is " + fifthQuestion);
 final double PI = 3.1416;
 int radius = 5;
 double volume = (4.0/3.0) * PI * Math.pow(radius, 3);
 System.out.printf("The Volume is equal to " + "%.3f", volume);
 System.out.println();
 final double INCHESTOCM = 2.54;
 double height = heightInInches;
 System.out.println("Your height in centimeteres is " + height * INCHESTOCM + " centimeters");

double fahrenheit = -40;
double Celsius = (fahrenheit - 32) * (5.0/9.0);
System.out.println("-40 degrees farenheit to celsius is " + Celsius);
fahrenheit = 0;
Celsius = (fahrenheit - 32) * (5.0/9.0);
System.out.println("0 degrees farenheit to celsius is " + Celsius); 
fahrenheit = 212;
Celsius = (fahrenheit - 32) * (5.0/9.0);
System.out.println("212 degrees farenheit to celsius is " + Celsius); 
 int ninthQuestion = 143;
 if (ninthQuestion % 13 == 0) {
     System.out.println("The value is evenly divisible by 13");
 } else {
     System.out.println("The value is NOT evenly divisible by 13");
 }
 ninthQuestion=144;
 if (ninthQuestion % 13 == 0) {
     System.out.println("The value is evenly divisible by 13");
 } else {
     System.out.println("The value is NOT evenly divisible by 13");
 }
 
 if (((2 * 6) > 10) == true && (7 < 5) == false) {
     System.out.println("it is true that 2 * 6 is greater than 10 and false that 7 is less than 5");
 } else {
     System.out.println("it is NOT true that 2 * 6 is greater than 10 and false that 7 is less than 5");
 }
 int age = 23;
 double gpa = 4.0;
 
 if ((age > 30) || gpa > 3.5) {  
     System.out.println("Statement is true"); 
 } else {
     System.out.println("Statement is false"); 
 }
 
 if ((age > 30) && gpa > 3.5) {  
     System.out.println("Statement is true"); 
 } else {
     System.out.println("Statement is false"); 
 }
 
 if ((age <= 30) || gpa <= 3.5) {  
     System.out.println("Statement is true"); 
 } else {
     System.out.println("Statement is false"); 
 }
 if ((age > 30) && gpa <= 3.5) {  
     System.out.println("Statement is true"); 
 } else {
     System.out.println("Statement is false"); 
 }
 
 
}
    
    
}