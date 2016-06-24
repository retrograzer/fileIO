package com.cooksys.ftd.io;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Server server = new Server(667);

		new Thread(server).start();

		List<Thread> clientThreads = new ArrayList<Thread>();

		clientThreads.add(new Thread(new Client("localhost", 667, "hello, world!")));
		clientThreads.add(new Thread(new Client("localhost", 667, "I hate hello world examples")));
		clientThreads.add(new Thread(new Client("localhost", 667, "don't mind me", "I'm just hacking ur cat pics")));
		clientThreads.add(new Thread(new Client("localhost", 667, "this example's getting weeeeeird")));
		clientThreads.add(new Thread(new Client("localhost", 667, "and it's over", "or is it?")));

		for (Thread t : clientThreads) {
			t.start();
		}

		for (Thread t : clientThreads) {
			t.join();
		}

		System.exit(0);
	}

}
