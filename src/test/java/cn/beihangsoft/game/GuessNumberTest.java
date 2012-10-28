package cn.beihangsoft.game;

import static junit.framework.Assert.*;
import org.junit.Test;

public class GuessNumberTest {
	@Test
	public void shouldReturn0A0B() throws Exception {
		GuessNumber guessNumber = new GuessNumber("1234");
		String result = guessNumber.validate("5678");
		assertEquals(result, "0A0B");
	}

	@Test
	public void shouldReturn0A4B() throws Exception {
		GuessNumber guessNumber = new GuessNumber("1234");
		String result = guessNumber.validate("4321");
		assertEquals(result, "0A4B");
	}

	@Test
	public void shouldReturn2A2B() throws Exception {
		GuessNumber guessNumber = new GuessNumber("1234");
		String result = guessNumber.validate("1243");
		assertEquals(result, "2A2B");
	}

	@Test
	public void shouldReturn4A0B() throws Exception {
		GuessNumber guessNumber = new GuessNumber("1234");
		String result = guessNumber.validate("1234");
		assertEquals(result, "4A0B");
	}

	@Test
	public void shouldReturn1A0B() throws Exception {
		GuessNumber guessNumber = new GuessNumber("1234");
		String result = guessNumber.validate("7654");
		assertEquals(result, "0A1B");
	}
}
