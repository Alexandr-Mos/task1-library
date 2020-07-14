package my.home.library.model;

public class Book {
	private int id;
	private String name;
	private String author;
	private int year;
	private String content;

	private static int counter;

	public Book() {
		Book.incrementCounter();
		this.id = Book.counter;
	}

	public Book(String name, String author, int year, String content) {
		Book.incrementCounter();
		setId(Book.counter);
		this.name = name;
		this.author = author;
		this.year = year;
		this.content = content;
	}

	private static void incrementCounter() {
		Book.counter++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > counter) {
			counter = id;
			this.id = id;
		} else if(id == counter) {
			incrementCounter();
			this.id = counter;
		} else {
			this.id = counter;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", author=" + author + ", year=" + year;
	}

}
