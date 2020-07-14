package my.home.library.controller;

import my.home.library.model.Book;
import my.home.library.model.Catalog;

public class CatalogManager {
	private Catalog catalog;
	
	public CatalogManager() {}
	
	public CatalogManager(Catalog catalog) {
		this.catalog = catalog;
	}
	
	public void removeBook(int id) {
		for (Book b : catalog.getCatalog()) {
			if (b.getId() == id) {
				catalog.getCatalog().remove(b);
				return;
			}
		}
	}
	
	public void search(String name) {
		for (Book b : catalog.getCatalog()) {
			if (b.getName().toLowerCase().contains(name.toLowerCase())) {
				System.out.println(b);
			}
		}
	}
	
	public Book get(int i) {
		return catalog.getCatalog().get(i);
	}
	
	public void addBook(Book book) {
		catalog.getCatalog().add(book);
	}
	
	public int size() {
		return catalog.size();
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public String toString() {
		return catalog.toString();
	}
}
