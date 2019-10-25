//James Andros
//This program creates and maintains a linked list of assignments for the school year.
//It allows a user to add and remove assignments, print all the assignments to the screen,
//and also to show the user which assignment(s) are due next.
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
    //Main acts as the menu and directs the user to other functions of the program
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
                // I thought this would be more efficient then having almost identical code from "listAssignments"
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
	
	public Project1()//constructor assignment with default parameters.
	{
		title = "Test Assignment";
		due = 20190101;
	}

  	public Project1(String title, int due)//assignment with user defined parameters
  	{
    	this.title = title;
    	this.due = due;
  	}
  
  	public static void removeAssignment()
// prompts user to enter the index of the assignment they want to remove.
    {
      Scanner in = new Scanner(System.in);
      boolean valid = false;
      while (valid == false)
      {
        try
        {
          userInputInt = in.nextInt();
          if (userInputInt > assignment.size() - 1 || userInputInt < 0)  //catch if user enters an int outside the bounds of the list
          {
            System.out.println("Invalid Input \nEnter the number associated with the assignment you wish to delete.");
            valid = false;
          }
          else
            valid = true;
        }
        
        catch (InputMismatchException e)// catch if user enters a non-int
        {
          in.next();
          System.out.println("Invalid Input \nEnter the number associated with the assignment you wish to delete.");
        }
      }
      assignment.remove(userInputInt);
      System.out.println();
    }

	
	public static void addAssignment()
      //prompts the user to enter an assignment to the list
      //assignment is entered by taking a String for the name and an int for the due date
      //these two variables are used to populate the assignment constructor
	{
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter the assignment name");
		userInputString = in.nextLine();
		System.out.println("\nEnter the due date in YYYYMMDD format"); //YYYYMMDD format seemed easier to parse
		boolean valid = false; // checks if the user input is valid
		while (valid == false)
		{
			try
			{
				userInputInt = in.nextInt();
				if (userInputInt < 20190101 || userInputInt > 99991231)  // user's input must after 2018.  This is an easy way to avoid the user typing in 00s as the year
				{
					System.out.println("Invalid Input \nMust be in YYYYMMDD format");
					valid = false;
				}
				else
					valid = true;
			}
			catch (InputMismatchException e)  //must be an int
			{
				in.next();
				System.out.println("Invalid Input \nMust be in YYYYMMDD format");
			}
		}
		assignment.add(new Project1(userInputString, userInputInt));
      	if (userInputInt <= earliestDue)
        	earliestDue = userInputInt; // logs assignments with the earliest due date as they are entered
	}

  public static void listAssignments()
    //iterates through linked list and prints each one with its index value
    //this makes it reusable for removing assignments
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
      //Will display all assignments that have the earliest due date
  {
    int i = 0;
    Iterator<Project1> iterator = assignment.iterator();
    if (assignment.size() == 0)
    {
      System.out.println("The assignment list is empty.\n");
    }
      
    else
    {
      System.out.println("Assignment(s) with the earliest due date:");
      while(iterator.hasNext())
        //i variable is needed because "dueCheck" iterates to the next element
        //if the variable was not in use "printer" would not work
      {
        if (dueCheck(iterator.next()) == earliestDue)
        {
          	i++;
        	System.out.println(printer(assignment.get(i)));
        }
      }
      System.out.println();
    }
  }

  public static int dueCheck(Project1 i)
    //just used to check if the assignment matches the earliest due date variable
  {
    return i.due;
  }
  
  public static String printer(Project1 i)
    //prints out the assignment title and its due date
  {
    return i.title + " due on " + i.due;
  }
	public static void quitProgram()
      //Exits the program
	{
		System.out.println("Goodbye");
		exit = true;
	}
}
