package com.shpp.cs.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * This program  implements the viewer for
 * the baby-name database described in the assignment handout.
 */


import com.shpp.cs.a.simple.SimpleProgram;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    private JButton graphButton;
    private JButton clearButton;
    private JTextField nameField;
    private final static int NAME_FIELD_WIDTH = 30;
    private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
    private NameSurferGraph graph;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        //add button "delete"
        JButton deleteButton = new JButton("Delete");
        add(deleteButton, NORTH);
        //add text label
        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel, NORTH);
        //add name field
        nameField = new JTextField(NAME_FIELD_WIDTH);
        nameField.setActionCommand("EnterPressed");
        nameField.addActionListener(this);
        add(nameField, NORTH);
        //add button "graph"
        graphButton = new JButton("Graph");
        add(graphButton, NORTH);
        //add button "clear"
        clearButton = new JButton("Clear");
        add(clearButton, NORTH);
        //listen to mouse commands
        addActionListeners();
        //add empty graph at beginning
        graph = new NameSurferGraph();
        add(graph);
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if(cmd.equals("EnterPressed")){
            //if enter pressed show graph for entered name
           NameSurferEntry entry = dataBase.findEntry(nameField.getText());
           graph.addEntry(entry);

        } else if (e.getSource() == clearButton){
            //clear all graphs
            graph.clear();
        }
        else if (e.getSource() == graphButton){
            //if button "graph" pressed show graph for entered name
            NameSurferEntry entry = dataBase.findEntry(nameField.getText());
            graph.addEntry(entry);
        }
        else {
            System.out.println("pressed");
            graph.deleteName(nameField.getText());

        }
    }
}
