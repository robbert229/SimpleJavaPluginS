package core.plugin.info.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.plugin.info.*;

public class Dependency_Tests {
	Dependency core_0_1 = new Dependency("core", 0, 1);
	Dependency PluginA_0_3 = new Dependency("PluginA", 0, 3);
	Dependency Ignition_0_0 = new Dependency("Ignition", 0, 0);
	Dependency[] installed = { core_0_1, PluginA_0_3, Ignition_0_0 };
	String installList = "Installed : Core 0.1, PluginA 0.3, Ignition 0.0";

	@Test
	public void DependencyCompareTo_A() {
		Dependency aSmall = new Dependency("Foo", 0, 0);
		Dependency bSmall = new Dependency("Foo", 0, 0);
		if (aSmall.compareTo(bSmall) != 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_B() {
		Dependency a = new Dependency("Foo", 2, 0);
		Dependency b = new Dependency("Foo", 2, 0);
		if (a.compareTo(b) != 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_G() {
		Dependency a = new Dependency("Foo", 34, 03);
		Dependency b = new Dependency("Foo", 34, 03);
		if (a.compareTo(b) != 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_H() {
		Dependency a = new Dependency("Foo", 34, 03);
		Dependency b = new Dependency("Bar", 34, 03);
		if (a.compareTo(b) == 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_C() {
		Dependency small = new Dependency("Foo", 0, 0);
		Dependency big = new Dependency("Foo", 1, 0);
		if (big.compareTo(small) <= 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_D() {
		Dependency small = new Dependency("Foo", 0, 0);
		Dependency big = new Dependency("Foo", 1, 0);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_E() {
		Dependency small = new Dependency("Foo", 13, 38);
		Dependency big = new Dependency("Foo", 14, 38);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

	@Test
	public void DependencyCompareTo_F() {
		Dependency small = new Dependency("Foo", 19, 01);
		Dependency big = new Dependency("Foo", 19, 02);
		if (small.compareTo(big) >= 0)
			fail("Comparison Failed");
	}

	/*
	 * @Test public void PluginNonExistant() { Dependency[] required = {new
	 * Dependency("Pizza",0,0)}; TestPlugin testPlugin = new TestPlugin();
	 * testPlugin.setDependency(required); if(testPlugin.depCheck(installed))
	 * fail("depCheck: Dependent Pizza 0.0, " + installList); }
	 * 
	 * @Test public void PluginExistsTooOld(){ Dependency[] required = {new
	 * Dependency("core",0,4)}; TestPlugin testPlugin = new TestPlugin();
	 * testPlugin.setDependency(required); if(testPlugin.depCheck(installed))
	 * fail("depCheck: Dependent Core 0.4, " + installList); }
	 * 
	 * @Test public void PluginExistsPerfect(){ Dependency[] required = {new
	 * Dependency("core",0,1)}; TestPlugin testPlugin = new TestPlugin();
	 * testPlugin.setDependency(required); if(!testPlugin.depCheck(installed))
	 * fail("depCheck : Dependent Core 0.1, " + installList ); }
	 * 
	 * @Test public void PluginExistsNewer(){ Dependency[] required = {new
	 * Dependency("core",0,1)}; TestPlugin testPlugin = new TestPlugin();
	 * testPlugin.setDependency(required); if(!testPlugin.depCheck(new
	 * Dependency[]{new Dependency("core",0,5)}))
	 * fail("depCheck : Dependent Core 0.1, Installed : Core 0.5"); }
	 */

}
