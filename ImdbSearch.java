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
	ArrayList<String> k;
	ArrayList<String> imageloc;
	ArrayList<ImageIcon> v;
	Multimap<String, ImageIcon> images;
	ArrayList<String> movieURL;
	
	public ImdbSearch(String search) throws IOException{
		imageloc = new ArrayList<String>();
		k = new ArrayList<String>();
		v = new ArrayList<ImageIcon>();
		images = ArrayListMultimap.create();
		movieURL = new ArrayList<String>();
		
		search = search.replaceAll(" ", "+").toLowerCase();
		System.out.println ("http://www.imdb.com/find?q="+search+"&s=tt&ttype=ft&ref_=fn_ft");
		Document doc = Jsoup.connect("http://www.imdb.com/find?q="+search+"&s=tt&ttype=ft&ref_=fn_ft").get();
		
		Elements names = doc.select("a[href^=/title/");
		
		int i = 0;
		for(Element name : names){
			if(i==0){
				i=1;
				Pattern p = Pattern.compile("(<img src=\")(.*?)(\">)");
				Matcher m = p.matcher(name.toString());
				if(m.find()){
					imageloc.add(m.group(2));
				}
				
				p = Pattern.compile("(<a href=\")(.*?)(\">)");
				m = p.matcher(name.toString());
				if(m.find()){
					movieURL.add("http://www.imdb.com/"+m.group(2));
				}
			}else{
				i=0;
				Pattern p = Pattern.compile("(\">)(.*?)(<)");
				Matcher m = p.matcher(name.toString());
				if(m.find()){
					k.add(m.group(2));
				}
			}
		}
		
		for(String s : imageloc){
			URL url = new URL(s);
			ImageIcon imagex = new ImageIcon(ImageIO.read(url));
			v.add(imagex);
		}
	}
	
	public ArrayList<ImageIcon> getImages()
	{
		return v;
	}
	
	public ArrayList<String> getMovieNames()
	{
		return k;
	}
	
	public ArrayList<String> getURLs()
	{
		return movieURL;
	}
}