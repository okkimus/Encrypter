import java.util.Scanner;

public class Encrypter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message;
		int step1;
		String viesti;
		boolean contProg = true;
		String[] cuesForCracking = {"the", "ck", "an", "st", "be", "re", "ou", "th", "om", "ce", "do", "ing", "in", "on", "at", "of"};
		while (contProg) {
			System.out.println("Gimme sometin' to encrypt (or type insert \"q\" to stop)!");
			message = sc.nextLine();
			if (message.equals("q")) {
				contProg = false;
			}
			System.out.println("Gimme number for encrypting");
			step1 = sc.nextInt();
			sc.nextLine();
			viesti = encryptCeasar(message, step1);
			System.out.println(viesti);



			String[][] lista = rateArrayOfMessages(createCrackedMessages(viesti), cuesForCracking);
			
			
			System.out.println("My wild guess will be:\n" + findBest(lista));
		}
		
	}

	public static String encryptCeasar(String toDecrypt, int step) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		int charsAmount = chars.length();

		int indexOfNewChar; 
		StringBuilder target = new StringBuilder(toDecrypt.toLowerCase());

		for (int i = 0; i < target.length(); i++) {
			if (chars.indexOf(target.charAt(i)) == -1) {
				target.setCharAt(i, ' ');
			} else {
				indexOfNewChar = chars.indexOf(target.charAt(i)) + step;
				while (indexOfNewChar >= charsAmount) {
					indexOfNewChar -= charsAmount;
				}
				target.setCharAt(i, chars.charAt(indexOfNewChar));
			}
		}

		return target.toString();
	}

	public static String decryptCeasar(String toDecrypt, int step) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		int charsAmount = chars.length();

		int indexOfNewChar;
		StringBuilder target = new StringBuilder(toDecrypt.toLowerCase());

		for (int i = 0; i < target.length(); i++) {
			if (chars.indexOf(target.charAt(i)) == -1) {
				target.setCharAt(i, ' ');
			} else {
				indexOfNewChar = chars.indexOf(target.charAt(i)) - step;
				while(indexOfNewChar < 0) {
					indexOfNewChar += charsAmount;
				}
				target.setCharAt(i, chars.charAt(indexOfNewChar));
			}
		}

		return target.toString();
	}

	public static String[][] createCrackedMessages(String toCrack) {
		String[][] list = new String[26][2];

		for (int i = 0; i < 26; i++) {
			list[i][0] = decryptCeasar(toCrack, i);
		}
		return list;
	}

	public static int rateMessage(String mess, String[] cues) {
		int cuesFound = 0;
		for (int i = 0; i < cues.length; i++) {
			if (mess.contains(cues[i])) {
				cuesFound++;
			}
		}
		return cuesFound;
	}

	public static String[][] rateArrayOfMessages(String[][] list, String[] cues) {
		for (int i = 0; i < list.length; i++) {
			list[i][1] = Integer.toString(rateMessage(list[i][0], cues));
		}

		return list;
	}

	public static String findBest(String[][] list) {
		int score = -1;
		int current = 0;
		String highest = "";
		for (int i = 0; i < list.length; i++) {
			current = Integer.parseInt(list[i][1]);
			if (current > score) {
				score = current;
				highest = list[i][0];
			}
		}
		return highest;
	}

}