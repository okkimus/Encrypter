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
			
			orderArray(lista, 0);

			System.out.println("Was it \""+ lista[0][0] + "\"?");

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

	public static int findBest(String[][] list, int start) {
		int score = -1;
		int current = 0;
		int highest = 0;
		for (int i = start; i < list.length; i++) {
			current = Integer.parseInt(list[i][1]);
			if (current > score) {
				score = current;
				highest = i;
			}
		}
		return highest;
	}

	public static void orderArray(String[][] list, int start) {
		if (start < list.length - 1) {
			int a = findBest(list, start);
			String toSwitch = list[start][0];
			String toScore = list[start][1];

			//take value with the highest score to the "beginning" of the list
			list[start][0] = list[a][0];
			list[start][1] = list[a][1];
			//and replace the switched value with the value from the beginning
			list[a][0] = toSwitch;
			list[a][1] = toScore;
			
			//recursive call to sort whole list
			orderArray(list, start + 1);
		}
	}

}