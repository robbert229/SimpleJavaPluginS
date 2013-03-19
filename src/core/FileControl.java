package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileControl {
	private static ArrayList<File> getDir() {
		ArrayList<File> res = new ArrayList<File>();
		File dir = new File("bin/plugins");
		File listDir[] = dir.listFiles();
		for (int i = 0; i < listDir.length; i++)
			if (listDir[i].isDirectory())
				res.add(listDir[i]);
		return res;
	}

	private static File[] getDir(boolean v) throws IOException {
		ArrayList<File> fi = getDir();
		File[] f = new File[fi.size()];
		for (int i = 0; i < f.length; i++)
			f[i] = fi.get(i);
		return f;
	}

	public static String[] getClassName() {
		try {
			File[] files = getDir(true);

			String[] res = new String[files.length];
			for (int i = 0; i < files.length; i++)
				res[i] = (files[i].getPath().substring(4, files[i].getPath().length())).replace("\\", ".");

			return res;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
