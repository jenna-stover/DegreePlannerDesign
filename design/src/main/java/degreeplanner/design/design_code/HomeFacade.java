package testClasses;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import testClasses.Course;
import testClasses.CourseList;
import testClasses.Student;
import testClasses.User;
import testClasses.UserList;
import testClasses.UserType;

public class HomeFacade {
    public User user;
    public int count;
    public Course searchedCourse;
    //private ArrayList<User> users;
    public UserList userList;
    private CourseList courseList;
    private DegreeList degreeList;
    private DegreePlan degreePlan;
    private AdvisementPlanList advisementPlanList;

    public HomeFacade()
    {
        //this.user = user;
        this.count = 0;
        courseList = CourseList.getInstance();
        userList = UserList.getInstance();
        userList.updateAdviseStuList();
        degreeList = DegreeList.getInstance();
        advisementPlanList = AdvisementPlanList.getInstance();
        this.searchedCourse = searchedCourse;
        //this.users = new ArrayList<User>();
        this.degreePlan = degreePlan; //should we create new DegreePlan?
    }

     public boolean login(String email, String userPass)
     {
        User temp = userList.login(email, userPass);
        if (temp != null)
        {
            this.user = temp;
            return true;
        }
        else
            return false;
     }
     public UserType getLoggedInUserType()
     {
        return user.userType;
     }
  

     public boolean logout()
     {
        return userList.logout();
     }

    /**
     * getter methods which allows other classes to retrieve the user in different ways
     * @param firstName
     * @param lastName
     * @param userType
     * @return the User object
     */
    public User getUser(String fullName, UserType userType)
    {
        return userList.getUser(fullName, userType);
    }

    public User getUser(String email, String password)
    {
        return userList.getUserByEmailAndPass(email, password);
    }
    

/*  public AdvisementPlan getAdvisementPlan(User user)
    {
        return advisementPlan;
    } */

    public boolean updateAdvisementPlan(User user)
    {
        return true;
    }

    public boolean editDegreePlan(UserType userType)
    {
        return true;
    }

    public boolean addCourse(UserType userType)
    {
        return true;
    }

    public boolean removeCourse(UserType userType)
    {
        return true;
    }

/*  public boolean createUser(UUID userUUID, String firstName, String lastName, 
    String userEmail, String userPass, String userID, UserType newUserType)
    {
        if(user.userType.equals(UserType.PROFESSOR) || user.userType.equals(UserType.ADVISOR))
        {
            User toAdd = new User(userUUID, firstName, lastName, userEmail, userPass, userID, newUserType);
            // you don;t need this line, you are adding the user in the return statement
            //userList.addUser(toAdd); //could we compress these previous two lines to userList.addUser(new User(userUUID, 
                                     //firstName, lastName, userEmail, userPass, userID, newUserType))
            return userList.addUser(toAdd);
        }    
        return false;
    } */

    public boolean editUser(UserType userType)
    {
        return true;
    }

    public boolean removeUser(UserType userType)
    {
        return true;
    }

    public Course getCourse(String courseID)
    {
        return courseList.getCourse(courseID);
    }

    public ArrayList<Course> getAllCourses(Student student)
    {
        return student.getAllCourses();
    }

    public boolean editCourse(String courseID, UserType userType, String newName, String newDescription)
    {
        return courseList.editCourse(courseID, userType, newName, newDescription);
    }

/*  public void getView(Tabs tab)
    {

    } */

    public void getCourseDetails(String courseID)
    {
        Course temp = courseList.getCourse(courseID);
        System.out.println(temp.toString());
    }
    public String searchByName(String fullName)
    {
        User tempUser = userList.getUser(fullName, user.userType);
        if (tempUser == null)
            return "No User by that Name";
        return tempUser.toString();
    }
    public String getStudentsProgress(User student)
    {
        return ((Student)student).progressToString();
    }
    public boolean addNoteToStudent(User tempStudent, String tempNote)
    {
        UUID id = UUID.randomUUID();
        AdvisementPlan tempPlan = new AdvisementPlan(id, tempStudent.getUUID(), user.getUUID(), tempNote);
        if(advisementPlanList.AddList(tempPlan))
            return true;
        return false;
    }

    public String getUserOptions()
    {
        if((user.userType).toString() == "STUDENT")
        {
            return "OPTIONS:\n1. Logout\n2. Generate Plan";
        }
        else if((user.userType).toString() == "ADVISOR")
        {
            return "OPTIONS:\n1. Logout\n2. Inspect Student's Progress\n3. Add Advisee\n4. Add note to Advisee";
        }
        else // Professor
        {
            return "";
        }
    }
    public String getHomePage()
    {
        UserType userType = user.userType;
        if(userType.toString() == "STUDENT")
        {
            return getStudentHomePage();
        }
        else if(userType.toString() == "ADVISOR")
        {
            return getFacultyHomePage();
        }
        else  //Professor
        {
            return getProfessorHomePage();
        }
    }
    public boolean addAdvisingStudent(String studentName)
    {
        if((user.userType).toString() == "ADVISOR")
        {
            User temp = userList.getUser(studentName, user.userType);
            if(((Faculty) user).addAdvisingStudent(temp))
                return true;
            return false;
        }
        return false;
    }
    public String get8SemPlan()
    {
        if((user.userType).toString() == "STUDENT")
            return ((Student) user).eightSemesterPlanToString();
        else
            return "You are not a student.";
    }

    private String getStudentHomePage()
    {
        return "---HomePage---\nYour Progress:\n" + ((Student) user).progressToString();
    }

    private String getFacultyHomePage()
    {
        return "---HomePage---\nList of advisees:\n" + ((Faculty) user).advisingStudentsToString();
    }

    private String getProfessorHomePage()
    {
        return "";
    }

}