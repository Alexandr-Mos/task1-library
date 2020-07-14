package my.home.library.controller;

import java.util.Scanner;

public class Console {
	private static Console instance;
	private static Scanner scanner;
	public static String HELP = "Доступные команды:\r\n"
			+ "\t-[help]       - вывести помощь на экран,\r\n"
			+ "\t-[login]      - войти в аккаунт,\r\n"
			+ "\t-[logout]     - выйти из аккаунта,\r\n"
			+ "\t-[register]   - зарегестрировать пользователя,\r\n"
			+ "\t-[catalog]    - вывод каталога на экран,\r\n"
			+ "\t-[addbook]    - добавить книгу,\r\n"
			+ "\t-[removebook] - удалить книгу,\r\n"
			+ "\t-[search]     - поиск книги,\r\n"
			+ "\t-[exit]       - выйти из программы.";
	
	private Console() {
		scanner = new Scanner(System.in);
	}
	
	public static Console getInstance() {
		if (instance == null) {
			instance = new Console();
		}
		return instance;
	}
	
	public String nextCommand() {
		System.out.print(">> ");
		return scanner.nextLine();
	}
	
	public int nextInt() {
		int number = 0;
		System.out.print(">> ");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.print(">> ");
		}
		number = scanner.nextInt();
		scanner.nextLine();                // сьедает перевод на новую строку. Иначе следующее чтение строки забагует
		return number;
	}
	

}
