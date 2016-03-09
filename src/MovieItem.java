import javax.swing.*;

/**
 *An item that has a name, year, genre, and image of the main poster.
 */
public class MovieItem implements Item
{
	private String name;
	private int year;
	private String genre;
	private ImageIcon image;
	
	public MovieItem(String n, int y, String g, ImageIcon i){
		name = n;
		year = y;
		genre = g;
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
}