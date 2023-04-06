// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField rep = new JTextField(2);
    private JTextField rec = new JTextField(2);
    private JTextField ter = new JTextField(10);
    private JTextField tem = new JTextField(10);
    private JTextField w = new JTextField(20);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labrep = new JLabel(" Repetition (Sprint):");
    private JLabel labrec = new JLabel(" Recovery (Sprint):");
    private JLabel labter = new JLabel(" Terrain (Cycle):");
    private JLabel labtem = new JLabel(" Tempo (Cycle):");
    private JLabel labw = new JLabel(" Where (Swim):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find");
    private JButton cycle = new JButton("Cycle");
    private JButton sprint = new JButton("Sprint");
    private JButton swim = new JButton("Swim");
    private JButton remove = new JButton("Remove");

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labrep);
        add(rep);
        rep.setEditable(true);
        add(labrec);
        add(rec);
        rec.setEditable(true);
        add(labter);
        add(ter);
        ter.setEditable(true);
        add(labtem);
        add(tem);
        tem.setEditable(true);
        add(labw);
        add(w);
        w.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(remove);
        remove.addActionListener(this);
        add(cycle);
        cycle.addActionListener(this);
        add(sprint);
        sprint.addActionListener(this);
        add(swim);
        swim.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();
        

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        
        if (event.getSource() == findAllByDate) {
        	message = findAllEntry();
        }
        if (event.getSource() == cycle) {
        	message = addEntry("cycle");
        }
        if (event.getSource() == sprint) {
        	message = addEntry("sprint");
        }
        if (event.getSource() == swim) {
        	message = addEntry("swim");
        }
        if (event.getSource() == remove) {
        	message = remove();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
    	Boolean check = false;
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        
        check = myAthletes.check(n,d,m,y);
        if (check == true) {
        	message = "Record already exists";
        } else if (what.equals("sprint")) {
        	int rep2 = Integer.parseInt(rep.getText());
        	int rec2 = Integer.parseInt(rec.getText());
        	Entry e = new SprintEntry(n,d,m,y,h,mm,s,km,rep2,rec2);
        	myAthletes.addEntry(e);
        	message = "Sprint record added\n";
        } else if (what.equals("cycle")) {
        	String ter2 = ter.getText();
        	String tem2 = tem.getText();
        	Entry e = new CycleEntry(n,d,m,y,h,mm,s,km,ter2,tem2);
        	myAthletes.addEntry(e);
        	message = "Cycle record added\n";
        }else if (what.equals("swim")) {
        	String w2 = w.getText();
        	Entry e = new SwimEntry(n,d,m,y,h,mm,s,km,w2);
        	myAthletes.addEntry(e);
        	message = "Swim record added\n";
        }else {
        	Entry e = new Entry(n,d,m,y,h,mm,s,km);
        	myAthletes.addEntry(e);
        	message = "Generic record added\n";
        }
        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }
    
    public String findAllEntry() {
    	String message = "";
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Finding records ...");
        for (int i = 0; i< myAthletes.getNumberOfEntries(); i++) {
        	message = myAthletes.findAllEntry(d, m, y);
        }
        return message;
    }
    
    public String remove() {
    	String message = "";
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Removing record...");
        message = myAthletes.delete(n,d,m,y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

