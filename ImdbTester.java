import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.common.collect.*;

public class ImdbTester extends JFrame implements ActionListener
{
	ArrayList<String> movieNames;
	ArrayList<ImageIcon> movieImages;
	ImdbSearch test;
	JButton enter = new JButton("Enter");
	JTextField movienamebox;
	String moviename;
	JPanel firstPanel;
	
	
	public ImdbTester()
	{
		try{
			setSize(600, 600);
	      	setTitle("ImdbTester");
	      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      	
	      	movienamebox = new JTextField(20);
			enter.addActionListener(this);
			moviename = "";
			
			firstPanel = new JPanel();
			firstPanel.add(movienamebox);
			firstPanel.add(enter);
			
			add(firstPanel);
			
			setVisible(true);
		} catch(Exception e){
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource().equals(enter)){
				moviename = movienamebox.getText();
				test = new ImdbSearch(moviename);
	      		
	      		remove(firstPanel);
	      		
		        movieImages = test.getImages();
		        movieNames = test.getMovieNames();
		      	
		      	JPanel panel = new JPanel();
		      	//panel.setLayout(new BorderLayout(movieNames.size(),2));
		      	panel.setLayout(new GridBagLayout());
		      	GridBagConstraints c = new GridBagConstraints();
		      	
		      	ArrayList<MovieItem> results = new ArrayList<MovieItem>();

				for(int i = 0; i < movieNames.size(); i++){
					String name = movieNames.get(i);
					ImageIcon image = movieImages.get(i);
					results.add(new MovieItem(name,1995,"genre",image));
				}
				
				int row = 0;
		      	for(MovieItem movie:results)
		      	{
		      		ImdbPageInfo x = new ImdbPageInfo(test.getURLs().get(row));
		      		JButton b = new JButton(x.movieImage);
					b.addActionListener(this);
					
					b.setPreferredSize(new Dimension(182,268));
					c.weightx = 0.5;
					c.gridx = 0;
					c.gridy = row;
			      	panel.add(b,c);
			      	
			      	c.gridx = 1;
			      	panel.add(new JLabel(x.movieName),c);
			      	row++;
		      	}
		      	JScrollPane jp = new JScrollPane(panel);
		      	add(jp);
		      	repaint();
		      	setVisible(true);
			} else {
				for(int i = 0; i < movieNames.size(); i++){
					String name = movieNames.get(i);
					ImageIcon image = movieImages.get(i);
					
					 if(((JButton) e.getSource()).getIcon().equals(movieImages.get(i))){
					 	ImdbPageInfo x = new ImdbPageInfo(test.getURLs().get(i));
					 	System.out.println ("Name: "+x.movieName);
					 	System.out.println ("Genre: "+x.movieGenre);
					 	System.out.println ("Length: "+x.movieLength);
						System.out.println ("Rating: "+x.movieRating);
					 }
				}
			}
		} catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static void main(String[] args)
    {
	   	new ImdbTester();
    }
}