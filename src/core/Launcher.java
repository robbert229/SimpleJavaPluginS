package core;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import core.plugin.Plugin;
import core.plugin.info.Dependency;

class Launcher {

	private static HashMap<String, Plugin> loadedPluginMap;
	private static ArrayList<String> loadedPluginKeys;
	private static ArrayList<Dependency> loadedPluginDependency;
	
	private static ArrayList<JarFile> jarList;
	private static ArrayList<URL> urlList;
	private static ClassLoader cl;

	public static void main(String... args) {
		loadJars();
		loadClasses();
		onload();
		depCheck();
		onStart();
	}

	public static void onload(){
		System.out.print("================================================================================");
		System.out.println("Starting onLoad Event");
		for(String key : loadedPluginKeys){
			loadedPluginMap.get(key).onLoad(loadedPluginMap);
			System.out.println("    "+key + " onLoaded()");
		}
		System.out.println("Completed onLoad Event");
	}

	public static void onStart(){
		System.out.print("================================================================================");
		System.out.println("Starting onStart Events");
		for(String key : loadedPluginKeys){
			loadedPluginMap.get(key).onStart();
			System.out.println("    "+key + " onStart()");
		}
		System.out.println("Completed onStart Events");
	}

	public static void depCheck(){
		System.out.print("================================================================================");
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

	private static void loadJars(){
		jarList = new ArrayList<JarFile>();
		urlList = new ArrayList<URL>();
		File file = new File("");	
		System.out.print("================================================================================");
		System.out.println("Starting loading the jars");
		for(File f : file.getAbsoluteFile().listFiles()){
			if(f.getAbsolutePath().endsWith(".jar") && !f.getAbsolutePath().contains("Plugin.jar")){				
				try {
					jarList.add(new JarFile(f.getPath()));
					urlList.add(new URL("jar:file:" + f.getPath()+"!/"));
					System.out.println("    " + f.getName() + " loaded succesfully");
				} catch (IOException e) {
					System.out.println("    " + f.getName() + " failed to load");
					e.printStackTrace();
				}
			}
		}
		URL[] urls = new URL[urlList.size()];
		urlList.toArray(urls);
        cl = URLClassLoader.newInstance(urls);
		System.out.println("Completed loading the jars");
	}
	@SuppressWarnings("unchecked")
	public static void loadClasses(){
		loadedPluginMap = new HashMap<String, Plugin>();
		loadedPluginKeys = new ArrayList<String>();
		loadedPluginDependency = new ArrayList<Dependency>();
		System.out.print("================================================================================");
		System.out.println("Starting load the classes from the jars");
		for(JarFile j : jarList){
			Enumeration<JarEntry> e = j.entries();
			while(e.hasMoreElements()){
				JarEntry je = (JarEntry)e.nextElement();
				if(je.isDirectory() || !je.getName().endsWith(".class")){
					continue;
				}
				else if(je.getName().contains("Core.class")){
					String className = je.getName().substring(0, je.getName().length()-6);
					className = className.replace("/", ".");
					
					try {
						Class<? extends Plugin> c = (Class<? extends Plugin>) cl.loadClass(className);
						Plugin plug = c.newInstance();
						loadedPluginMap.put(plug.getName(), plug);
						loadedPluginKeys.add(plug.getName());
						loadedPluginDependency.add(new Dependency(plug.getName(),plug.getVersion()));
						
						System.out.println("    "+plug.getName() + ".onPreloaded()");
						String jarName = "..." + (j.getName().substring(j.getName().lastIndexOf(".jar")-15,j.getName().length()));
						System.out.println("    Succesfully loaded : " + je.getName() + " in " + jarName);
						plug.onPreLoad();
						
					} catch (Exception e1) {
						System.out.println("Failed at Cast");
						e1.printStackTrace();
					}
					
				}
			}
		}
		System.out.println("Completed loading the classes form the jars");
	}
}