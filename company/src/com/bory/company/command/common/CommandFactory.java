package com.bory.company.command.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletException;

public class CommandFactory {
	private final String commandPropertiesFilePath;
	private Map<String, Class<Command>> commandMap = new HashMap<>();
	
	public CommandFactory(String commandPropertiesFilePath) {
		this.commandPropertiesFilePath = commandPropertiesFilePath;
	}
	
	public Command createCommand(String uri) {
		try {
			return commandMap.get(uri).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
	
	public void init() throws ServletException{
		Properties properties = loadProperties();
		Iterator<Entry<Object, Object>> entries = properties.entrySet().iterator();
		loadCommands(entries);
	}

	@SuppressWarnings("unchecked")
	private void loadCommands(Iterator<Entry<Object, Object>> entries) throws ServletException {
		while(entries.hasNext()) {
			Entry<Object, Object> entry = entries.next();
			String uri = (String)entry.getKey();
			String commandName = (String)entry.getValue();
			
			Class<Command> commandClass;
			try {
				commandClass = (Class<Command>)this.getClass().getClassLoader().loadClass(commandName);
				commandMap.put(uri, commandClass);
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			}
		}
	}

	private Properties loadProperties() throws ServletException {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File(commandPropertiesFilePath)));
		} catch (IOException e) {
			throw new ServletException(e);
		}
		return properties;
	}
}
