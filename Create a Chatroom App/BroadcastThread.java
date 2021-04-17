// @authors - Zhouling Shen & Tiffany Taghvaiee

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.StreamHandler;

public class BroadcastThread implements Runnable {
	public void run() {
		while (true) {
			try { 
				Thread.sleep(100); 
			} 
			catch (InterruptedException ignore) { 

			}
		}
	}
} 
