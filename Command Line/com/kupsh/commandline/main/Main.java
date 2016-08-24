package com.kupsh.commandline.main;

import java.lang.reflect.Method;

import javax.swing.JTextArea;

public class Main {
	
	//basic command line program
	//add all functions in command class
	
	private JTextArea textBox;
	private Command c;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start(){
		Window window = new Window(this);
		window.window();
		this.textBox = window.getChatBox();
		c = new Command(this);
	}
	
	public void textInput(String userInput) {
		textBox.append(userInput + "\n");
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
}
