public class RolloverCounter {
	private int limit;
	private int value;
	public RolloverCounter(int rollOverLimit){
		limit = rollOverLimit;
	}
	public int getValue(){
		return value;
	}
	public String getTwoDigitString(){
		if(value < 10)
			return ""+0+""+value;
		else
			return ""+value;
	}
	public void setValue(int replacementValue){
		if(replacementValue >0 && replacementValue <limit)
			value = replacementValue;
	}
	public void increment(){
		if(++value > limit)
			value = 0;
	}
	public void decrement() {
		if(--value < 0)
			value = limit;
	}
}