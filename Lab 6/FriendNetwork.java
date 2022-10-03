
/**
 * FriendNetwork.java
 * @author Aurielle Jocom
 * @author Donggyu Yu
 * CIS 22C, Lab 6
 */
import java.io.*;
import java.util.*;

public class FriendNetwork {

	public static void main(String[] args) {
		String filename;
		String name;
		int vertices;
		int userId;
		String friend;
		String[] arr;
		int friendId;
		int choice = 0;

		ArrayList<String> users;
		users = new ArrayList<String>();
		users.add("");

		System.out.println("Welcome to the Friend Recommender!");

		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the name of a file: ");
		filename = sc.next();

		File file = new File(filename);

		while (!file.exists()) {
			System.out.println("\nInvalid file name!");
			System.out.print("Enter the name of a file: ");
			filename = sc.next();

			file = new File(filename);
		}

		Scanner input;
		try {
			input = new Scanner(file);

			vertices = input.nextInt();

			Graph G = new Graph(vertices);

			boolean eof = true;
			while (eof == true) {
				userId = input.nextInt();
				input.nextLine();
				name = input.nextLine();
				users.add(name);

				friend = input.nextLine();
				arr = friend.split(",");

				for (int i = 0; i < arr.length; i++) {
					friendId = Integer.parseInt(arr[i].trim());
					G.addUndirectedEdge(userId, friendId);
				}

				eof = input.hasNextLine();
			}

			input.close();

			System.out.println("Enter Your User Number Below:");

			for (int i = 1; i <= vertices; i++) {
				System.out.println(i + ". " + users.get(i));
			}

			System.out.print("\nEnter your choice: ");
			userId = sc.nextInt();

			while (userId < 1 || userId > vertices) {
				System.out.print("Invalid choice!");
				System.out.print("Enter your choice: ");
				userId = sc.nextInt();
			}

			int count;
			while (choice != -1) {
				G.BFS(userId);

				System.out.println("\nHere are your current friends: ");
				for (int i = 1; i <= vertices; i++) {
					if (G.getDistance(i) == 1) {
						System.out.println(i + ". " + users.get(i));
					}
				}

				count = 0;
				System.out.println("\nHere are your recommended friends: ");
				for (int i = 1; i <= vertices; i++) {
					if (G.getDistance(i) > 1) {
						System.out.println(i + ". " + users.get(i));
						count++;
					}
				}

				if (count == 0) {
					System.out.println("Sorry! We don't have any recommendations for you at this time.");
					System.out.print("\nGoodbye!");
					break;
				}

				System.out.println("\nEnter the number of a friend to add or -1 to quit:");
				System.out.print("Enter your choice: ");
				choice = sc.nextInt();

				while ((choice < 1 || choice > vertices) && choice != -1) {
					System.out.println("Invalid choice!");
					System.out.print("Enter your choice: ");
					choice = sc.nextInt();
				}

				if (choice == -1) {
					System.out.print("\nGoodbye!");
					break;
				}

				G.addUndirectedEdge(userId, choice);
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
