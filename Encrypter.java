import java.util.Scanner;

public class Encrypter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message;
		int step1;
		boolean contProg = true;
		while (contProg) {
			System.out.println("Gimme sometin' to encrypt!");
			message = sc.nextLine();
			if (message.equals("q")) {
				contProg = false;
			}
			System.out.println("Gimme number for encrypting");
			step1 = sc.nextInt();
			sc.nextLine();
			System.out.println(encryptCeasar(message, step1));
		}
		//System.out.println(decryptCeasar(encryptCeasar(message, step1), step1));

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

}