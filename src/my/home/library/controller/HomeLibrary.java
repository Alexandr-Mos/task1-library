package my.home.library.controller;

import my.home.library.model.Book;
import my.home.library.model.Catalog;
import my.home.library.model.User;

public class HomeLibrary {
	private User authUser;
	private FileManager fileMng;
	private String path;
	private CatalogManager catalog;
	private UsersBase usersBase;
	private Console console;
	private EmailSender emailSender;

	public HomeLibrary(String path) {
		this.path = path;
	}
	
	public void initialise() {
		authUser = null;
		fileMng = new FileManager(path);
		console = Console.getInstance();
		emailSender = new EmailSender("mail", "password");

		while (!fileMng.getDirectory().exists()) {
			if (!fileMng.getDirectory().mkdirs()) {
				System.out.println("Данной директории не существует и её невозможно создать... Введите новый путь:");
				path = console.nextCommand();
				fileMng.setDirectory(path);
			} else {
				System.out.println("Создан новый каталог");
			}

		}

		if (!fileMng.existsCatalogFile()) {
			fileMng.createFile(Catalog.FILE_NAME);
		}
		catalog = new CatalogManager(new Catalog(fileMng.readCatalogFile()));
		
		System.out.println(catalog);

		if (!fileMng.existsUsersFile()) {
			fileMng.createFile(UsersBase.FILE_NAME);
		}
		usersBase = new UsersBase(fileMng.readUsersFile(UsersBase.FILE_NAME));
		System.out.println(usersBase);
	}
	
	public void login() {
		System.out.println("Авторизация... (команда [menu] для возврата)");
		
		while (true) {
			System.out.print("email ");
			String email = console.nextCommand();
			if (email.toLowerCase().equals("menu")) {
				return;
			}
			System.out.print("password ");
			String password = console.nextCommand();
			if (password.toLowerCase().equals("menu")) {
				return;
			}
			
			User user = usersBase.find(email);
			if (user == null) {
				System.out.println("Пользователя с таким email не существует.");
			} else if (!user.getPassword().equals(Encryptor.encryptMD5(password))) {
				System.out.println("Неверный пароль.");
			} else {
				this.authUser = user;
				System.out.println("Добро пожаловать!");
				return;
			}
		}
		
	}
	
	public void logout() {
		authUser = null;
		System.out.println("Пользователь разлогинен");
	}
	
	public void addUser() {
		System.out.print("Введите email ");
		String email = console.nextCommand();
		while (!(email.contains("@") && email.contains("."))) {     //простейшая проверка
			System.out.print("Неверный формат email ");
			email = console.nextCommand();
		}
		
		String password;
		do {
			System.out.print("Введите пароль(не менее 8 символов) ");
			password = console.nextCommand();
		} while (password.length() < 8); 
		
		String isAdmin;
		do {
			System.out.print("Администратор? [Да/Нет] ");
			isAdmin = console.nextCommand();
		} while(!(isAdmin.toLowerCase().equals("да") || isAdmin.toLowerCase().equals("нет")));
		
		User user;
		if (isAdmin.toLowerCase().equals("да")) {
			user = new User(email, Encryptor.encryptMD5(password), true);
		} else {
			user = new User(email, Encryptor.encryptMD5(password), false);
		}
		
		usersBase.addUser(user);
		authUser = user;
		System.out.println("Пользователь создан и залогинен");
	}
	
	public void printCatalog() {
		String command = "";
		int page = 1;
		while (!command.toLowerCase().equals("menu")) {
			
			int begin = 10 * (page-1);
			int end = (10 * page) -1;
			for (int i = begin; i < end && i < catalog.size(); i++) {
				System.out.println(catalog.get(i));
			}
			System.out.println("Всего страниц: " + (catalog.size() / 10 + 1));
			
			System.out.print("Введите команду([page] или [menu]) ");
			command = console.nextCommand();
			
			if (command.toLowerCase().equals("page")) {
				do {
					System.out.print("Введите страницу ");
					page = console.nextInt();
				} while (page < 1);
				
				
			}
		}
	}
	
	public void addBook() {
		if (authUser == null) {
			System.out.println("Авторизируйтесь!");
			return;
		}
		Book book = new Book();
		System.out.println("Введите название ");
		book.setName(console.nextCommand());
		System.out.println("Введите автора ");
		book.setAuthor(console.nextCommand());
		System.out.println("Введите год издания ");
		book.setYear(console.nextInt());
		System.out.println("Введите \"[paper]\", если книга бумажная. Иначе введите текст книги ");
		book.setContent(console.nextCommand());
		
		
		if (authUser.isAdmin()) {
			catalog.addBook(book);
			System.out.println("Книга добавлена");
			emailSender.sendToAll("Добавлена новая книга", book.toString(), usersBase);
		} else {
			emailSender.sendToAdmins("Запрос на добавление книги", book.toString(), usersBase);
		}
	}
	
	public void removeBook() {
		if (!authUser.isAdmin()) {
			System.out.println("У вас не прав на это действие");
			return;
		}
		System.out.print("Введите id книги, которую хотите удалить ");
		catalog.removeBook(console.nextInt());
		System.out.println("Книга удалена");
	}
	
	public void search() {
		System.out.println("Введите название книги или часть названия");
		catalog.search(console.nextCommand());
	}
	
	public void close() {
		fileMng.writeFile(Catalog.FILE_NAME, catalog.toString());
		fileMng.writeFile(UsersBase.FILE_NAME, usersBase.toString());
	}
}
