package core.plugin;

import java.util.Arrays;
import java.util.HashMap;

import core.plugin.functions.IFunction;
import core.plugin.info.Dependency;
import core.plugin.info.Version;

@SuppressWarnings("unused")
public abstract class Plugin implements IPlugin {
	protected String name;
	protected String description;
	protected Version version;
	protected IFunction[] functions;
	protected Dependency[] dependencies;
	protected HashMap<String, Plugin> loadedPluginMap;

	public Dependency[] getDependencies() {
		return this.dependencies;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public IFunction[] getFunction() {
		return functions;
	}

	@Override
	public IFunction getFunction(int i) {
		return functions[i];
	}
	
	@Override
	public HashMap<String, Plugin> getLoadedPluginMap() {
		return loadedPluginMap;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Version getVersion() {
		return version;
	}

	@Override
	public boolean hasError(Dependency[] dependency) {
		return false;
	}

	@Override
	public void onClose() {

	}

	@Override
	public void onLoad(HashMap<String, Plugin> loadedPluginMap) {
		this.loadedPluginMap = loadedPluginMap;
	}

	@Override
	public void onPreLoad() {

	}

	@Override
	public void onStart() {

	}

	@Override
	public void onStep() {

	}

	@Override
	public void setDependencies(Dependency[] dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setFunction(IFunction function, int i) {
		this.functions[i] = function;
	}

	@Override
	public void setFunction(IFunction[] functions) {
		this.functions = functions;
	}

	@Override
	public void setLoadedPluginMap(HashMap<String, Plugin> loadedPluginMap) {
		this.loadedPluginMap = loadedPluginMap;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setVersion(int maj, int min) {
		this.version = new Version(maj, min);
	}

	@Override
	public String toString() {
		return "{\n"
				+ "  Name: \""
				+ name
				+ "\",\n"
				+ "  Description: \""
				+ description
				+ "\",\n"
				+ "  Version: "
				+ version
				+ ",\n"
				+ "  Functions: "
				+ (functions.length != 0 ? Arrays.asList(functions) : " Empty ")
				+ ",\n"
				+ "  Dependencies: "
				+ (dependencies.length != 0 ? Arrays.asList(dependencies)
						: " Empty ") + "\n}";
	}
}
