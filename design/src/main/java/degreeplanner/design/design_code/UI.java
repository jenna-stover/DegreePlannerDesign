package testClasses;
import java.util.Scanner;

import testClasses.User;
import testClasses.UserType;
/**
 * @author Benjamin King
 * This class is to write output to the console for the user and read user
 * input from the console using the Scanner
 */
public class UI
{
    private Scanner reader;
    /**
     * Constructs new Scanner for reading user input
     */
    public UI()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Reads user input for instructions for program.
     */
    public void inputReader()
    {

    }

    /**
     * Writes output for user to see on console
     * @return Output needed for user to see
     */
    public String outputWriter()
    {
        return "";
    }

    public void run()
    {
        HomeFacade homeFacade = new HomeFacade();
        System.out.println("Please enter your Email");
        String tempEmail = reader.nextLine();
        System.out.println("Please enter your Password");
        String tempPass = reader.nextLine();
        homeFacade.login(tempEmail,tempPass);
        System.out.println(homeFacade.getHomePage());
        System.out.println(homeFacade.getUserOptions());
        int tempInt = Integer.parseInt(reader.nextLine());
        boolean loggedout = false;
        while(loggedout != true)
        {
            loggedout = interpretUserOption(homeFacade, tempInt);
            if (!loggedout)
            {
                System.out.println(homeFacade.getHomePage());
                System.out.println(homeFacade.getUserOptions());
                tempInt = Integer.parseInt(reader.nextLine());
            }
        }
/*
        int count = 0;
        while(true)
        {
            System.out.println("Please enter your email address:\n");
            String userEmail = reader.nextLine().trim();
            User user = new User(userEmail);
            System.out.println("Please enter your password:\n");
            String userPass = reader.nextLine().trim();
            if(userPass == user.getUserPass() && count < 9 )
            {
                outputWriter();
            }
            else if(count < 9)
            {
                count++;
                System.out.println("wrong credentials, try again");
                continue;
            }
            else
            {
                System.exit(0);
            }
        }
*/
    }
    public boolean interpretUserOption(HomeFacade homeFacade, int input)
    {
        UserType tempType = homeFacade.getLoggedInUserType();
        if((tempType).toString() == "STUDENT")
        {
            boolean loggingOut = false;
            switch (input) {
                case 1:
                    homeFacade.logout();
                    loggingOut = true;
                    break;
                case 2:
                    System.out.println(homeFacade.get8SemPlan());
                    break;
                default:
                    homeFacade.logout();
                    loggingOut = true;
                    break;
            }
            return loggingOut;
        }
        else if((tempType).toString() == "ADVISOR")
        {
            boolean loggingOut = false;
            switch (input) {
                case 1:
                    homeFacade.logout();
                    loggingOut = true;
                    break;
                case 2:
                    System.out.println("Please enter the student's ID");
                    String tempStuName = reader.nextLine();
                    User tempStu = homeFacade.getUser(tempStuName, tempType);
                    if((tempStu.userType).toString() == "STUDENT")
                        System.out.println(homeFacade.getStudentsProgress(tempStu));
                    else
                        System.out.println("Student was not entered correctly");
                    break;
                case 3:
                    System.out.println("Please enter the student's ID");
                    String tempName = reader.nextLine();
                    if(homeFacade.addAdvisingStudent(tempName))
                        System.out.println("Student successfully added");
                    else
                        System.out.println("Operation Unsuccessful");
                    break;
                case 4:
                    System.out.println("Please enter the student's ID");
                    String temporaryName = reader.nextLine();
                    User tempStudent = homeFacade.getUser(temporaryName, tempType);
                    if((tempStudent.userType).toString() == "STUDENT")
                    {
                        System.out.println("Please write your note. (Enter when finished)");
                        String tempNote = reader.nextLine();
                        if(homeFacade.addNoteToStudent(tempStudent, tempNote))
                            System.out.println("Note Successfully Added");
                        else
                            System.out.println("Note unsuccessfully added");
                    }
                    else
                        System.out.println("Student was not entered");
                    break;

                default:
                    homeFacade.logout();
                    loggingOut = true;
                    break;
            }
            return loggingOut;
        }
        else  // Professor
        {
            return false;
        }
    }

    /**
     * main to run ui for program
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        
        UI ui = new UI();
        ui.run();
    }

}