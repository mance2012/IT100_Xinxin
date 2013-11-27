/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		//Give the input position of name
	    add(new JLabel("name"),SOUTH);
	    firstName = new JTextField(20);
	    firstName.addActionListener(this);
	    add(firstName,SOUTH);
	    
	    //Add button
	    graphButton = new JButton("Graph");
	    add(graphButton,SOUTH);
	    
	    //clear button
	    graphButton = new JButton("Clear");
	    add(graphButton, SOUTH);
	    
	    //Get the Graph of NameSurferGraph
	    graph = new NameSurferGraph();
	    add(graph);
	    addActionListeners(this);
	    
	    //Read in data of names
	    database = new NameSurferDataBase("names-data.txt");
	    
	    
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == firstName || e.getActionCommand().equals("Graph")) {
			NameSurferEntry nse = database.findEntry(firstName.getText());
			if(nse != null) {
				graph.addEntry(nse);
			}
		} else if(e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	private JTextField firstName;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferDataBase database;
    private NameSurferGraph graph;
}
