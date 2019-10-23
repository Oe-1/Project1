import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Main
{
	public static String title = null;
	public static int due = 0;
	public static boolean exit = false;
	public static char userInputChar;
	public static int userInputInt;
	public static String userInputString;
	public static LinkedList<Main> assignment = new LinkedList<Main>();
	
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
          				listAssignments();
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
	
	public Main()
	{
    //default assignment
		title = "nothing";
		due = 0;
	}

  public Main(String title, int due)
  {
    this.title = title;
    this.due = due;
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
				if (userInputInt < 20190101 || userInputInt > 99991231)
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
		Main temp = new Main(userInputString, userInputInt); 
		assignment.add(temp);
	}

  public void listAssignments()
  {
    for (Iterator i = assignment.iterator(); i.hasNext();) 
    {
      i.next();
      System.out.println(Main.printer());
    }
  }
	
  public static String printer() 
  {
    return title + " due on " + due;
  }
	public static void quitProgram()
	{
		System.out.println("Goodbye");
		exit = true;
	}
}
