package model;

import java.util.ArrayList;

import main.Main;


public class Pokedex {

	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	int money = 1000;
	
	public Pokedex() {
		this.defaultItem();
	}
	
	
	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public void insertPokemon(String name, String element, String type) {
		pokemonList.add(new Pokemon(name, element, type));
	}
	
	public void showPokemon() {
		System.out.println("My Pokemon");
		if(pokemonList.size() == 0) {
			System.out.println("==================================");
			System.out.println("No pokemon collected!");
			return;
		}
		
		int i = 1;
		for (Pokemon pokemon : pokemonList) {
			System.out.println("==================================");
			System.out.println((i++) + ". " + pokemon.getName());
			System.out.println("Element: " + pokemon.getElement());
			System.out.println("Type: " + pokemon.getType());
			System.out.println("BP : " + pokemon.getBattlePower());
		}
		System.out.println("==================================");
		
	}
	
	public void showItem() {
		System.out.println("My Items");
		
		if(itemList.size() == 0) {
			System.out.println("==================================");
			System.out.println("No item!");
			return;
		}
		
		for (Item item : itemList) {
			System.out.println("==================================");
			System.out.println(item.getName() + " x" + item.getQuantity());
		}
		System.out.println("==================================");
	}
	
	public boolean usePokeball() {
		int i = 0;
		for (Item item : itemList) {
			if(item instanceof Pokeball) {
				System.out.println((++i) + ". " + item.getName() +", qty: " + item.getQuantity());
			}
		}
		
		if(i == 0) {
			System.out.println("No pokeball to use...");
			
			return false;
		}
		
		int select = -1;
		do {
			System.out.print("[Choose pokeball] >> ");
			select = Main.getInt();
		} while(!(select >= 1 && select <= i));
		
		int k = 0;
		for (Item item : itemList) {
			if(item instanceof Pokeball) {
				if(i == select-1) {
					break;
				}
				i++;
			}
			k++;
		}
		
		boolean temp = ((Pokeball) itemList.get(k-1)).use();
		
		if(((Pokeball) itemList.get(k-1)).getQuantity() == 0) {
			itemList.remove(k-1);
		}
		
		return temp;
	}
	
	private void defaultItem() {
		
		itemList.add((Item) new Pokeball(25.0, "Normal Pokeball", 3));
	}
	
	
	public void buyItem() {
		
		if(money == 0) {
			System.out.println("No enough money");
			return;
		}
		
		int select = -1;
		do {
			Main.cls();
			System.out.println("Buy Item");
			System.out.println("================");
			System.out.println("PokeGems: " + getMoney());
			System.out.println("1. Normal Pokeball (25%) 200 PokeGems");
			System.out.println("2. Ultra Pokeball (40%) 500 PokeGems");
			System.out.println("3. Super Pokeball (70%) 1000 PokeGems");
			System.out.println("4. Change pokemon element 1000 PokeGems");
			System.out.println("5. back");
			System.out.print(">> ");
			
			select = Main.getInt();
			switch (select) {
			case 1: buyPokeball("Normal Pokeball", 25 , 200);
				break;
			case 2: buyPokeball("Ultra Pokeball", 40 , 500);
				break;
			case 3: buyPokeball("Super Pokeball", 70 , 1000);
				break;
			case 4: changeElement();
				break;
			}
			Main.sleep(1000);
		} while(select != 5);
		
	}
	
	private void changeElement() {
		if(money < 1000) {
			System.out.println("Not enough money");
			return;
		}
		
		if(pokemonList.size() <= 0) {
			System.out.println("No pokemon in the list");
			return;
		}
		
		int menu = -1, element = -1;
		do {
			showPokemon();
			
			System.out.print("Input pokemon name to change element: ");
			menu = Main.getInt();
		} while(!(menu >= 1 && menu <= pokemonList.size()));
		
		do {
			
			System.out.println("New Element");
			System.out.println("1. Pyro");
			System.out.println("2. Cyro");
			System.out.println("3. Dendro");
			System.out.println("4. Anemo");
			System.out.println("5. Geo");
			System.out.println("6. Electro");
			System.out.println("7. Hydro");
			
			System.out.println("Select Element to choose: ");
			element = Main.getInt();
		} while(!(element >= 1 && element <= 6));
		
		switch (element) {
		case 1: pokemonList.get(menu).setElement("Pyro");
			break;
		case 2: pokemonList.get(menu).setElement("Cyro");
			break;
		case 3: pokemonList.get(menu).setElement("Dendro");
			break;
		case 4: pokemonList.get(menu).setElement("Anemo");
			break;
		case 5: pokemonList.get(menu).setElement("Geo");
			break;
		case 6:pokemonList.get(menu).setElement("Elecro");
			break;
		case 7: pokemonList.get(menu).setElement("Hydro");
			break;
		}
		
		System.out.println("Successfully changed");
		Main.sleep(1000);
		
	}
	
	private void buyPokeball(String name,double sr, int price) {
		int buy = -1;
		do {
			System.out.println("Input number of pokeball you want to buy: ");
			buy = Main.getInt();
			
			if(buy * price > money) {
				System.out.println("Not enough money");
				Main.sleep(1000);
				return;
			}
		} while(buy < 1);
		
		System.out.println("Successfully bought pokeball!");
		insertItem(name, sr, buy);
		money -= (buy*price);
		
	}
	
	public void insertItem(String name, double success, int qty) {
		for (Item item2 : itemList) {
			if(item2.getName().equals(name)) {
				item2.setQuantity(item2.getQuantity() + qty);
				return;
			}
		}
		
		itemList.add((Item) new Pokeball(success, name, qty));
		
	}
	
	public void sellPokemon() {
		int select = -1;
		do {
			Main.cls();
			showPokemon();
			System.out.print("Choose pokemon to sell: ");
			select = Main.getInt();
		} while(!(select >= 1 && select <= pokemonList.size()));
		
		System.out.print("Sold for" + pokemonList.get(select-1).getSellPrice());
		money = money + pokemonList.get(select-1).getSellPrice();
		pokemonList.remove(select-1);
		
		Main.sleep(1000);
	}
	

}
