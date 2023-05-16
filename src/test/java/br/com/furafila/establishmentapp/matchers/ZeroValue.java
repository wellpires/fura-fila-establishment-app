package br.com.furafila.establishmentapp.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ZeroValue extends TypeSafeMatcher<Number> {

	@Override
	public void describeTo(Description description) {
		description.appendText("only non-zero values");

	}

	@Override
	protected boolean matchesSafely(Number item) {
		return item.doubleValue() <= 0;
	}

	public static ZeroValue zeroValue() {
		return new ZeroValue();
	}

}
