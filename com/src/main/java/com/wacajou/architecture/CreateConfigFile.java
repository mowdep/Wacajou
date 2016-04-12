package com.wacajou.architecture;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

import com.wacajou.WacajouApplication;

public class CreateConfigFile {

	private String path;

	public CreateConfigFile() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			System.out.println("Chemin d'installation des fichiers ?");
			Scanner user_input = new Scanner(System.in);
			String path = user_input.next();
			prop.setProperty("PATH", path);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void getFile() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "config.properties";
			input = new FileInputStream("config.properties");
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property value and print it out
			System.out.println(path = prop.getProperty("PATH"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

}
