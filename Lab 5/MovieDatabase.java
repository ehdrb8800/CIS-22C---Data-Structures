
/**
 * MovieDatabase.java
 * @author Aurielle Jocom
 * @author Donggyu Yu
 * CIS 22C, Lab 5
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MovieDatabase {
	private final int NUM_MOVIES = 26;
	Hash<Movie> ht = new Hash<>(NUM_MOVIES * 2);
	BST<Movie> bst = new BST<>();

	public static void main(String[] args) throws IOException {

		String title;
		String director;
		int year;
		double grossMillions;
		File file = new File("movies.txt");
		if (!file.exists()) {
			throw new IOException("movie.txt" + "could not be found");
		}
		Scanner input = new Scanner(file);

		MovieDatabase md = new MovieDatabase();
		
		Movie m;

		boolean eof = true;

		while (eof == true) {
			title = input.nextLine();
			director = input.nextLine();
			year = input.nextInt();
			grossMillions = input.nextDouble();

			m = new Movie(title, director, year, grossMillions);
			md.ht.insert(m);
			md.bst.insert(m);

			if (input.hasNextLine()) {
				input.nextLine();
				input.nextLine();
			}

			eof = input.hasNextLine();
		}

		input.close();
		Scanner sc = new Scanner(System.in);
		char in;

		System.out.println("Welcome to the Bond Movie Database!\n");

		do {
			System.out.println("Please select from one of the following options:\n");
			System.out.println("A. Add a Movie\n" + "D. Display all Movies\n" + "R. Remove a Movie\n"
					+ "S. Search for a Movie\n" + "X. Exit\n");
			System.out.print("Enter your choice: ");
			in = sc.next().charAt(0);

			while (in != 'D' && in != 'd' && in != 'R' && in != 'r' && in != 'A' && in != 'a' && in != 'X' && in != 'x'
					&& in != 'S' && in != 's') {
				System.out.println("\nInvalid Selection!\n");
				System.out.println("Please select from one of the following options:\n");
				System.out.println("A. Add a Movie\n" + "D. Display all Movies\n" + "R. Remove a Movie\n"
						+ "S. Search for a Movie\n" + "X. Exit\n");
				System.out.print("Enter your choice: ");
				in = sc.next().charAt(0);
			}

			if (in == 'D' || in == 'd') {
				System.out.println("\nPlease select one of the following options:\n");
				System.out.println("S. Sorted\n" + "U. Unsorted\n");

				System.out.print("Enter your choice: ");
				in = sc.next().charAt(0);

				System.out.println("\nDisplaying Movies:\n");

				if (in == 'U' || in == 'u') {
					System.out.print(md.ht);
					System.out.print("\n");
				}

				else if (in == 'S' || in == 's') {
					md.bst.inOrderPrint();
					System.out.print("\n");
				}

				else {
					System.out.println("\nInvalid Selection!\n");
					System.out.println("\nPlease select one of the following options:\n");
					System.out.println("S. Sorted\n" + "U. Unsorted\n");

					System.out.print("Enter your choice: ");
					in = sc.next().charAt(0);
				}
			}

			else if (in == 'R' || in == 'r') {
				System.out.println("\nRemoving a movie!\n");

				System.out.print("Enter the title: ");
				title = sc.next() + sc.nextLine();
				System.out.print("Enter the director: ");
				director = sc.next() + sc.nextLine();

				m = new Movie(title, director, 0, 0);

				if (md.ht.search(m) == -1) {
					System.out.println("\nI cannot find " + director + "'s " + title + " in the database.\n");
				}

				else {
					md.ht.remove(m);
					md.bst.remove(m);
					System.out.println("\n" + director + "'s " + title + " was removed!\n");
				}
			}

			else if (in == 'A' || in == 'a') {
				System.out.println("\nAdding a movie!\n");

				System.out.print("Enter the title: ");
				title = sc.next() + sc.nextLine();
				System.out.print("Enter the director: ");
				director = sc.next() + sc.nextLine();
				System.out.print("Enter the year: ");
				year = sc.nextInt();
				System.out.print("Enter the gross in millions: $");
				grossMillions = sc.nextDouble();

				m = new Movie(title, director, year, grossMillions);
				md.ht.insert(m);
				md.bst.insert(m);

				System.out.println("\n" + title + " was added!\n");
			}

			else if (in == 'S' || in == 's') {
				System.out.println("\nSearching a movie!\n");

				System.out.print("Enter the title: ");
				title = sc.next() + sc.nextLine();
				System.out.print("Enter the director: ");
				director = sc.next() + sc.nextLine();

				m = new Movie(title, director, 0, 0);

				if (md.ht.search(m) == -1) {
					System.out.println("\n" + director + "'s " + title + " is not in the database.\n");
				}

				else {
					System.out.println("\n" + director + "'s " + title + " is in the database.\n");
				}
			}

			else {
				System.out.println("\nGood bye!\n");
				break;
			}
		} while (in != 'X' || in != 'x');
		sc.close();
	}
}