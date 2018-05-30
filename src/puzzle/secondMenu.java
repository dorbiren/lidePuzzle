package puzzle;

import javax.imageio.ImageIO;
import javax.swing.*;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.WEST;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

public class secondMenu extends JPanel implements ActionListener{
	private JRadioButton R3;
    private JRadioButton R4;
    private JRadioButton R5;
    private JButton pic1;
    private JButton pic2;
    private JButton pic3;
    private JButton Random;
    private JButton upload;
    private ButtonGroup tGrp;
    private HashMap<Integer, LinkedList<int[][]>> csvLists;
    private HashMap<String, ImageIcon[][]> filesMap;
    
    public secondMenu (HashMap<Integer, LinkedList<int[][]>> csvLists, HashMap<String, ImageIcon[][]> filesMap) {
    	super(new GridBagLayout());
    	this.csvLists=csvLists;
    	this.filesMap=filesMap;
		GridBagConstraints tProto = new GridBagConstraints();
		tProto.insets = new Insets(5,2,5,2);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		GridBagConstraints tLabelConst = (GridBagConstraints)tProto.clone();
		tLabelConst.anchor = EAST;
		tLabelConst.fill = NONE;
		tLabelConst.weightx = 0.0;

		GridBagConstraints tTextConst = (GridBagConstraints)tProto.clone();
		tTextConst.anchor = WEST;
		tTextConst.fill = HORIZONTAL;
		tTextConst.weightx = 1.0;

		GridBagConstraints tSelConst = (GridBagConstraints)tProto.clone();
		tSelConst.anchor = WEST;
		tSelConst.fill = NONE;
		tSelConst.weightx = 0.0;

		GridBagConstraints tConst = (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 0; tConst.gridy = 1;
		JPanel tPane = new JPanel();
		this.R3 = new JRadioButton("3X3");
		R3.setActionCommand("3");
		R3.addActionListener(this);
		this.R4 = new JRadioButton("4X4");
		R4.setActionCommand("4");
		R4.addActionListener(this);
		this.R5 = new JRadioButton("5X5");
		R5.setActionCommand("5");
		R5.addActionListener(this);
		R3.setSelected(true);
		tPane.add(R3);
		tPane.add(R4);
		tPane.add(R5);
		this.tGrp = new ButtonGroup();
		tGrp.add(R3);
		tGrp.add(R4);
		tGrp.add(R5);
		tConst = (GridBagConstraints)tSelConst.clone();
		tConst.gridx = 1; tConst.gridy = 1;
		add(tPane, tConst);
		tConst.fill = BOTH;
		tConst.anchor = CENTER;
		tConst.weighty = 1.0;
		tConst.gridx = 0; tConst.gridy = 3;
		tConst.gridwidth = 4; tConst.gridheight = 2;
		add(tPane, tConst);
		
		ImageIcon imageIcon_cat =filesMap.get("cat")[0][0];
		ImageIcon imageIcon_cyber =filesMap.get("cyber")[0][0];
		ImageIcon imageIcon_sushi =filesMap.get("sushi")[0][0];
		Image imagecat = imageIcon_cat.getImage();
		Image new_imgcat = imagecat.getScaledInstance(120, 120,  Image.SCALE_SMOOTH);
		ImageIcon Fimagecat = new ImageIcon(new_imgcat);
		Image imagecyber = imageIcon_cyber.getImage();
		Image new_imgcyber = imagecyber.getScaledInstance(120, 120,  Image.SCALE_SMOOTH);
		ImageIcon Fimagecyber = new ImageIcon(new_imgcyber);
		Image imagesushi = imageIcon_sushi.getImage();
		Image new_imgsushi = imagesushi.getScaledInstance(120, 120,  Image.SCALE_SMOOTH);
		ImageIcon Fimagesushi = new ImageIcon(new_imgsushi);
		this.pic1= new JButton(Fimagecat);
		pic1.setBorder(BorderFactory.createEmptyBorder());
		this.pic2= new JButton(Fimagecyber);
		pic2.setBorder(BorderFactory.createEmptyBorder());
		this.pic3= new JButton(Fimagesushi);
		pic3.setBorder(BorderFactory.createEmptyBorder());
		this.Random=new JButton("Choose Random"); 
		Random.setBorder(BorderFactory.createEmptyBorder());
		JPanel tPane2 = new JPanel();
        tConst = (GridBagConstraints) tSelConst.clone();
        tConst.gridx = 3;
        tConst.gridy =2;
        pic1.addActionListener(this);
        tPane2.add(pic1);
        pic2.addActionListener(this);
        tPane2.add(pic2);
        pic3.addActionListener(this);
        tPane2.add(pic3);
        Random.addActionListener(this);
        tPane2.add(Random);
        add(tPane2,tConst);
        
        
		Font tFont = new Font("Dialog", Font.BOLD, 16);
		UIManager.put("Label.font", tFont);
		UIManager.put("RadioButton.font", tFont);
		UIManager.put("TitledBorder.font", tFont);
		JFrame tFrame = new JFrame("Personal details");
		tFrame.setContentPane(this);
		tFrame.pack();
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tFrame.setResizable(false);
		tFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double random1 = Math.random();
		double random2 = Math.random();
		int rand1;
		String rand2;
		if (random1 >0.5)
			rand1=1;
		else
			rand1=0;
		if (random2<0.3)
		rand2="cat";
		else if(random2<0.6)
			rand2="cyber";
		else
			rand2="sushi";
		
		if (this.tGrp.getSelection().getActionCommand()== "3") {
			int [][] csv= csvLists.get(3).get(rand1);
			int [][] ret= changeCsv(csv);
			if (e.getSource().equals(pic1)) {
				ImageIcon[] images=cutImage(3,filesMap.get("cat")[0][0]);
				//ImageIcon[] images= filesMap.get("cat")[1];
				new game(3, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic2)) {
				ImageIcon[] images=cutImage(3,filesMap.get("cyber")[0][0]);
				//ImageIcon[] images= filesMap.get("cyber")[1];
				new game(3, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic3)) {
				ImageIcon[] images=cutImage(3,filesMap.get("sushi")[0][0]);
				//ImageIcon[] images= filesMap.get("sushi")[1];
				for (int i=1 ; i<=9; i++) {
					Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
					images[i].setImage(image);
				}
				new game(3, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(Random)) {
				ImageIcon[] images=cutImage(3,filesMap.get(rand2)[0][0]);
				//ImageIcon[] images= filesMap.get(rand2)[1];
				if (rand2=="sushi") {
					for (int i=1 ; i<=9; i++) {
						Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
						images[i].setImage(image);
					}
				}
				new game(3, ret, images, csvLists, filesMap);
			}
			
		}
		else if (this.tGrp.getSelection().getActionCommand()== "4") {
			int [][] csv= csvLists.get(4).get(rand1);
			int [][] ret= changeCsv(csv);
			if (e.getSource().equals(pic1)) {
				ImageIcon[] images=cutImage(4,filesMap.get("cat")[0][0]);
				//ImageIcon[] images= filesMap.get("cat")[2];
				new game(4, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic2)) {
				ImageIcon[] images=cutImage(4,filesMap.get("cyber")[0][0]);
				//ImageIcon[] images= filesMap.get("cyber")[2];
				new game(4, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic3)) {
				ImageIcon[] images=cutImage(4,filesMap.get("sushi")[0][0]);
				//ImageIcon[] images= filesMap.get("sushi")[2];
				for (int i=1 ; i<=16; i++) {
					Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
					images[i].setImage(image);
				}
				new game(4, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(Random)) {
				ImageIcon[] images=cutImage(4,filesMap.get(rand2)[0][0]);
				//ImageIcon[] images= filesMap.get(rand2)[2];
				if (rand2=="sushi") {
					for (int i=1 ; i<=16; i++) {
						Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
						images[i].setImage(image);
					}
				}
				new game(4, ret, images, csvLists, filesMap);
			}
			
		}
		else if (this.tGrp.getSelection().getActionCommand()== "5") {
			int [][] csv= csvLists.get(5).get(rand1);
			int [][] ret= changeCsv(csv);
			if (e.getSource().equals(pic1)) {
				ImageIcon[] images=cutImage(5,filesMap.get("cat")[0][0]);
				//ImageIcon[] images= filesMap.get("cat")[3];
				new game(5, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic2)) {
				ImageIcon[] images=cutImage(5,filesMap.get("cyber")[0][0]);
				//ImageIcon[] images= filesMap.get("cyber")[3];
				new game(5, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(pic3)) {
				ImageIcon[] images=cutImage(5,filesMap.get("sushi")[0][0]);
				//ImageIcon[] images= filesMap.get("sushi")[3];
				for (int i=1 ; i<=25; i++) {
					Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
					images[i].setImage(image);
				}
				new game(5, ret, images, csvLists, filesMap);
			}
			else if (e.getSource().equals(Random)) {
				ImageIcon[] images=cutImage(5,filesMap.get(rand2)[0][0]);
				//ImageIcon[] images= filesMap.get(rand2)[3];
				if (rand2=="sushi") {
					for (int i=1 ; i<=25; i++) {
						Image image = images[i].getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
						images[i].setImage(image);
					}
				}
				new game(5, ret, images, csvLists, filesMap);
			}
		}
		
	}
	
		private ImageIcon[] cutImage(int size, ImageIcon image) {
			Image image1 = image.getImage();
			BufferedImage buff = toBufferedImage(image1);
			ImageIcon[] images=new ImageIcon[size*size+1];
			int counter=0;
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
				   counter++;
				BufferedImage cutImage = buff.getSubimage((j)*buff.getWidth()/size ,(i)*buff.getHeight()/size, buff.getWidth()/size, buff.getHeight()/size);
			    images[counter] = new ImageIcon(cutImage);
				}
			}
			return images;
		}
		
		public static BufferedImage toBufferedImage(Image img)
		{
		    if (img instanceof BufferedImage)
		    {
		        return (BufferedImage) img;
		    }

		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();

		    // Return the buffered image
		    return bimage;
		}
		
		private int [][] changeCsv(int [][] csv){
			int [][] ret= new int[csv.length][csv.length];
			for (int i=0; i<csv.length; i++) {
				for (int j=0; j<csv.length; j++) {
					ret[i][j]=csv[i][j];
				}
			}
			return ret;
		}
	}

