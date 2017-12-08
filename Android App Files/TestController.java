package sysc3010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestController 
{
	Controller controller;
	
	@Before
    public void setUp() throws Exception
	{
        controller = new Controller(7);
    }

	@Test
	public void TestIsRaining()
	{
        Assert.assertEquals(true, controller.IsRaining(201));
        Assert.assertEquals(false, controller.IsRaining(199));
	}
	
	public void TestAlarm()
	{
		
	}

	public void TestSendData()
	{
		
	}
	
	public void TestRecData()
	{
		
	}
	
	public void TestPhone()
	{
		
	}

}
