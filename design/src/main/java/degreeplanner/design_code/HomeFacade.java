package degreeplanner.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import degreeplanner.design_code.Course;
import degreeplanner.design_code.CourseList;
import degreeplanner.design_code.Student;
import degreeplanner.design_code.User;
import degreeplanner.design_code.UserList;
import degreeplanner.design_code.UserType;

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
    private static HomeFacade homeFacade;

    private HomeFacade()
    {
        //this.user = user;
        this.count = 0;
        courseList = CourseList.getInstance();
        userList = UserList.getInstance();
        userList.updateAdviseStuList();
        degreeList = DegreeList.getInstance();
        advisementPlanList = AdvisementPlanList.getInstance();
        userList.updatePlans();
        this.searchedCourse = searchedCourse;
        //this.users = new ArrayList<User>();
        this.degreePlan = degreePlan; //should we create new DegreePlan?
    }

    public static HomeFacade getInstance()
    {
        if(homeFacade == null)
        {
            homeFacade = new HomeFacade();
        }
        return homeFacade;
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

     public User getLoggedInUser()
     {
        return this.user;
     }

     public boolean logout()
     {
        this.advisementPlanList.saveList();
        this.courseList.saveList();
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
    
    public User getUser(String userID)
    {
        return userList.getUser(userID);
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

    public boolean addCourse(UserType userType, Course newCourse)
    {
        if(userType.equals(UserType.PROFESSOR) || userType.equals(UserType.ADVISOR))
        {
            return courseList.addCourse(newCourse);
        }
        else
        {
            return false; //no permission
        }
    }

    public boolean removeCourse(UserType userType, String courseID)
    {
        if(userType.equals(UserType.PROFESSOR) || userType.equals(UserType.ADVISOR))
        {
            return courseList.removeCourse(courseID);
        }
        else
        {
            return false; //no permission
        }
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

    public ArrayList<Course> getCoursesForSemester(Student student, String semester) {
        return student.getCoursesForSemester(semester);
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
        AdvisementPlan tempPlan = new AdvisementPlan(id, tempStudent, user, tempNote);
        if(advisementPlanList.AddPlan(tempPlan))
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

    public ArrayList<User> searchUsers(String query) {
        ArrayList<User> matchingUsers = new ArrayList<>();
        ArrayList<User> allUsers = userList.getUsers();
    
        // Iterate through each user to find matches
        for (User user : allUsers) {
            if (user.getUserFullName().toLowerCase().contains(query.toLowerCase()) ||
                user.getUserID().toLowerCase().contains(query.toLowerCase()) ||
                user.getUserEmail().toLowerCase().contains(query.toLowerCase())) {
                matchingUsers.add(user);
            }
        }
    
        return matchingUsers;
    }


    public ArrayList<Course> searchCourses(String query) {
        ArrayList<Course> matchingCourses = new ArrayList<>();
    
        // Get all courses from the course list
        ArrayList<Course> allCourses = courseList.getCourses();
    
        // Iterate through each course to find matches
        for (Course course : allCourses) {
            // Check if the course name, ID, or description contains the query
            if (course.getCourseName().toLowerCase().contains(query.toLowerCase()) ||
                course.getCourseID().toLowerCase().contains(query.toLowerCase()) ||
                course.getCourseDescription().toLowerCase().contains(query.toLowerCase())) {
                matchingCourses.add(course);
            }
        }
    
        return matchingCourses;
    }

    public ArrayList<Student> getAdvisees() {
        if (user instanceof Faculty) {
            ArrayList<User> advisees = ((Faculty) user).getAdvisingStudents();
            ArrayList<Student> studentAdvisees = new ArrayList<>();
            for (User advisee : advisees) {
                if (advisee instanceof Student) {
                    studentAdvisees.add((Student) advisee);
                }
            }
            return studentAdvisees;
        }
        return new ArrayList<>();
    }

    
}