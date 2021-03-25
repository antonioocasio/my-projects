package com.company;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
    	// creates new frame (window)
	    JFrame frame = new JFrame();

		// sets title of frame
	    frame.setTitle("GUI App");

		// sets x and y dimension
	    frame.setSize(500, 500);

		// exits out of app
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// prevent frame from being resized
		frame.setResizable(false);



		// makes frame visible
		frame.setVisible(true);

    }
}
