package core.plugin.info;

public class Version implements Comparable<Version> {
	private int minor;
	private int major;

	public Version(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	@SuppressWarnings("unused")
	private Version() {
	}

	public int getMinor() {
		return minor;
	}

	public int getMajor() {
		return major;
	}

	@Override
	public String toString() {
		return ("" + major + "." + minor + "");
	}

	public int compareTo(Version v) {
		if (this.getMajor() == v.getMajor())
			return (this.getMinor() - v.getMinor());
		else
			return (this.getMajor() - v.getMajor());
	}
}
