package core.plugin.info;

public class Dependency implements Comparable<Dependency> {
	private String name;
	private Version version;

	public Dependency(String name, Version version) {
		this.name = name;
		this.version = version;
	}

	public Dependency(String name, int maj, int min) {
		this.name = name;
		this.version = new Version(maj, min);
	}

	public int compareTo(Dependency d) {
		if (name != d.getName())
			return (name.compareTo(d.getName()));
		else
			return (version.compareTo(d.getVersion()));
	}

	private boolean sameFamily(Dependency d) {
		return (name.compareTo(d.getName()) == 0);
	}

	public boolean isDescendent(Dependency d) {
		if (sameFamily(d)) {
			return (version.compareTo(d.getVersion()) >= 0);
		} else
			return false;
	}

	@SuppressWarnings("unused")
	private Dependency() {
		// Blocks dvc
	}

	public String getName() {
		return name;
	}

	public Version getVersion() {
		return version;
	}
	@Override
	public String toString(){
		return name + " - " + version;
	}
}
