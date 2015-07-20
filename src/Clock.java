public class Clock {
	private RolloverCounter hour;
	private RolloverCounter minute;
	private RolloverCounter second;
	
	public Clock(){
		hour = new RolloverCounter(23);
		minute = new RolloverCounter(59);
		second = new RolloverCounter(59);
	}
	public Clock(int h, int mnt, int sec){
		hour = new RolloverCounter(23);
		hour.setValue(h);
		minute = new RolloverCounter(59);
		minute.setValue(mnt);
		second = new RolloverCounter(59);
		second.setValue(sec);
	}
	public boolean timeTick(){
		second.decrement();
		if(second.getValue() == 59)
			minute.decrement();
		if(minute.getValue() == 59 && second.getValue() == 59)
			hour.decrement();
		if(hour.getValue() == 23 && minute.getValue() == 59 && second.getValue() == 59)
			return false;
		
		return true;
	}
	public void setTime(int h, int mnt, int sec){
		hour.setValue(h);
		minute.setValue(mnt);
		second.setValue(sec);
	}
	public String getTime(){
		return hour.getTwoDigitString() + " : " + 
				minute.getTwoDigitString() + " : " + 
				second.getTwoDigitString(); 
	}
}
