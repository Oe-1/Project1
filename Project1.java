import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Project1
{
	public String title = null;
	public int due = 20190101;
  	public static int earliestDue = 99991232;
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
                	System.out.println("Enter the number associated with the assignment you wish to delete.");
					listAssignments();
                	removeAssignment();
                	System.out.println("Here is the new list of assignments:");
                	if (assignment.size() < 1)
                    	System.out.println("The list is empty\n");
                	else
                    	listAssignments();
                	
					break;
					
				case 'L':
          				listAssignments();
					break;
					
				case 'P':
					printAssignments();
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
	
	public Project1()
	{
		title = "Test Assignment";
		due = 20190101;
	}

  	public Project1(String title, int due)
  	{
    	this.title = title;
    	this.due = due;
  	}
  
  	public static void removeAssignment()
    {
      Scanner in = new Scanner(System.in);
      boolean valid = false;
      while (valid == false)
      {
        try
        {
          userInputInt = in.nextInt();
          if (userInputInt > assignment.size() - 1 || userInputInt < 0)
          {
            System.out.println("Invalid Input \nEnter the number associated with the assignment you wish to delete.");
            valid = false;
          }
          else
            valid = true;
        }
        
        catch (InputMismatchException e)
        {
          in.next();
          System.out.println("Invalid Input \nEnter the number associated with the assignment you wish to delete.");
        }
      }
      assignment.remove(userInputInt);
      System.out.println();
    }

	
	public static void addAssignment()
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
		assignment.add(new Project1(userInputString, userInputInt));
      	if (userInputInt <= earliestDue)
        	earliestDue = userInputInt;
	}

  public static void listAssignments()
  {
    Iterator<Project1> iterator = assignment.iterator();
    int i = 0;
    while(iterator.hasNext())
    {
    	System.out.print(i + ". ");
    	System.out.println(printer(iterator.next()));
      	i++;
    }
    System.out.println();
  }
  
    public static void printAssignments()
  {
    Iterator<Project1> iterator = assignment.iterator();
    if (assignment.size() == 0)
    {
      System.out.println("The assignment list is empty.\n");
    }
      
    else
    {
      while(iterator.hasNext())
      {
        if (dueCheck(iterator.next()) == earliestDue)
        {
        	System.out.println("The earliest due assignment is:");
        	System.out.println(printer(iterator));
        }
      }
      System.out.println();
    }
  }

  public static int dueCheck(Project1 i)
  {
    return i.due;
  }
  
  public static String printer(Project1 i) 
  {
    return i.title + " due on " + i.due;
  }
	public static void quitProgram()
	{
		System.out.println("Goodbye");
		exit = true;
	}
}
