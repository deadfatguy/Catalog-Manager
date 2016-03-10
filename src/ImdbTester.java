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
	JButton backToSearch = new JButton("Back to search");
	JButton openLibrary = new JButton("Open Library");
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
			openLibrary.addActionListener(this);
			movieName = "";
			
			mainPagePanel = new JPanel();
			mainPagePanel.add(movieNameBox);
			mainPagePanel.add(enter);
			mainPagePanel.add(openLibrary);
			
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
				//Takes the ImdbPageInfo from instance field of ImdbSearch class
	      		movieInformationResults = imdbSearchResults.getPageInfo();
	      		
	      		remove(mainPagePanel);
	      		
		        movieImages = imdbSearchResults.getImages();
		        movieNames = imdbSearchResults.getMovieNames();
		      	
		      	JPanel searchResultsPanel = new JPanel();
		      	searchResultsPanel.setLayout(new GridBagLayout());
		      	GridBagConstraints layoutContraints = new GridBagConstraints();
		      	
		      	backToSearch.addActionListener(this);
		      	JPanel main = new JPanel();
		      	JPanel header = new JPanel();
				header.add(backToSearch);
				header.add(openLibrary);
				main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
				main.add(header);
				main.add(searchResultsPanel);
				
		      	
		      	ArrayList<MovieItem> results = new ArrayList<MovieItem>();

				for(int i = 0; i < movieNames.size(); i++){
					String name = movieNames.get(i);
					ImageIcon image = movieImages.get(i);
					results.add(new MovieItem(name,1995,"genre","rating",image));
				}
				
				int row = 0;
		      	for(MovieItem movie:results)
		      	{
		      		JButton movieResultImageButton = new JButton(movieInformationResults.movieImages.get(row));
					movieResultImageButton.addActionListener(this);
					movieResultImageButton.setPreferredSize(new Dimension(182,268));
					layoutContraints.weightx = 0.5;
					layoutContraints.gridx = 0;
					layoutContraints.gridy = row+1;
			      	searchResultsPanel.add(movieResultImageButton,layoutContraints);
			      	
			      	layoutContraints.gridx = 1;
			      	searchResultsPanel.add(new JLabel(movieInformationResults.movieNames.get(row)),layoutContraints);
			      	row++;
		      	}
		      	JScrollPane searchResultsPanelScroll = new JScrollPane(main);
		      	add(searchResultsPanelScroll);
		      	repaint();
		      	setVisible(true);
			} else if(e.getSource().equals(backToSearch))
			{
				setVisible(false);
				new ImdbTester();
			} else if(e.getSource().equals(openLibrary))
			{
				System.out.println("The current operation has not been implemented yet!  Stay tuned!");
			}
			else {
				for(int i = 0; i < movieNames.size(); i++){
					if(((JButton) e.getSource()).getIcon().equals(movieInformationResults.movieImages.get(i))){
					 	MovieItem n = new MovieItem(movieInformationResults.getName(i),movieInformationResults.getYear(i),movieInformationResults.getGenre(i),movieInformationResults.getRating(i),movieInformationResults.getImage(i));
					 	System.out.println(n.getMovie());
//					 		movieInformationResults.getMovie(i));
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