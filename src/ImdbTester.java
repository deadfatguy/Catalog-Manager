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
	ImdbSearch imdbSearchResults;
	JButton enter = new JButton("Enter");
	JTextField movieNameBox;
	String movieName;
	JPanel mainPagePanel;
	ImdbPageInfo movieInformationResults;
	
	public ImdbTester()
	{
		try{
			setSize(600, 600);
	      	setTitle("ImdbTester");
	      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      	
	      	movieNameBox = new JTextField(20);
			enter.addActionListener(this);
			movieName = "";
			
			mainPagePanel = new JPanel();
			mainPagePanel.add(movieNameBox);
			mainPagePanel.add(enter);
			
			add(mainPagePanel);
			
			setVisible(true);
		} catch(Exception e){
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource().equals(enter)){
				movieName = movieNameBox.getText();
				imdbSearchResults = new ImdbSearch(movieName);
	      		movieInformationResults = new ImdbPageInfo(imdbSearchResults.getURLs());
	      		
	      		remove(mainPagePanel);
	      		
		        movieImages = imdbSearchResults.getImages();
		        movieNames = imdbSearchResults.getMovieNames();
		      	
		      	JPanel searchResultsPanel = new JPanel();
		      	searchResultsPanel.setLayout(new GridBagLayout());
		      	GridBagConstraints layoutContraints = new GridBagConstraints();
		      	
		      	ArrayList<MovieItem> results = new ArrayList<MovieItem>();

				for(int i = 0; i < movieNames.size(); i++){
					String name = movieNames.get(i);
					ImageIcon image = movieImages.get(i);
					results.add(new MovieItem(name,1995,"genre",image));
				}
				
				int row = 0;
		      	for(MovieItem movie:results)
		      	{
		      		JButton movieResultImageButton = new JButton(movieInformationResults.movieImages.get(row));
					movieResultImageButton.addActionListener(this);
					movieResultImageButton.setPreferredSize(new Dimension(182,268));
					layoutContraints.weightx = 0.5;
					layoutContraints.gridx = 0;
					layoutContraints.gridy = row;
			      	searchResultsPanel.add(movieResultImageButton,layoutContraints);
			      	
			      	layoutContraints.gridx = 1;
			      	searchResultsPanel.add(new JLabel(movieInformationResults.movieNames.get(row)),layoutContraints);
			      	row++;
		      	}
		      	JScrollPane searchResultsPanelScroll = new JScrollPane(searchResultsPanel);
		      	add(searchResultsPanelScroll);
		      	repaint();
		      	setVisible(true);
			} else {
				for(int i = 0; i < movieNames.size(); i++){
					if(((JButton) e.getSource()).getIcon().equals(movieInformationResults.movieImages.get(i))){
					 	System.out.println (movieInformationResults.getMovie(i));
					}
				}
			}
		} catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	public static void main(String[] args)
    {
	   	new ImdbTester();
    }
}