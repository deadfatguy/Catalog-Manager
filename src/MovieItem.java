import javax.swing.*;

/**
 *An item that has a name, year, genre, and image of the main poster.
 */
public class MovieItem implements Item
{
	private String name;
	private int year;
	private String genre;
	private String rating;
	private ImageIcon image;
	
	public MovieItem(String n, int y, String g, String r, ImageIcon i){
		name = n;
		year = y;
		genre = g;
		rating = r;
		image = i;
	}
	
	public String getName(){
		return name;
	}
	
	public int getYear(){
		return year;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public ImageIcon getImage(){
		return image;
	}
	
	public String getRating(){
		return rating;
	}
	
	public String getMovie()
	{
		return "Movie name: "+name+"\nMovie year: "+year+"\nMovie genre: "+genre+"\nMovie rating: "+rating;
	}
}