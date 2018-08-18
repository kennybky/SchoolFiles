
import static org.junit.Assert.*;

import org.junit.Test;

public class MyFractionTest {
	
	@Test
	public void testNumerator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertEquals(f.getNumerator(), num);
	}
	
	@Test
	public void testDenominator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertEquals(f.getDenominator(), denom);
	}
	
	@Test
	public void testSign() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertEquals(f.getSign(), sign);
	}
	
	@Test
	public void testGetSign() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertNotNull(f.getSign());
	}
	
	@Test
	public void testGetNumerator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertNotNull(f.getNumerator());
		
	}
	
	@Test
	public void testGetDenominator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertNotNull(f.getDenominator());
	}
	
	@Test
	public void testSetSign() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		f.setSign('-');
		assertEquals(f.getSign(), '-');
		
	}
	
	@Test
	public void testSetNumerator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		f.setNumerator(1);
		assertEquals(f.getNumerator(), 1);
	}
	
	@Test
	public void testSetDenominator() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		f.setDenominator(4);
		assertEquals(f.getDenominator(), 4);
	}
	
	@Test
	public void testBreakDownFraction() {
		int num = 6;
		int  denom = 8;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		
		assertEquals(f.getNumerator(), 3);
		assertEquals(f.getDenominator(), 4);
	}
	
	
	

	@Test
	public void testMyFraction() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertNotNull(f);
	}

	@Test
	public void testAdd() {
		int num = 3;
		int  denom = 4;
		int numOne = 10;
		int  denomOne = 3;
		char sign = '-';
		char signOne = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		MyFraction f1 = new MyFraction(numOne, denomOne, signOne);
		MyFraction expected = new MyFraction(31, 12, '+');
		MyFraction actual = f.add(f1);
		assertNotNull(actual);
		assertEquals(actual.getNumerator(), expected.getNumerator());
		assertEquals(actual.getDenominator(), expected.getDenominator());
		assertEquals(actual.getSign(), expected.getSign());
	}

	@Test
	public void testSubtract() {
		int num = 10;
		int  denom = 7;
		int numOne = 8;
		int  denomOne = 9;
		char sign = '-';
		char signOne = '-';
		MyFraction f = new MyFraction(num, denom, sign);
		MyFraction f1 = new MyFraction(numOne, denomOne, signOne);
		MyFraction expected = new MyFraction(34, 63, '-');
		MyFraction actual = f.subtract(f1);
		assertNotNull(actual);
		assertEquals(actual.getNumerator(), expected.getNumerator());
		assertEquals(actual.getDenominator(), expected.getDenominator());
		assertEquals(actual.getSign(), expected.getSign());
	}

	@Test
	public void testMultiply() {
		int num = 2;
		int  denom = 3;
		int numOne = 1;
		int  denomOne = 4;
		char sign = '+';
		char signOne = '-';
		MyFraction f = new MyFraction(num, denom, sign);
		MyFraction f1 = new MyFraction(numOne, denomOne, signOne);
		MyFraction expected = new MyFraction(1, 6, '-');
		MyFraction actual = f.multiply(f1);
		assertNotNull(actual);
		assertEquals(actual.getNumerator(), expected.getNumerator());
		assertEquals(actual.getDenominator(), expected.getDenominator());
		assertEquals(actual.getSign(), expected.getSign());
	}

	@Test
	public void testDivide() {
		int num = 2;
		int  denom = 3;
		int numOne = 1;
		int  denomOne = 3;
		char sign = '-';
		char signOne = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		MyFraction f1 = new MyFraction(numOne, denomOne, signOne);
		MyFraction expected = new MyFraction(2, 1, '-');
		MyFraction actual = f.divide(f1);
		assertNotNull(actual);
		assertEquals(actual.getNumerator(), expected.getNumerator());
		assertEquals(actual.getDenominator(), expected.getDenominator());
		assertEquals(actual.getSign(), expected.getSign());
	}

	@Test
	public void testToString() {
		int num = 2;
		int  denom = 3;
		char sign = '+';
		MyFraction f = new MyFraction(num, denom, sign);
		assertNotNull(f.toString());
	}

}
