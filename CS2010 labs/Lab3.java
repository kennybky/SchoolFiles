import java.util.Scanner;
public class Lab3 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;
		double maxPerimeter = 0;
		double maxVolumeOfSphere= 0;
		double maxVolumeOfCylinder = 0;
		do {
			choice = getChoice();

			switch (choice) {
			case 0:
				System.out.println("goodbye");
				break;
			case 1:
				double perimeterOfCircle = perimeterOfCircle(0, 100);
				System.out.println("perimeter of circle is " + perimeterOfCircle);
				maxPerimeter = calcMaxValue(perimeterOfCircle, maxPerimeter);
				break;
			case 2:
				double volumeOfSphere = volumeOfSphere(0, 100);
				System.out.println("Volume of sphere is " + volumeOfSphere);
				maxVolumeOfSphere = calcMaxValue(volumeOfSphere, maxVolumeOfSphere);
				break;
			case 3:
				double volumeOfCylinder = volumeOfCylinder(0, 100);
				System.out.println("Volume of Cylinder is " + volumeOfCylinder);
				maxVolumeOfCylinder = calcMaxValue(volumeOfCylinder, maxVolumeOfCylinder);
				break;
			}

		} while(choice !=0);

		System.out.println("Largest Perimeter of Circle = " + maxPerimeter);
		System.out.println("Largest Volume of Sphere = " + maxVolumeOfSphere);
		System.out.println("Largest Volume of Cylinder = " + maxVolumeOfCylinder);

	}

	public static double getInputValue (double min, double max, String valueType) {
		double value;
		do {
			System.out.println("Please enter a value between " + min + " and "
					+ max + " for the " + valueType);
			while (!sc.hasNextDouble()) {
				String badInputString = sc.nextLine();
				System.out.println(badInputString + " is not a valid double. Try again");
			}
			value = sc.nextDouble();
			sc.nextLine();
		} while (value < min || value > max);

		return value;
	}

	public static double perimeterOfCircle(double min, double max) {
		double radius = getInputValue(min, max, "radius");
		double perimeter = 2 * Math.PI * radius;
		return perimeter;
	}

	public static double volumeOfSphere(double min, double max) {
		double radius = getInputValue(min, max, "radius");
		double volume = (4.0/3.0) * Math.PI * radius;
		return volume;
	}

	public static double volumeOfCylinder(double min, double max) {
		double radius = getInputValue(min, max, "radius");
		double height = getInputValue(min, max, "height");
		double volume = Math.PI * Math.pow(radius, 2) * height;
		return volume;
	}

	public static double calcMaxValue(double value, double maxValue) {
		if (value > maxValue) {
			return value;
		} else {
			return maxValue;
		}
	}
	public static int getChoice() {
		int choice;
		do {
			System.out.println("Enter 0 to quit, 1 to calculate the perimeter of the circle, 2 to calculate the volume of the sphere,"
					+ "or 3 to calculate the volume of the cylinder");

			while (!sc.hasNextInt()) {
				String badString = sc.nextLine();
				System.out.println(badString + " is Invalid. Please enter a valid Integer between 0 to 3");
			}
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice < 0 || choice > 3);
		return choice;
	}
	
}
