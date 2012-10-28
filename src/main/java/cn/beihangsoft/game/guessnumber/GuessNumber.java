package cn.beihangsoft.game.guessnumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author Zhiyong
 */
public final class GuessNumber {
	private final static int numberCount = 4;
	private final static int tryCount = 6;
	private String serverNumber;
	private HashMap<String, Integer> hmServerNumber;

	public GuessNumber() {
		serverNumber = "";
		hmServerNumber = new HashMap<String, Integer>();

		while (hmServerNumber.size() < numberCount) {
			String number = Integer.toString((int)(Math.random() * 10));
			if (!hmServerNumber.containsKey(number)) {
				hmServerNumber.put(number, hmServerNumber.size());
				serverNumber += number;
			}
		}
	}

	/**
	 * Only for test
	 *
	 * @param serverNumber
	 */
	public GuessNumber(String serverNumber) throws Exception {
		setServerNumber(serverNumber);
	}

	public void setServerNumber(String serverNumber) throws Exception {
		if (serverNumber == null || serverNumber.length() != numberCount) {
			throw new Exception("The number MUST BE " + numberCount + " digits!");
		}

		hmServerNumber = new HashMap<String, Integer>();
		for (int i = 0; i < numberCount; i++) {
			char ch = serverNumber.charAt(i);
			if (ch < '0' || ch > '9') {
				throw new Exception("The \"" + ch + "\" IS NOT a number!");
			}

			String number = String.valueOf(ch);
			if (hmServerNumber.containsKey(number)) {
				throw new Exception("The number " + number + " is repeated!");
			}

			hmServerNumber.put(number, hmServerNumber.size());
		}

		this.serverNumber = serverNumber;
	}

	public String getServerNumber() {
		return serverNumber;
	}

	public String validate(String clientNumber) throws Exception {
		if (clientNumber == null || clientNumber.length() != numberCount) {
			throw new Exception("The number MUST BE " + numberCount + " digits!");
		}

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		for (int i = 0; i < numberCount; i++) {
			char ch = clientNumber.charAt(i);
			if (ch < '0' || ch > '9') {
				throw new Exception("The \"" + ch + "\" IS NOT a number!");
			}

			String number = String.valueOf(ch);
			if (hashMap.containsKey(number)) {
				throw new Exception("The number " + number + " is repeated!");
			}

			hashMap.put(number, hashMap.size());
		}

		int countA = 0;
		int countB = 0;
		for (int i = 0; i < numberCount; i++) {
			String number = String.valueOf(clientNumber.charAt(i));

			if (hmServerNumber.containsKey(number)) {
				if (hmServerNumber.get(number) == i) {
					countA++;
				}
				else {
					countB++;
				}
			}
		}

		return countA + "A" + countB + "B";
	}

	public static void main(String[] args) {
		int count = 0;
		GuessNumber guessNumber = new GuessNumber();

		while (count < tryCount) {
			System.out.print("Guess the number: ");

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String clientNumber = reader.readLine();

				String result = guessNumber.validate(clientNumber);
				System.out.println(clientNumber + " ==> " + result);

				if (result.equals("4A0B")) {
					System.out.println("Congratulations");
					System.exit(0);
				}

				count++;
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			System.out.println();
		}

		System.out.println("Game Over!\nThe right answer is: " + guessNumber.getServerNumber());
	}
}
