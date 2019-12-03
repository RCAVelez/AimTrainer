package application.model;

public class ScoreBoard {
	
	private int hits;
	private int misses;
	private int target; //add for later functionality
	
	public ScoreBoard()
	{
		this.hits = 0;
		this.misses = 0;
		
	}
	
	public void addMisses()
	{
		this.misses += 1;
	}
	
	public void addHits()
	{
		this.hits += 1;
	}
	
	public int getMisses()
	{
		return this.misses;
	}
	
	public int getHits()
	{
		return this.hits;
	}
	
	@Override 
	public String toString() {
		return "-------------- Scoreboard -------------- \n Hits: " + String.valueOf(getHits()) + "\n Misses: " + String.valueOf(getMisses()) + "\n";
	}
	
}
