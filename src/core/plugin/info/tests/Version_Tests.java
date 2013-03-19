package core.plugin.info.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import core.plugin.info.*;

public class Version_Tests {

	@Test
	public void VersionCompareTo_A() {
		Version aSmall = new Version(0, 0);
		Version bSmall = new Version(0, 0);
		if (aSmall.compareTo(bSmall) != 0)
			fail("Comparison Failed");
	}

	@Test
	public void VersionCompareTo_B() {
		Version a = new Version(2, 0);
		Version b = new Version(2, 0);
		if (a.compareTo(b) != 0)
			fail("Comparison Failed");
	}

	@Test
	public void VersionCompareTo_C() {
		Version small = new Version(0, 0);
		Version big = new Version(1, 0);
		if (big.compareTo(small) <= 0)
			fail("Comparison Failed");
	}

	@Test
	public void VersionCompareTo_D() {
		Version small = new Version(0, 0);
		Version big = new Version(1, 0);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

	@Test
	public void VersionCompareTo_E() {
		Version small = new Version(13, 38);
		Version big = new Version(14, 38);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

	@Test
	public void VersionCompareTo_F() {
		Version small = new Version(19, 01);
		Version big = new Version(19, 02);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

}
