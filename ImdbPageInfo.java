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
	String movieName;
	String movieGenre;
	String movieRating;
	String movieLength;
	ImageIcon movieImage;
	
	public ImdbPageInfo(String url) throws IOException
	{
		Document doc = Jsoup.connect(url).get();
		movieName = doc.select("meta[name=title]").get(0).attr("content");
		movieGenre = doc.select("span[itemprop=genre]").text();
		Element rating = doc.select("meta[itemprop=contentRating]").first();
		if(rating==null){
			movieRating = "None";
		} else {
			movieRating = doc.select("meta[itemprop=contentRating]").get(0).attr("content");
		}
		
		Element poster = doc.select("img[alt$=Poster]").first();
		if(poster==null){
			
		} else {
			URL urltemp = new URL(doc.select("img[alt$=Poster]").get(0).attr("src"));
			movieImage = new ImageIcon(ImageIO.read(urltemp));	
		}

	}
}