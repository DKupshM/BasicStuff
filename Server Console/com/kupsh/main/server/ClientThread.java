package com.kupsh.main.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.JTextArea;

public class ClientThread implements Runnable {

	private Socket threadSocket;
	private JTextArea chatBox;
	private Server server;
	private BufferedReader input;
	private PrintWriter output;

	private int thread;
	
	private volatile boolean run = true;

	public ClientThread(Socket socket, JTextArea chatBox, Server server) {
		this.threadSocket = socket;
		this.chatBox = chatBox;
		this.server = server;

	}

	private void inputLoop() throws Exception {
		while (run) {
			String chatInput = input.readLine();
			if (chatInput.equals(null)) {
				input.close();
				output.close();
			} else {
				chatBox.append("[Client " + thread + "] Input: " + chatInput
						+ "\n");
				System.out.println(chatInput);
			}
		}
	}
	
	public void kill(){
		run = false;
	}

	public void run() {
		try {
			output = new PrintWriter(threadSocket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(
					threadSocket.getInputStream()));
			output.println("You have connected at: " + new Date());
			chatBox.append("Client connected at: " + new Date() + "\n");
			server.setThreads(server.getThreads() + 1);
			server.setActivethreads(server.getActivethreads() + 1);
			thread = server.getThreads();
			inputLoop();
		} catch (Exception e) {
			chatBox.append("Client Closed at: " + new Date() + "\n");
			run = false;
			server.setActivethreads(server.getActivethreads() - 1);
		}
	}
}
