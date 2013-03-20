package core;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import core.plugin.Plugin;
import core.plugin.info.Dependency;
import core.plugin.info.Version;

class Launcher {
	
	private static HashMap<String, Plugin> loadedPluginMap;
	private static ArrayList<String> loadedPluginKeys;
	private static ArrayList<Dependency> loadedPluginDependency;
	private static ClassLoader cl;
	private static String[] can;
	
	public static void main(String... args) {
		loadPlugins();
		onload();
		depCheck();
		onStart();
	}
	
	public static void onload(){
		System.out.println("=========================================");
		System.out.println("Starting onLoad Event");
		for(String key : loadedPluginKeys){
			loadedPluginMap.get(key).onLoad(loadedPluginMap);
			System.out.println("    "+key + " onLoaded()");
		}
		System.out.println("Completed onLoad Event");
	}
	
	public static void onStart(){
		System.out.println("=========================================");
		System.out.println("Starting onStart Events");
		for(String key : loadedPluginKeys){
			loadedPluginMap.get(key).onStart();
			System.out.println("    "+key + " onStart()");
		}
		System.out.println("Completed onStart Events");
	}
	
	public static void depCheck(){
		System.out.println("=========================================");
		System.out.println("Starting Dependency Check");
		Object[] depRaw = loadedPluginDependency.toArray();
		Dependency[] dependencies = new Dependency[depRaw.length];
		for(int i=0;i<depRaw.length;i++)
			dependencies[i] = (Dependency)depRaw[i];
				
		for(String key : loadedPluginKeys ){
			Plugin pl = loadedPluginMap.get(key);
			if(pl.hasError(dependencies))
			{
				System.out.println("    Plugin : " + key +" was removed, Dependency Conflict");
				System.out.println("    "+key + " Dependencies :" + Arrays.toString(pl.getDependencies()));
				System.out.println("    Plugins installed : " + Arrays.toString(dependencies));
				
				loadedPluginMap.remove(key);
				loadedPluginKeys.remove(key);
			}
			else
				System.out.println("    "+key + " passed dependency checks");
		}
		System.out.println("Completed Dependency Check");
	}
	
	@SuppressWarnings("unchecked")
	public static void loadPlugins(){
		System.out.println("=========================================");
		
		loadedPluginMap = new HashMap<String, Plugin>();
		loadedPluginKeys = new ArrayList<String>();
		loadedPluginDependency = new ArrayList<Dependency>();

		cl = ClassLoader.getSystemClassLoader();
		can = FileControl.getClassName();
		
		System.out.println("Starting loadPlugins");
		
		for (int i = 0; i < can.length; i++) {
			System.out.println("    Attempting to load : " + can[i] + ".Core");
			try {
				Class<? extends Plugin> plug = (Class<? extends Plugin>) cl.loadClass(can[i] + ".Core");
				Constructor<? extends Plugin> ctor = plug.getConstructor();
				
				Plugin eplug = ctor.newInstance();
				String name = eplug.getName();
				Version version = eplug.getVersion();
				
				loadedPluginMap.put(name, eplug);
				loadedPluginKeys.add(name);
				loadedPluginDependency.add(new Dependency(name,version));
				
				System.out.println("    "+name + ".onPreloaded()");
				System.out.println("    Succesfully loaded : " + can[i] + ".Core");
				eplug.onPreLoad();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("    Failed to load : " + can[i] + ".Core");
			}
		}
		System.out.println("Completed loadPlugins");
	}

	
	
	
}