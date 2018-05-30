package puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class win extends JFrame implements ActionListener {
    private JLabel winner;
    private String timer;
    private String steps;
    private JButton mainMenu;
    private JButton newGame;
    private HashMap<Integer, LinkedList<int[][]>> csvLists;
    private HashMap<String, ImageIcon[][]> filesMap;
    public win( String timer, String steps,HashMap<Integer, LinkedList<int[][]>> csvLists, HashMap<String, ImageIcon[][]> filesMap){
    	this.csvLists=csvLists;
    	this.filesMap=filesMap;
    	this.timer=timer;
    	this.steps=steps;
    	Image image = new ImageIcon("src" + "\\" + "back4.jpg").getImage().getScaledInstance(200, 200,  Image.SCALE_SMOOTH);
		ImageIcon im = new ImageIcon(image);
		setContentPane(new JLabel(im));
    	FlowLayout fl=new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		this.setLayout(fl);
	
    	this.winner=new JLabel("you win!!");
    	winner.setForeground(Color.WHITE);
    	Container cp = getContentPane();
    	cp.add(winner, BorderLayout.NORTH);
    	JLabel T=new JLabel("time: " + timer);
    	T.setForeground(Color.WHITE);
    	JLabel S=new JLabel(steps);
    	S.setForeground(Color.WHITE);
    	cp.add(T, BorderLayout.CENTER);
    	cp.add(S, BorderLayout.SOUTH);
    	this.mainMenu= new JButton("main menu");
    	mainMenu.addActionListener(this);
    	
    	this.newGame= new JButton("new game");
    	newGame.addActionListener(this);
    	
    	JPanel downPanel=new JPanel();
    	downPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	downPanel.add(mainMenu);
    	downPanel.add(newGame);
    	this.add(downPanel, BorderLayout.SOUTH);
        pack();
        setSize(200, 200);
        setResizable(false);
        setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		java.awt.Window wind[] = java.awt.Window.getWindows(); 
		for(int i=0;i<wind.length;i++){ 
		wind[i].dispose(); 
		}
		if (e.getSource().equals(mainMenu)) 
			new mainMenu();
		else 
			new secondMenu(csvLists, filesMap);
		

}
}
