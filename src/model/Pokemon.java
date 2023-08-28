package model;

public class Pokemon{

	
	private String name, element, type;
	private int battlePower;
	
	// Default constructor 
	public Pokemon() {
		
	}
	
	public Pokemon(String name, String element, String type) {
		super();
		this.name = name;
		this.element = element;
		this.type = type;
		setBattlePower();
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Pokemon Deleted");
	}
	
	protected void setBattlePower() {
		this.battlePower = randomNum(rarity());
	}
	
	public int getBattlePower() {
		return this.battlePower;
	}
	
	protected int rarity() {
		switch(this.type) {
		case "Common": return 100;
		case "Uncommon": return 200;
		case "Rare": return 500;
		case "Epic": return 1000;
		case "Legend": return 5000;
		}
		return 0;
	}
	
	protected int randomNum(int a) {
		return (a + (int)(Math.random()*10000)%a);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getSellPrice() {
		return randomNum(rarity()*200) + getBattlePower();
	}
	
}
	
