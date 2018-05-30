package puzzle;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.*;
	public class board extends JPanel implements ActionListener, KeyListener  {
      private JButton [][] matrix;
      private int [][] csv;
      private ImageIcon[] images;
      private JLabel steps;
      private int stepsI;
      private Timer time;
      private ImageIcon image;
      private Stack<int[]> unStuck;
      private JButton undo;
      private JLabel timer;
      private HashMap<Integer, LinkedList<int[][]>> csvLists;
      private HashMap<String, ImageIcon[][]> filesMap;
      private JButton menu;
      public board(int n, int [][] csv, ImageIcon[] images, JLabel steps, Timer time, JButton undo, JLabel timer,HashMap<Integer, LinkedList<int[][]>> csvLists, HashMap<String, ImageIcon[][]> filesMap, JButton menu)
      {
    	 super (new GridLayout(csv.length,csv.length));
    	 this.menu=menu;
    	 this.csvLists=csvLists;
    	 this.filesMap=filesMap;
    	 this.timer=timer;
    	 this.undo=undo;
    	 this.unStuck = new Stack<>(); 
    	 this.image=image;
    	 this.time=time;
    	 this.csv=csv;
    	 this.images=images;
    	 this.steps=steps;
    	 this.matrix= new JButton [n][n];
    	   for (int i =0; i<n;i++) {
    		 for (int j=0; j<n; j++) {
    			 int a= csv[i][j];
    			 matrix[i][j]=new JButton(images[a]);
    			 matrix[i][j].addActionListener(this);
    			 matrix[i][j].addKeyListener(this);
    			 matrix[i][j].setBorder(BorderFactory.createEmptyBorder());
    			 add(matrix[i][j]);
    			 matrix[i][j].setSize(100, 100);
    			 }
    	   }
    	  
      }


	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(menu)) {
			java.awt.Window wind[] = java.awt.Window.getWindows(); 
			for(int i=0;i<wind.length;i++){ 
			wind[i].dispose(); 
			}
			new mainMenu();
		}
		if (e.getSource().equals(undo)) {
			if (this.unStuck.empty()) {
				return;
			}
			else {
				int b[]= new int[2];
				int c[]= this.unStuck.pop();
				b[0]=c[0];  b[1]=c[1];
				swap(c[2], c[3], b);
			}
		}
			
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix.length; j++) {
				if( matrix[i][j].equals(e.getSource()) ) {
					int [] zero= checkZero(i,j);
					if (zero==null)
						return;
					stepsI++;
					steps.setText("steps: " + stepsI);
					swap(i, j, zero);
					int [] a = new int [4];
					a[0]=i; a[2]=zero[0];
					a[1]=j; a[3]=zero[1];
					this.unStuck.push(a);
					isComplete();
					
					return;
	   }	
	  }
	 }
	}
	public static void main(String[] args) {
		/*int [][] a= new int [2][2];
		a[0][0]=1;
		a[0][1]=3;
		a[1][0]=2;
		a[1][1]=0;
		ImageIcon[] image= new ImageIcon [4];
		image[0]= new ImageIcon( "sample_pictures" + "\\" + "cat" + "\\" + "cat_3x3" + "\\" + "0.jpeg");
		image[1]= new ImageIcon( "sample_pictures" + "\\" + "cat" + "\\" + "cat_3x3" + "\\" + "1.jpeg");
		image[2]= new ImageIcon( "sample_pictures" + "\\" + "cat" + "\\" + "cat_3x3" + "\\" + "2.jpeg");
		image[3]= new ImageIcon( "sample_pictures" + "\\" + "cat" + "\\" + "cat_3x3" + "\\" + "3.jpeg");
		new board(2,a, image);*/
		
	}
	
	
     private void swap (int i, int j, int[] a) {
    	 matrix[a[0]][a[1]].setIcon(matrix[i][j].getIcon());
    	 matrix[i][j].setIcon(null);
    	 int temp = csv[i][j];
    	 csv[i][j]=csv[a[0]][a[1]];
    	 csv[a[0]][a[1]]=temp; 
     }
	 private int[] checkZero(int i, int j) {
		 int [] ret = new int [2];
	     int [][] a = new int [csv.length+2][csv.length+2];
	     for (int m=0; m<a.length; m++) {
	    	 for (int p=0; p<a.length; p++) {
	    		 a[m][p]=-1;
	    	 }
	     }
	     for (int k=1; k<=csv.length; k++) {
	    	 for (int l=1; l<=csv.length; l++)
	    	 a[k][l]=csv[k-1][l-1];
	     }
	     if (a[i][j+1]==0) {
	    	 ret [0]=i-1;
	    	 ret[1]=j;
	     }
	     else if (a[i+1][j]==0) {
	    	 ret [0]=i;
	    	 ret[1]=j-1;
	     }
	     else if (a[i+1][j+2]==0) {
	    	 ret [0]=i;
	    	 ret[1]=j+1;
	     }
	     else if (a[i+2][j+1]==0) {
	    	 ret [0]=i+1;
	    	 ret[1]=j;
	     }
	     else {
	    	 ret=null;
	     }
	    return ret;	 
	 }
	 
	 private boolean isComplete() {
		 int counter=1;
		 for (int i =0; i<csv.length;i++) {
    		 for (int j=0; j<csv.length; j++) {
    			 if (csv[i][j]!=counter%(csv.length*csv.length))
    				 return false;
    			 counter++;
    		 }
	 }
		 matrix[matrix.length-1][matrix.length-1].setIcon(images[matrix.length*matrix.length]);
			for (int k=0; k<matrix.length; k++) {
				for (int m=0; m<matrix.length; m++) {
					matrix[k][m].removeActionListener(this);
					matrix[k][m].removeKeyListener(this);
				}
			}
			undo.removeActionListener(this);
		    menu.removeActionListener(this);
			time.stop();
			new win(timer.getText(), steps.getText(), csvLists, filesMap);
		 
		 return true;
	}



	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int [] a =new int [2];
		int temp=0;
		int col=0;
		int row=0;
		for (int i=0; i<csv.length;i++) {
			for (int j=0; j<csv.length; j++) {
				if (csv[i][j]==0) {
					row=i;
					col=j;
				}	
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP ){
			if (row==csv.length-1)
				return;
			else {
				a[0]=row; a[1]=col;
				swap(row+1, col, a);
				int [] b = new int [4];
				b[0]=row+1; b[2]=a[0];
				b[1]=col; b[3]=a[1];
				this.unStuck.push(b);
				stepsI++;
				steps.setText("steps: " + stepsI);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN ){
			if (row==0)
				return;
			else {
				a[0]=row; a[1]=col;
				swap(row-1, col, a);
				int [] b = new int [4];
				b[0]=row-1; b[2]=a[0];
				b[1]=col; b[3]=a[1];
				this.unStuck.push(b);
				stepsI++;
				steps.setText("steps: " + stepsI);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
			if (col==0)
				return;
			else {
				a[0]=row; a[1]=col;
				swap(row, col-1, a);
				int [] b = new int [4];
				b[0]=row; b[2]=a[0];
				b[1]=col-1; b[3]=a[1];
				this.unStuck.push(b);
				stepsI++;
				steps.setText("steps: " + stepsI);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT ){
			if (col==csv.length-1)
				return;
			else {
				a[0]=row; a[1]=col;
				swap(row, col+1, a);
				int [] b = new int [4];
				b[0]=row; b[2]=a[0];
				b[1]=col+1; b[3]=a[1];
				this.unStuck.push(b);
				stepsI++;
				steps.setText("steps: " + stepsI);
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
			if (this.unStuck.empty()) {
				return;
			}
			else {
				int b[]= new int[2];
				int c[]= this.unStuck.pop();
				b[0]=c[0];  b[1]=c[1];
				swap(c[2], c[3], b);
			}
		}
		isComplete();
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}

