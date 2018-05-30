package puzzle;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static java.awt.GridBagConstraints.*;
public class mainMenu extends JFrame implements ActionListener {
	public JButton btn1;
	public JButton btn2;
	public HashMap<Integer, LinkedList<int[][]>> csvLists;
	public HashMap<String, ImageIcon[][]> filesMap;
	public mainMenu() 
	{
		super("welcome");
		
		FlowLayout fl=new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		this.setLayout(fl);
		Image image = new ImageIcon("src" + "\\" + "back5.png").getImage().getScaledInstance(500, 500,  Image.SCALE_SMOOTH);
		ImageIcon im = new ImageIcon(image);
		setContentPane(new JLabel(im));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn1 = new JButton("start playing");
		btn1.addActionListener(this);
		btn2 = new JButton("exit");
		btn2.addActionListener(this);
		Container cp = getContentPane();
		  cp.add(btn1, BorderLayout.CENTER);
	        cp.add(btn2, BorderLayout.AFTER_LAST_LINE);
		cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
      
      //  cp.setBackground("sample_pictures" + "\\" + "cat" + "cat.jpeg");
        
        pack();
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        initCvs();
        initFiles();
	}
	
	public static void main(String[] args) {
		new mainMenu();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn1)) {
			new secondMenu(this.csvLists, this.filesMap);
			this.dispose();
		}
		else
			System.exit(0);
	}
	public void initFiles() {
	
		File files = new File ("sample_pictures");
		this.filesMap= new HashMap <String, ImageIcon[][]>();
		String[] folderNames= files.list();
		   for (int i=0; i<folderNames.length; i++) {
			   String a= folderNames[i];
			   File files1 = new File ("sample_pictures" + "\\" + folderNames[i]);
			   String[] folderNames1= files1.list();
			      for (int j=0; j<folderNames1.length; j++) {
			    	  if (folderNames1[j].endsWith(".jpeg") || folderNames1[j].endsWith(".jpg")) {
			    		   ImageIcon[][] images1 = new ImageIcon [26][26];
			    		   images1[0][0]=new ImageIcon("sample_pictures" + "\\" + folderNames[i] + "\\" + folderNames1[j] );
			    		   filesMap.put(a, images1);
			    	  }
			    	  else {
			    		  File files2 = new File ("sample_pictures" + "\\" + folderNames[i] + "\\" + folderNames1[j] );
			    		  String[] folderNames2= files2.list();
			    		  for (int k=0; folderNames2!=null && k<folderNames2.length; k++) {
			    			
			    		    filesMap.get(a)[j][k+1]=new ImageIcon("sample_pictures" + "\\" + folderNames[i] + "\\" + folderNames1[j]+ "\\" + folderNames2[k]);
			    		  }
			    	  }	  
			      }
		   }
		
	}
	public void initCvs() 
	{
		this.csvLists=new HashMap <Integer, LinkedList<int[][]>>();
		try {
			Scanner lines= new Scanner(new File ("boards.csv"));
			while (lines.hasNext()) {
				String next = lines.next();
				if (next.length()==1) {
					int size =Integer.parseInt(next);
					int [][] insert = new int [size][size];
					for (int i =0; i<size; i++) {
					   String next2=lines.next();
					   String [] a=next2.split(",");
					   for (int j=0; j<size; j++) {
						   insert [i][j]= Integer.parseInt(a[j]);
					   }
					}
					if (csvLists.containsKey(size))
					   csvLists.get(size).add(insert);
					else {
						LinkedList <int[][]> N = new LinkedList<int[][]>();
						N.add(insert);
						csvLists.put(size, N);
					}		
				}
			}
		}
		catch (FileNotFoundException e){
		System.out.println();	
		}
	}
	
}