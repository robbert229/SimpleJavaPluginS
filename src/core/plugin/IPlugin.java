package core.plugin;

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

	public String getName();

	public String getDescription();

	public Version getVersion();

	public IFunction[] getFunction();

	public IFunction getFunction(int i);
	
	public void onPreLoad();
	
	public void onLoad();
	
	public void onStart();
	
	public void onStep();
	
	public void onClose();
}
