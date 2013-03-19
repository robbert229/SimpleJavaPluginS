package core.plugin.functions;

public interface IFunction {
	public <T,X> T run(X x);

	public String getName();

	public String getDescription();
}
