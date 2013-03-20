package core.plugin;

import java.util.HashMap;

import core.plugin.functions.IFunction;
import core.plugin.info.Dependency;
import core.plugin.info.Version;

interface IPlugin {
	public boolean hasError(Dependency[] d);
	
	public void setName(String name);

	public void setDescription(String description);

	public void setVersion(int maj, int min);

	public void setFunction(IFunction[] functions);

	public void setFunction(IFunction function, int i);

	public void setDependencies(Dependency[] dependencies);
	
	public void setLoadedPluginMap(HashMap<String, Plugin> loadedPluginMap);
	
	public String getName();

	public String getDescription();

	public Version getVersion();

	public IFunction[] getFunction();

	public IFunction getFunction(int i);
	
	public HashMap<String, Plugin> getLoadedPluginMap();
	
	public void onPreLoad();
	
	public void onLoad(HashMap<String, Plugin> loadedPluginMap);
	
	public void onStart();
	
	public void onStep();
	
	public void onClose();
}
