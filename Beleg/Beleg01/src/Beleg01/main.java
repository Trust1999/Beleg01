package Beleg01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class main {

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Wilkommen beim Texas-Holdem-Poker, bitte geben Sie die"
				+ " Spielerzahl an. (2 - 10)");
		int player = Check(2, 10);
		List<Card> pokergame = new ArrayList<>(52);
		genCardDeck(pokergame);
		Collections.shuffle(pokergame);
		int[] betsum = new int[player];
		Round(0);
		//HandPlayer(pokergame, player, true);
		for(int i = 1 ; i <= 3; i++) {
			/**/HandPlayer(pokergame, player, false);
			/**/Bet(betsum, player);
			Round(i);
			Table(pokergame, i+2, player);
		}
		Round(4);
		System.out.println("Alle Karten werden aufgedeckt.");
		HandPlayer(pokergame, player, true);
	}
	
	private static void Round(int round) {
		switch(round) {
		case 0:
			System.out.println("Dealing Cards");
			break;
		case 1:
			System.out.println("Bieterrunde: Flop");
			break;
		case 2:
			System.out.println("Bieterrunde: Turn");
			break;
		case 3:
			System.out.println("Bieterrunde: River");
			break;
		case 4:
			System.out.println("Sielende");
			break;
			}
		}

	public static int Check(int lowerlimit, int uperlimit) {
		String userinput = "";
		int number = 0;
		
		while (true) {
			try
			{
				userinput = input.next();
				number = Integer.parseInt(userinput);
				if( number >= lowerlimit && number <= uperlimit) {
					return number;
				}
				System.out.println("Eingabe ist out of range");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Eingabe hat ungültiges Format");
			}
		}
	}
	
	public static void Bet (int[] betsum, int player) {
		System.out.println("Erhöhen Sie Ihre Wetten");
		for(int i = 1; i <= player; i++) {
			System.out.println("Spieler " + i +".:");
			int bet = Check(0, 999999999);
			betsum[i-1] += bet;
			System.out.println("Spieler " + i + ". aktueller Einsatz: " + betsum[i-1]);
		}
	}
	
	public static void HandPlayer (List<Card> pokergame, int player, Boolean end ) {
		for(int i = 0; i < player; i++) {
			System.out.println("Spieler:" + (i+1) );
			Boolean show = true;
			if(end == false) {
				show = new String(Yes()).equals("ja");
			}
			if(show == true) {
					System.out.println("Karten: " + pokergame.get(i) + 
							" " + pokergame.get(player+i) + "\n");
			}
		}
	}	
	public static void Table (List<Card> pokergame, int round, int start) {
		String table = "";
		for(int i = 1; i <= round; i++) {
			table += pokergame.get(2*start+i);
			table += " ";
		}
		System.out.println("Auf dem Tisch liegt: " + table + "\n");
		
	}
	
	public static String Yes() {
		String seecard = "";
		do {
			System.out.println("Möchten Sie Ihre Karten sehen? (ja/nein)");
			seecard = input.next();
		}
		while( !seecard.equals("ja") && !seecard.equals("nein"));
		return seecard;
	}
	
	private static void genCardDeck(List<Card> deck) {
		String color[] = {"Kreuz", "Pike", "Herz", "Karo"};
		String value[] = 
		{"Ass", "König", "Dame", "Bube", "10", "9", "8", "7", "6,", "5",
				"3", "2"};
		for(String c: color) {
			for(String v: value) {
				deck.add( new Card(c, v));
			}
		}
	}

}

