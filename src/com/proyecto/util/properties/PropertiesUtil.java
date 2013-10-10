package com.proyecto.util.properties;


import java.util.Properties;

public class PropertiesUtil {
	
	public PropertiesUtil(){		
	}
	
	public static Properties loadProperties(String rutaProperties) {
		Properties props = new Properties();
		try {
			props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(rutaProperties));
		}
		catch (Exception e) {
			System.out.println("Error al leer properties: "+e);
			e.printStackTrace();
		}
		
		return props;
	}

}
