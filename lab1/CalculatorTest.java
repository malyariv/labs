package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator c=new Calculator();
	
	@Test
	public void testCalculate() {
		String res=c.calculate("1");
		assertEquals("1.0",res);
		
		res=c.calculate("+1");
		assertEquals("1.0",res);
		
		res=c.calculate("-1");
		assertEquals("-1.0",res);

		res=c.calculate("");
		assertEquals("Wrong expression. ",res);
		
		res=c.calculate("1+((1+1)");
		assertEquals("Wrong expression. Wrong brackets",res);
		
		res=c.calculate("1.1.1+((1+1)");
		assertEquals("Wrong expression. Wrong number format",res);
		
		res=c.calculate("1+");
		assertEquals("Wrong expression. Wrong operators",res);
		
		res=c.calculate("2^2^2");
		assertEquals("Wrong expression. Wrong operators",res);
		
		res=c.calculate("(2+4/2)^(1+2*(1+1)-2)^(9/3+1)");
		assertEquals("Wrong expression. Wrong operators",res);
		
		res=c.calculate("2+2+2");
		assertEquals("6.0",res);
		
		res=c.calculate("-1/5");
		assertEquals("-0.2",res);
		
		res=c.calculate("1+(0.2-bx01)*5");
		assertEquals("-3.0",res);
		
		res=c.calculate("1+(0.2-bx01)*5+(-2*(5+1)+ox10*2)*hxa");
		assertEquals("37.0",res);
		
		res=c.calculate("1+(0.2*5+2/4-0.5)*2");
		assertEquals("3.0",res);
		
		res=c.calculate("1+(-0.2*5+2/4-0.5)*2");
		assertEquals("-1.0",res);
		
		res=c.calculate("2^(2+bx01)");
		assertEquals("8.0",res);
		
		res=c.calculate("(2+hxa)^(1+bx01)");
		assertEquals("144.0",res);
		
		res=c.calculate("(2+hxa)^bx10");
		assertEquals("144.0",res);
		
		res=c.calculate("(2+hxa)^bx10/12+1");
		assertEquals("13.0",res);
	}
	

}
