package com.cooksys.ftd.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleConnectionClient implements Runnable {

	private Logger log = LoggerFactory.getLogger(SingleConnectionClient.class);

	private String hostname;
	private int port;

	public SingleConnectionClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	@Override
	public void run() {
		try (Socket socket = new Socket(this.hostname, this.port)) {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			log.info("server said: {}", input.readUTF());
		} catch (IOException e) {
			log.error("client failed! oh noes :(", e);
		}
	}

}
