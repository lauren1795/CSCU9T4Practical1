package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {
	private int recovery;
	private int repetitions;
	public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int rep, int rec) {
		super(n,d,m,y,h,min,s,dist);
		this.repetitions = rep;
		this.recovery = rec;
    }
    
	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}
	public int getRecovery() {
		return recovery;
	}
	
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	public int getRepetitions() {
		return repetitions;
	}
  
	public String getEntry() {
		String result = getName() + " sprinted " + getRepetitions() + "x" + getDistance() + "m in " + getHour() 
		+ ":" + getMin() + ":" + getSec() + " with " + getRecovery() + " minutes recovery on " + getDay()
		+ "/" + getMonth() + "/" + getYear() + "\n";
		return result;
	}

}
