package org.bearfly.ewords.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigUtils {
	private static Logger logger = LogManager.getLogger(ConfigUtils.class.getName());
	private static Properties prop;
	private static String configFileName = "Application.properties";
	static {
		prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream(configFileName);
		try {
			logger.info(String.format("load properties from %s...", configFileName));
			prop.load(stream);
		} catch (IOException e) {
		    logger.error("load config file error!", e);
		}
	}
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
