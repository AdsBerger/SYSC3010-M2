package sysc3010;


public class Controller
{
	private int ShotDuration;
	
	public Controller(int ShotDuration)
	{
		this.ShotDuration= ShotDuration;
	}
	
	public Controller(Controller controller)
    {
        this(controller.ShotDuration);
    }
	
	/*
	public void SendData(Data data)
	{
		
	}
	*/

	public void Phone()
	{
	}
	
	
	public void alarm()
	{
		alarm.CallAlarm();
	}


	public Boolean IsRaining(int humidity)
	{
		if(humidity>200)
		{
			alarm();
			return true;
		}
		return false;
	}
	
	public void RecData()
	{
		
	}


}
