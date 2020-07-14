package my.home.library;

import java.io.IOException;

import my.home.library.controller.Console;
import my.home.library.controller.HomeLibrary;

/*Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
Общие требования к заданию:
• Система учитывает книги как в электронном, так и в бумажном варианте. +
• Существующие роли: пользователь, администратор. +
• Пользователь может просматривать книги в каталоге книг, осуществлять поиск
книг в каталоге.+
• Администратор может модифицировать каталог.+
• *При добавлении описания книги в каталог оповещение о ней рассылается на
e-mail всем пользователям
• **При просмотре каталога желательно реализовать постраничный просмотр+
• ***Пользователь может предложить добавить книгу в библиотеку, переслав её
администратору на e-mail.
• Каталог книг хранится в текстовом файле.+
• Данные аутентификации пользователей хранятся в текстовом файле. Пароль
не хранится в открытом виде+*/

public class Main {

	public static void main(String[] args) throws IOException {
		Console console = Console.getInstance();
		String command = "F:\\test\\";

		System.out.println("Введите директорию файлов библиотеки...");
		//command = console.nextCommand();
		HomeLibrary lib = new HomeLibrary(command);
		
		lib.initialise();
		
		System.out.println(Console.HELP);
				
		while (!command.equals("exit")) {
			System.out.print("Введите команду ");
			command = console.nextCommand();
			
			switch (command.toLowerCase()) {
				case "help":
					System.out.println(Console.HELP);
					break;
				case "login":
					lib.logout();
					lib.login();
					break;
				case "logout":
					lib.logout();
					break;
				case "register":
					lib.addUser();
					break;
				case "catalog":
					lib.printCatalog();
					break;
				case "addbook":
					lib.addBook();
					break;
				case "removebook":
					lib.removeBook();
					break;
				case "search":
					lib.search();
					break;
				default:
					System.out.println("Неизвестная команда");
					break;
			}
		}
		
		lib.close();
	}

}
