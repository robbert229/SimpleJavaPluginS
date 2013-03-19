package core.coreplugins;

import core.plugin.Plugin;
import core.plugin.functions.Function;
import core.plugin.info.Dependency;
import core.plugin.info.Version;

public class TestPlugin extends Plugin {

	private class doFoo extends Function {
		public doFoo(){
			this.name = "doFoo";
			this.description = "a do Foo Bar function";
		}
		@Override
		public <T, X> T run(X x) {
			System.out.println("doFoo");
			return null;
		}
	}
	private class OtherTestFunction extends Function {
		public OtherTestFunction(){
			this.name="Other Function";
			this.description= "The Other Test Function";
		}
		@Override
		public <T, X> T run(X x) {
			System.out.println("OtherTestFunction");
			return null;
		}
	}

	public TestPlugin() {
		this.name = "Test";
		this.description = "A Test plugin with limited functionality";
		this.version = new Version(0, 1);
		this.functions = new Function[] {new doFoo(), new OtherTestFunction()};
		this.dependencies = new Dependency[] {};
	}
}
