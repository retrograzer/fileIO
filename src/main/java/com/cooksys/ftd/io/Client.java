package com.cooksys.ftd.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements Runnable {

	private Logger log = LoggerFactory.getLogger(Client.class);

	private String hostname;
	private int port;
	private String[] messages;
	private CopyFile file;
	private boolean isFile;
	
	public Client(String hostname, int port, CopyFile file) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.file = file;
		isFile = true;
	}

	public Client(String hostname, int port, String... messages) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.messages = messages;
		isFile = false;
	}

	@Override
	public void run() {
		try (Socket server = new Socket(this.hostname, this.port);
				BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
				PrintWriter writer = new PrintWriter(server.getOutputStream(), true);) {
			
			// send a bunch of messages and read the responses
			if (isFile) {
				log.info("Sending Server file:");
				//CopyFile.main();
				//log.info("..." + reader.readLine());
			} else {
				for (String message : this.messages) {
					log.info("Sending server message: {}", message);
					writer.println(message);
					log.info("Server responded with message: {}", reader.readLine());
				}
			}
			
		} catch (IOException e) {
			log.error("client fail! oh noes :(", e);
		}
	}

}
