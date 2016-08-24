package com.kupsh.main.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	private boolean running;
	
	public static int Height = 480;
	public static int Width = 640;
	
	private Thread thread;
	private BufferedImageLoader loader;
	private LoadingScreen ls;
	
	private State state = State.loadingScreen;

	public void init(){
		System.out.println("Initialize Starting");
		//just add screens here
		
		
		//listeners for key and mouse
		this.addMouseListener(new MouseInput(this));
		this.addKeyListener(new KeyInput(this));
		System.out.println("Initialize Finished");
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, Width, Height);
		g.setColor(Color.black);
		
		//render all screens here
		if(state == State.loadingScreen)
			ls.render(g);
		
		g.dispose();
		bs.show();
	}
	public void tick(){
		//tick all screens here
		if(state == State.loadingScreen)
			ls.tick();
	}
	
	public void start(){
		if (running) {
			return;
		}
		running = true;
		Window window = new Window();
		window.window("Game", this);
		loader = new BufferedImageLoader();
		ls = new LoadingScreen(this, loader);
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 20.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		// Game loop
		// tick is 20 a second
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				delta--;
			}
			render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
