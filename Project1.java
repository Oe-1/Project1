import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Project1 
{
	public String assignmentTitle = null;
	public int dueDate = 0;
	public static boolean exit = false;
	public static char userInputChar;
	public static int userInputInt;
	public static String userInputString;
	public static LinkedList<Project1> assignment = new LinkedList<Project1>();
	
	public static void main (String[]args)
	{
		while (exit == false)
		{
			System.out.println("Please enter the capital letter that corresponds with the appropriate command.");
			System.out.println("A) Add an assignment \nR) Remove an assignment \nL) List assignments in the order they were assigned \nP) Print to screen the assignment with the earliest due date \nQ) Quit program");
			Scanner in = new Scanner(System.in);
			userInputChar = in.next().charAt(0);
			
			switch (userInputChar) 
			{
				case 'A':
					addAssignment();
					break;
					
				case 'R':
					//removeAssignment();
					break;
					
				case 'L':
					//listAssignments();
					break;
					
				case 'P':
					//printAssignment();
					break;
					
				case 'Q':
					quitProgram();
					break;
					
				default:
					System.out.println("Invalid input.");
					System.out.println();
			}
		}
	}
	
	public Project1 (String title, int due)
	{
		assignmentTitle = title;
		dueDate = due;
	}
	
	public static void addAssignment ()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter the assignment name");
		userInputString = in.nextLine();
		System.out.println("\nEnter the due date in YYYYMMDD format");
		boolean valid = false;
		while (valid == false)
		{
			try
			{
				userInputInt = in.nextInt();
				if (userInputInt < 1 || userInputInt > 99991231)
				{
					System.out.println("Invalid Input \nMust be in YYYYMMDD format");
					valid = false;
				}
				else
					valid = true;
			}
			catch (InputMismatchException e)
			{
				in.next();
				System.out.println("Invalid Input \nMust be in YYYYMMDD format");
			}
		}
		Project1 temp = new Project1(userInputString, userInputInt); 
		assignment.add(temp);
	}
	
	public static void quitProgram()
	{
		System.out.println("Goodbye");
		exit = true;
	}
}
