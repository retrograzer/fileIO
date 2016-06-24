package com.cooksys.ftd.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleConnectionServer implements Runnable {

	private Logger log = LoggerFactory.getLogger(SingleConnectionServer.class);

	private int port;

	public SingleConnectionServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(this.port); Socket clientSocket = serverSocket.accept();) {
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			output.writeUTF("Hello you've won! You're the 1,000,000th visitor to my socket.");
		} catch (IOException e) {
			log.error("server failed! oh noes :(", e);
		}
	}

	public static void main(String[] args) {
		SingleConnectionServer server = new SingleConnectionServer(667);
		SingleConnectionClient client = new SingleConnectionClient("10.160.64.36", 667);
		
		new Thread(server).start();
		new Thread(client).start();
	}

}
