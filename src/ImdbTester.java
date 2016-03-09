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
	ImdbPageInfo x;
	
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
	      		x = new ImdbPageInfo(test.getURLs());
	      		
	      		remove(firstPanel);
	      		
		        movieImages = test.getImages();
		        movieNames = test.getMovieNames();
		      	
		      	JPanel panel = new JPanel();
		      	panel.setLayout(new GridBagLayout());
		      	GridBagConstraints c = new GridBagConstraints();
		      	
		      	ArrayList<MovieItem> results = new ArrayList<MovieItem>();

				for(int i = 0; i < movieNames.size(); i++){
					String name = movieNames.get(i);
					ImageIcon image = movieImages.get(i);
					results.add(new MovieItem(name,1995,"genre",image));
				}
				x = new ImdbPageInfo(test.getURLs());
				int row = 0;
		      	for(MovieItem movie:results)
		      	{
		      		JButton b = new JButton(x.movieImages.get(row));
					b.addActionListener(this);
					b.setPreferredSize(new Dimension(182,268));
					c.weightx = 0.5;
					c.gridx = 0;
					c.gridy = row;
			      	panel.add(b,c);
			      	
			      	c.gridx = 1;
			      	panel.add(new JLabel(x.movieNames.get(row)),c);
			      	row++;
		      	}
		      	JScrollPane jp = new JScrollPane(panel);
		      	add(jp);
		      	repaint();
		      	setVisible(true);
			} else {
				for(int i = 0; i < movieNames.size(); i++){
					if(((JButton) e.getSource()).getIcon().equals(x.movieImages.get(i))){
					 	System.out.println (x.getMovie(i));
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