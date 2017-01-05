import java.util.Scanner;

public class Encrypter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Gimme sometin' to encrypt!");
		String message = sc.nextLine();
		System.out.println("Gimme number for encrypting");
		int step1 = sc.nextInt();

		System.out.println(encryptCeasar(message, step1));
		System.out.println(decryptCeasar(encryptASCII(message, step1), step1));

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
				while (indexOfNewChar > charsAmount) {
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