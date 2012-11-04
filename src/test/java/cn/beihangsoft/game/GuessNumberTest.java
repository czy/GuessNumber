package cn.beihangsoft.game;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

public class GuessNumberTest {
	private GuessNumber guessNumber;

	@Before
	public void initGuessNumber() throws Exception {
		guessNumber = new GuessNumber("1234");
	}

	@Test
	public void shouldReturn0A0B() throws Exception {
		String result = guessNumber.validate("5678");
		assertEquals(result, "0A0B");
	}

	@Test
	public void shouldReturn0A1B() throws Exception {
		String result = guessNumber.validate("5671");
		assertEquals(result, "0A1B");
	}

	@Test
	public void shouldReturn0A2B() throws Exception {
		String result = guessNumber.validate("5612");
		assertEquals(result, "0A2B");
	}

	@Test
	public void shouldReturn0A3B() throws Exception {
		String result = guessNumber.validate("5123");
		assertEquals(result, "0A3B");
	}

	@Test
	public void shouldReturn0A4B() throws Exception {
		String result = guessNumber.validate("4321");
		assertEquals(result, "0A4B");
	}

	@Test
	public void shouldReturn1A0B() throws Exception {
		String result = guessNumber.validate("1678");
		assertEquals(result, "1A0B");
	}

	@Test
	public void shouldReturn1A1B() throws Exception {
		String result = guessNumber.validate("1378");
		assertEquals(result, "1A1B");
	}

	@Test
	public void shouldReturn1A2B() throws Exception {
		String result = guessNumber.validate("1348");
		assertEquals(result, "1A2B");
	}

	@Test
	public void shouldReturn1A3B() throws Exception {
		String result = guessNumber.validate("1342");
		assertEquals(result, "1A3B");
	}

	@Test
	public void shouldReturn2A0B() throws Exception {
		String result = guessNumber.validate("1278");
		assertEquals(result, "2A0B");
	}

	@Test
	public void shouldReturn2A1B() throws Exception {
		String result = guessNumber.validate("1273");
		assertEquals(result, "2A1B");
	}

	@Test
	public void shouldReturn2A2B() throws Exception {
		String result = guessNumber.validate("1243");
		assertEquals(result, "2A2B");
	}

	@Test
	public void shouldReturn3A0B() throws Exception {
		String result = guessNumber.validate("1238");
		assertEquals(result, "3A0B");
	}

	@Test
	public void shouldReturn4A0B() throws Exception {
		String result = guessNumber.validate("1234");
		assertEquals(result, "4A0B");
	}

	@Test
	public void checkNumberLength() {
		GuessNumber gn = new GuessNumber();
		String number = gn.getServerNumber();
		assertEquals(number.length(), 4);
	}

	@Test
	public void isValidNumber() {
		GuessNumber gn = new GuessNumber();
		String number = gn.getServerNumber();
		for (int len = number.length(), i = 0; i < len; i++) {
			assertTrue(number.charAt(i) >= '0');
			assertTrue(number.charAt(i) <= '9');
		}
	}

	@Test
	public void hasRepeatedNumber() {
		GuessNumber gn = new GuessNumber();
		String number = gn.getServerNumber();
	}
}
