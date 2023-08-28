package main;

import java.util.Scanner;

import model.Pokedex;

public class Main{
	
	static Scanner scan = new Scanner(System.in);
	Pokedex myPokedex = new Pokedex();

	public Main() {
		
		int menu = -1;
		do {
			cls();
			System.out.println("POKEMON GO");
			System.out.println("=================");
			System.out.println("1. View Pokedex");
			System.out.println("2. Manage Pokedex");
			System.out.println("3. Find Pokemon");
			System.out.println("4. Exit");
			System.out.println("=================");
			System.out.print(">> ");
			menu = getInt();
			
			switch (menu) {
			case 1:
				viewPokedex();
				break;
			case 2:
				managePokedex();
				break;
			case 3:
				play();
				break;
			}
			
		} while(menu != 4);
	}
	
	private void play() {
		boolean notFound = true;
		do {
			cls();
			word1by1("Searching nearby pokemon...");
			notFound = !findPokemon();
			
			cls();
			if(!notFound) {
				break;
			}
			System.out.println("No pokemon nearby. Press enter to continue searching...");
			scan.nextLine();
		} while(notFound);
		
		String type = generateType();
		String element = generateElement();
		System.out.printf("A wild %s %s pokemon found!\n", type, element);
		
		if(!myPokedex.usePokeball()) {
			System.out.println("Failed to catch..");
			sleep(2000);
			return;
		}
		
		
		String name;
		do {
			System.out.print("Name your pokemon: ");
			name =scan.nextLine();
		} while(name.length() < 1);
		
		myPokedex.insertPokemon(name, element, type);
		
	}


	private String generateElement() {
		int num = (int)(Math.random()*1000)%6;
		switch (num) {
		case 1: return "Cyro";
		case 2: return "Pyro";
		case 3: return "Dendro";
		case 4: return "Electro";
		case 5: return "Geo";
		}
		return "Anemo";
	}

	public String generateType() {
		int num = (int)(Math.random()*1000)%100;
		if(num == 99) {
			return "Legend";
		} else if(num >= 90) {
			return "Epic";
		} else if(num >= 75) {
			return "Rare";
		} else if(num >= 45) {
			return "Uncommon";
		}
		return "Common";
	}
	
	public void word1by1(String word) {
		
		int loop = (int)(Math.random()*1000)%100;
		int curr = 0;
		for(int i=0; i < loop; i++) {
			if(curr >= word.length()) {
				curr %= word.length();
				cls();
			}
			
			System.out.print(word.charAt(curr));
			sleep(100);
			curr++;
		}
		
		System.out.println("");
		
	}
	
	private boolean findPokemon() {
		int rand = (int)(Math.random()*10000)%2;
		return (rand == 0)? true:false;
	}

	private void managePokedex() {
		int menu = -1;
		do {
			cls();
			System.out.println("POKEMON GO");
			System.out.println("=================");
			System.out.println("PokeGems: " + myPokedex.getMoney());
			System.out.println("1. Buy Item");
			System.out.println("2. Sell Pokemon");
			System.out.println("3. Back");
			System.out.println("=================");
			System.out.print(">> ");
			menu = getInt();
			
			switch (menu) {
			case 1:
				myPokedex.buyItem();
				break;

			case 2:
				myPokedex.sellPokemon();
				break;
			}
			System.out.println("");
			
		} while(menu != 3);
		
	}

	private void viewPokedex() {
		System.out.println("");
		myPokedex.showPokemon();
		
		System.out.println("");
		myPokedex.showItem();
		System.out.println("");
		
		System.out.println("Press enter to continue...");
		scan.nextLine();
		
	}

	public static void main(String[] args) {
		new Main();
	}
	
	public static int getInt() {
		int num = -1;
		try {
			num = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			
		}
		return num;
	}

	public static void cls() {
		for(int i=0; i<50; i++) {
			System.out.println("");
		}
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
