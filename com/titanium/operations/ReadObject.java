package com.titanium.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	Properties p = new Properties();
	
	public Properties getObjectRepository() throws IOException {
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir") + "\\resources\\object.properties"));
		p.load(stream);
		return p;
	}
}
