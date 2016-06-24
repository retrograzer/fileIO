package com.cooksys.ftd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (InputStream in = new FileInputStream("C:\\stupid-test-resources\\input.txt");
				OutputStream out = new FileOutputStream("C:\\stupid-test-resources\\output.txt");) {
			int c;
			while((c = in.read()) != -1) {
				out.write(c);
			}
		}
	}

}
