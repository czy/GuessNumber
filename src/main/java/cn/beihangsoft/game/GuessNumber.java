package cn.beihangsoft.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Zhiyong
 */
public final class GuessNumber {
	private static int numberCount = 4;
	private static int tryCount = 6;
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
		InputStream is = GuessNumber.class.getResourceAsStream("/config.properties");
		if (is != null) {
			Properties properties = new Properties();
			try {
				properties.load(is);
				numberCount = Integer.parseInt(properties.getProperty("numberCount"));
				tryCount = Integer.parseInt(properties.getProperty("tryCount"));

				if (numberCount < 3) {
					numberCount = 3;
				}
				if (numberCount > 10) {
					numberCount = 10;
				}
				if (tryCount < 3) {
					tryCount = 3;
				}
				if (tryCount > 20) {
					tryCount = 20;
				}
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		int count = 0;
		GuessNumber guessNumber = new GuessNumber();

		while (count < tryCount) {
			System.out.format("Guess the number(%d digits, trying %d/%d): ", numberCount, count + 1, tryCount);

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String clientNumber = reader.readLine();

				String result = guessNumber.validate(clientNumber);
				System.out.println(clientNumber + " ==> " + result);

				if (result.equals("4A0B")) {
					System.out.println("\nCongratulations!");
					System.exit(0);
				}

				count++;
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		System.out.println("\nGame Over!\nThe right answer is: " + guessNumber.getServerNumber());
	}
}
