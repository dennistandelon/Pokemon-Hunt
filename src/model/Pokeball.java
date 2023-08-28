package model;

public class Pokeball extends Item{

	private double successRate;
	
	public Pokeball() {
		super();
	}
	
	public Pokeball(double successRate, String name, int qty) {
		super(name, qty);
		this.successRate = successRate;
	}
	
	public double getSuccessRate() {
		return successRate;
	}
	
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Pokeball Removed");
	}

	@Override
	public boolean use() {
		setQuantity(getQuantity()-1);
		
		int rand = (int)(successRate + Math.random()*100);
		
		boolean temp =  (rand > 100 - successRate)? true:false;
		return temp;
	}
}
