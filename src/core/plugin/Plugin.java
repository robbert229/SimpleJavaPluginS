package core.plugin;

import java.util.Arrays;

import core.plugin.*;
import core.plugin.info.*;
import core.plugin.functions.*;

@SuppressWarnings("unused")
public abstract class Plugin implements IPlugin {
	protected String name;
	protected String description;
	protected Version version;
	protected IFunction[] functions;
	protected Dependency[] dependencies;
	
	public boolean hasError(Dependency[] dependency){
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVersion(int maj, int min) {
		this.version = new Version(maj, min);
	}

	public void setFunction(IFunction[] functions) {
		this.functions = functions;
	}

	public void setFunction(IFunction function, int i) {
		this.functions[i] = function;
	}
	
	public void setDependencies(Dependency[] dependencies){
		this.dependencies = dependencies;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Version getVersion() {
		return version;
	}

	public IFunction[] getFunction() {
		return functions;
	}

	public IFunction getFunction(int i) {
		return functions[i];
	}
	public Dependency[] getDependencies(){
		return this.dependencies;
	}
	public void onPreLoad(){
		
	}
	public void onLoad(){
		
	}
	public void onStart(){
		
	}
	public void onClose(){
		
	}
	public void onStep(){
		
	}
	@Override
	public String toString(){
		return "{\n"+
			"  Name: \"" + name + "\",\n" + 
			"  Description: \"" + description + "\",\n" +
			"  Version: " + version + ",\n" +
			"  Functions: "+(functions.length!=0? Arrays.asList(functions): " Empty ") + ",\n" +
			"  Dependencies: " + (dependencies.length!=0? Arrays.asList(dependencies) : " Empty ") +
			"\n}";
	}
}
