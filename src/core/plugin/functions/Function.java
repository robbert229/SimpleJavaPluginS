package core.plugin.functions;

public abstract class Function implements IFunction{
	protected String name;
	protected String description;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public String toString(){
		return "{Name: " + getName() + ", Description: "+getDescription()+"}";
	}
}
