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

public class ImdbPageInfo
{
	ArrayList<String> movieNames;
	ArrayList<String> movieGenres;
	ArrayList<String> movieRatings;
	ArrayList<ImageIcon> movieImages;
	
	public ImdbPageInfo(ArrayList<String> moviePageUrls) throws IOException
	{
		movieNames = new ArrayList<String>();
		movieGenres = new ArrayList<String>();
		movieRatings = new ArrayList<String>();
		movieImages = new ArrayList<ImageIcon>();
		
		for(String moviePageUrl: moviePageUrls){
			Document moviePage = Jsoup.connect(moviePageUrl).get();
			String movieName = moviePage.select("meta[name=title]").get(0).attr("content");
			String movieGenre = moviePage.select("span[itemprop=genre]").text();
			Element rating = moviePage.select("meta[itemprop=contentRating]").first();
			String movieRating = "";
			if(rating==null){
				movieRating = "None";
			} else {
				movieRating = moviePage.select("meta[itemprop=contentRating]").get(0).attr("content");
			}
			
			//Assigns a generic image as default
			ImageIcon movieImage = new ImageIcon("generic.jpg");
			Element poster = moviePage.select("img[alt$=Poster]").first();
			
			//Reassigns actual image if found
			if(poster!=null){
			URL urltemp = new URL(moviePage.select("img[alt$=Poster]").get(0).attr("src"));
			movieImage = new ImageIcon(ImageIO.read(urltemp));
			}
				
			movieNames.add(movieName);
			movieGenres.add(movieGenre);
			movieRatings.add(movieRating);
			movieImages.add(movieImage);
		}
	}
	
	public String getName(int i){
		return movieNames.get(i);
	}
	
	public int getYear(int i){
		return 1998;
	}
	
	public String getGenre(int i){
		return movieGenres.get(i);
	}
	
	public ImageIcon getImage(int i){
		return movieImages.get(i);
	}
	
	public String getRating(int i){
		return movieRatings.get(i);
	}
	
	public String getMovie(int index)
	{
		return "Movie name: "+movieNames.get(index)+"\nMovie genre: "+movieGenres.get(index)+"\nMovie rating: "+movieRatings.get(index);
	}
}