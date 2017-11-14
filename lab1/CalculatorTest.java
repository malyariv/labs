package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator c=new Calculator();;


	@Test
	public void testCalculate() {
		Double res=c.calculate("1");
		assertEquals(Double.valueOf(1.0),res);
		
		res=c.calculate("+1");
		assertEquals(Double.valueOf(1),res);
		
		res=c.calculate("-1");
		assertEquals(Double.valueOf(-1),res);
		
		res=c.calculate("-1/5");
		assertEquals(Double.valueOf(-0.2),res);
		
		res=c.calculate("1+(0.2-bx01)*5");
		assertEquals(Double.valueOf(-3.0),res);
		
		res=c.calculate("1+(0.2-bx01)*5+(-2*(5+1)+ox10*2)*hxa");
		assertEquals(Double.valueOf(37.0),res);
		
		res=c.calculate("1+(0.2*5+2/4-0.5)*2");
		assertEquals(Double.valueOf(3.0),res);
		
		res=c.calculate("1+(-0.2*5+2/4-0.5)*2");
		assertEquals(Double.valueOf(-1.0),res);

	}

}
