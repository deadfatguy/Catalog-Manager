import java.util.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import java.util.regex.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Response;
import com.google.common.collect.*;

public class ImdbSearch
{
	ArrayList<String> movieNames;
	ArrayList<ImageIcon> movieImages;
	ArrayList<String> movieURL;
	ImdbPageInfo movieInformationResults;
	
	public ImdbSearch(String searchTerm) throws IOException{
		movieNames = new ArrayList<String>();
		movieImages = new ArrayList<ImageIcon>();
		movieURL = new ArrayList<String>();
		
		//Make the search term usable for the imdb url
		searchTerm = searchTerm.replaceAll(" ", "+").toLowerCase();
		
		//Create a document based on the imdb search syntax and the users search term
		Document searchPage = Jsoup.connect("http://www.imdb.com/find?q="+searchTerm+"&s=tt&ttype=ft&ref_=fn_ft").get();
		
		//Gets all the URLs for the poster image and gets the movie name
		Elements rawNames = searchPage.select("a[href^=/title/");
		
		int index = 0;
		for(Element name : rawNames){
			if(index==0){
				index=1;
				//Parse the movie image URLs
				Pattern movieURLPattern = Pattern.compile("(<a href=\")(.*?)(\">)");
				Matcher movieURLResults = movieURLPattern.matcher(name.toString());
				if(movieURLResults.find()){
					movieURL.add("http://www.imdb.com/"+movieURLResults.group(2));
				}
			}else{
				index=0;
				//Parse the movie name results
				Pattern movieNamePattern = Pattern.compile("(\">)(.*?)(<)");
				Matcher movieNameResults = movieNamePattern.matcher(name.toString());
				if(movieNameResults.find()){
					movieNames.add(movieNameResults.group(2));
				}
			}
		}
		//Sets instance field rather than setting up a local variable
		movieInformationResults = new ImdbPageInfo(movieURL);
		
		//Add all the images to movieImages
		for(ImageIcon movieImage : movieInformationResults.movieImages){
			movieImages.add(movieImage);
		}
	}
	
	public ArrayList<ImageIcon> getImages()
	{
		return movieImages;
	}
	
	public ArrayList<String> getMovieNames()
	{
		return movieNames;
	}
	
	public ArrayList<String> getURLs()
	{
		return movieURL;
	}
	
	public ImdbPageInfo getPageInfo()
	{
		return movieInformationResults;
	}
}