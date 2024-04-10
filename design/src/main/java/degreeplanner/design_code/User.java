package degreeplanner.design_code;
import java.util.ArrayList;
import java.util.UUID;

import degreeplanner.design_code.Tab;

public abstract  class User 
{
    public String firstName;
    public String lastName;
    public String userEmail;
    public UserType userType;
    private String userPass; 
    protected String userID;
    public UUID userUUID;

    // Should we have UUID's declared as type UUID or as type String?
    public User(UUID userUUID, String firstName, String lastName, String userEmail, String userPass, 
        String userID, UserType userType)
    {
        if (firstName == null || lastName == null || userEmail == null || userPass == null || userID == null || userType == null) 
        {
            if (firstName == null)
             System.out.println(1);
             if (lastName == null)
             System.out.println(2);
             if (userEmail == null)
             System.out.println(3);
             if (userPass == null)
             System.out.println(4);
             if (userID == null)
             System.out.println(5);
             if (userType == null)
             System.out.println(6);
            throw new IllegalArgumentException("Null values are not allowed for constructor parameters");
        }
        if (userEmail.isEmpty()) 
        {
            throw new IllegalArgumentException("User email cannot be empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userType = userType;
        this.userID = userID;
        this.userUUID = userUUID;
    }

    public User(String userEmail)
    {
        if (userEmail.isEmpty()) 
        {
            throw new IllegalArgumentException("User email cannot be empty");
        }

        this.userEmail = userEmail;
    }

    //consider if this method should return tabs
    public void displayTabs(UserType userType)
    {
        ArrayList<Tab> tabsToDisplay = new ArrayList<Tab>();

        switch(userType)
        {
            //we can change which tabs to display if needed
            case STUDENT:
                tabsToDisplay.add(Tab.HOMEPAGE);
                tabsToDisplay.add(Tab.DEGREEPLAN);
                tabsToDisplay.add(Tab.DRAFTSLATE);
                tabsToDisplay.add(Tab.ADVISEMENTPLAN);
                break;
            case PROFESSOR:
                tabsToDisplay.add(Tab.HOMEPAGE);
                tabsToDisplay.add(Tab.ADVISEMENTPLAN);
                break;
            case ADVISOR:
                tabsToDisplay.add(Tab.HOMEPAGE);
                tabsToDisplay.add(Tab.ADVISEMENTPLAN);
                break;
            default:
                break;
        }
    }

    public boolean checkLoginCredentials(String userEmail, String userPass)
    {
        if(this.userEmail.equals(userEmail) && this.userPass.equals(userPass))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
     * allows other classes to retrieve the user's ID since it is a private attribute
     * @return a String representation of the userID associated w/ the User object
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * allows other classes to retrieve the user's password since it is a private attribute
     * @return a String representation of the userPass associated w/ the User object
     */
    public String getUserPass()
    {
        return userPass;
    }

    public UUID getUUID() {
        return this.userUUID;
    }

    public String getUserEmail() 
    {
        return this.userEmail;
    }

    public UserType getUserType()
    {
        return this.userType;
    }

    public String getUserFirstName()
    {
        return this.firstName;
    }

    public String getUserLastName()
    {
        return this.lastName;
    }

    public String getUserFullName()
    {
        return this.firstName + " " + this.lastName;
    }

    /**
     * clears necessary fields associated w/ the user object
     * @param user
     */
    public void logout() //took out parameters
    {
        //consider implementing other "cleanup" tasks
        this.firstName = null;
        this.lastName = null;
        this.userEmail = null;
        this.userPass = null;
        this.userID = null;
        this.userType = null;
    }

    /**
     * 
     * @param courseID
     * @param courses
     * @return a Course from the ArrayList of courses based on the courseID
     */
    public Course searchCourse(String courseID, ArrayList<Course> courses) //added courses ArrayList to parameter
    {
        if (courses != null) {
            for(Course course : courses)
            {
                if(course.getCourseID().equals(courseID))
                {
                    return course;
                }
            }
        }
        return null;
    }

}
