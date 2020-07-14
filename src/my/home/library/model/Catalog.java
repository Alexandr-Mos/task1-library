package my.home.library.model;

import java.util.ArrayList;

public class Catalog {
	private ArrayList<Book> catalog;
	public static final String FILE_NAME = "catalog.txt";
	
	public Catalog(ArrayList<Book> catalog) {
		this.catalog = catalog;
	}
	
	public int size() {
		return catalog.size();
	}

	public ArrayList<Book> getCatalog() {
		return catalog;
	}

	public void setCatalog(ArrayList<Book> catalog) {
		this.catalog = catalog;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Book b : catalog) {
			sb.append(b.getId() + "\n");
			sb.append(b.getName() + "\n");
			sb.append(b.getAuthor() + "\n");
			sb.append(b.getYear() + "\n");
			sb.append(b.getContent() + "\n");
		}
		return sb.toString();
	}
	
}
