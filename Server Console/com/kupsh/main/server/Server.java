package com.kupsh.main.server;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JTextArea;

public class Server {

	private Window window;
	private ClientThread cT;
	private Command c;

	private JTextArea chatBox;

	private int port = 5000;
	private int threads = 0;
	private int activethreads = 0;

	public static void main(String[] args) {
		Server server = new Server();
		server.newServer();
	}

	public void newServer() {
		window = new Window(this);
		window.window();
		this.chatBox = window.getChatBox();
		start();
	}

	private void ServerLoop(ServerSocket sSocket) throws Exception {
		while (true) {
			Socket socket = sSocket.accept();
			cT = new ClientThread(socket, chatBox, this);
			new Thread(cT).start();
			chatBox.append("Number of Clients Connected: " + activethreads
					+ "\n");
		}
	}

	public void textInput(String userInput) {
		String[] words = userInput.split(" ");
		String[] params = new String[words.length - 1];
		for (int i = 1; i <= words.length - 1; i++) {
			params[i - 1] = words[i];
		}
		try {
			Class<Command> a = Command.class;
			Method method = a.getMethod(words[0], params.getClass());
			method.invoke(c, (Object) params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start() {
		try {
			ServerSocket sSocket = new ServerSocket(port);
			chatBox.append("Server started at: " + new Date() + "\n");
			chatBox.append("Server Address: " + sSocket.getLocalSocketAddress()
					+ "\n");
			c = new Command(this);
			ServerLoop(sSocket);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.exit(1);
		}
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getActivethreads() {
		return activethreads;
	}

	public void setActivethreads(int activethreads) {
		this.activethreads = activethreads;
	}
}
