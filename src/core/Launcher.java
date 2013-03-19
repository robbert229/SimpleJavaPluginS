package core;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import core.plugin.Plugin;

class Launcher {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		HashMap<String, Plugin> loadedPluginMap = new HashMap<String, Plugin>();
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		String[] can = FileControl.getClassName();

		System.out.println("Staring to Load Plugins");
		for (int i = 0; i < can.length; i++) {
			System.out.println("Attempting to load : " + can[i] + ".Core");
			try {
				Class<? extends Plugin> plug = (Class<? extends Plugin>) cl.loadClass(can[i] + ".Core");
				Constructor<? extends Plugin> ctor = plug.getConstructor();
				Plugin eplug = ctor.newInstance();
				String name = eplug.getName();
				System.out.println("Succesfully loaded : " + can[i] + ".Core");
				loadedPluginMap.put(name, eplug);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to load : " + can[i] + ".Core");
			}
		}
		System.out.println("Starting Dependency Check");
	}
}