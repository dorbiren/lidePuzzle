package puzzle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.*;
public class game extends JFrame implements ActionListener{
	private board board;
    private JLabel timer;
    private JLabel steps;
    private Timer time;
    private int seconds;
    private ImageIcon image;
    private JButton undo;
    private JButton menu;
    
    
    public game(int n, int [][] csv, ImageIcon[] images,HashMap<Integer, LinkedList<int[][]>> csvLists, HashMap<String, ImageIcon[][]> filesMap)
    {
    	this.setLayout(new BorderLayout());
    	
    	JPanel upPanel=new JPanel();
    	upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	this.undo = new JButton("UNDO");
    	this.menu= new JButton("BACK TO MENU");
    	
    	steps=new JLabel("steps: 0");
    	timer=new JLabel();
    	upPanel.add(timer);
    	upPanel.add(steps);
    	upPanel.add(undo);
    	upPanel.add(menu);
    	time=new Timer(1000,this);
		time.start();
	
    	this.board = new board(n,csv,images, steps, time, undo, timer, csvLists, filesMap, menu);
    	undo.addActionListener(board);
    	menu.addActionListener(board);
    	undo.setFocusable(false);
    	menu.setFocusable(false);
    	this.add(board,BorderLayout.SOUTH);
    	this.add(upPanel, BorderLayout.NORTH);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setResizable(false);
		this.setVisible(true);
		pack();
    }
    
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		seconds++;
		String hour="";
		String minute="";
		String second="";	
		hour = hour + seconds/3600;
		hour = zeroCheck(hour);
		minute =minute + (seconds%3600)/60;
		minute = zeroCheck(minute);
		second = second+ seconds%60;
		second = zeroCheck(second);
		timer.setText(hour + ":" + minute +":" + second);
		
		
			
	}
	private String zeroCheck(String s){
		if(s.length() == 1) s = "0" +s;
		return s;
	}

}
