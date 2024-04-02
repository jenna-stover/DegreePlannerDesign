package testClasses;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import testClasses.Course;
import testClasses.Student;
import testClasses.User;
import testClasses.UserList;
import testClasses.UserType;

public class Faculty extends User
{

    public HashMap<UUID, User> advisingStudentsHashMap;
    private ArrayList<UUID> advisingStudentsUUIDs; // reading from file
    public HashMap<UUID, Course> coursesInstructingHashMap;
    private ArrayList<UUID> coursesInstructingUUIDs; // reading from file
    public User searchedUser;
    public ArrayList<Course> coursesInstructingArrayList;
    public ArrayList<User> advisingStudentsArrayList;

    public Faculty(UUID userUUID, String firstName, String lastName, String userEmail, String userPass, 
        String userID, UserType userType, HashMap<UUID, User> advisingStudents, HashMap<UUID, Course> coursesInstructing)
    {
        super(userUUID, firstName, lastName, userEmail, userPass, userID, userType);
        //have to convert these to hashmaps
        //this.advisingStudents = getAdvisingStudents();
        //this.coursesInstructing = ReadFile.readFaculty().courseInstr;
    }

    public Faculty(UUID userUUID, String firstName, String lastName, String userEmail, String userPass, 
        String userID, UserType userType, ArrayList<UUID> IncomingUUIDs)
    {
        super(userUUID, firstName, lastName, userEmail, userPass, userID, userType);
        if(userType.toString() == "ADVISOR") // is this allowed
        {
            advisingStudentsArrayList = new ArrayList<User>();
            advisingStudentsHashMap = new HashMap<UUID, User>();
            this.advisingStudentsUUIDs = IncomingUUIDs;
        }
        else //Professor
        {
            coursesInstructingArrayList = new ArrayList<Course>();
            coursesInstructingHashMap = new HashMap<UUID, Course>();
            this.coursesInstructingUUIDs = IncomingUUIDs;
        }
    }

    public User searchUser(String userID)
    {

        return null;
    }
    
    public AdvisementPlan editStudentAdvisePlan(AdvisementPlan stuAdvisePlan)
    {

        return null;
    }

    public boolean changeCourseGrade(String courseID, String grade, boolean currentOrComplete)
    {

        return false;
    }

    public boolean setCourseCompletionStatus(Course course, int status)
    {

        return false;
    }

    public ArrayList<User> getAdvisingStudents() 
    {
        advisingStudentsArrayList = new ArrayList<User>();
        for(User user : advisingStudentsHashMap.values())
        {
            advisingStudentsArrayList.add(user);
        }
        return advisingStudentsArrayList;
    }

    public boolean addAdvisingStudent(User student)
    {
        if (student != null)
        {
            advisingStudentsArrayList.add(student);
            advisingStudentsHashMap.put(student.getUUID(), student);
            return true;
        }
        return false;
    }

    public ArrayList<UUID> getAdviseStuUUIDList()
    {
        return advisingStudentsUUIDs;
    }
    
    public ArrayList<Course> getCoursesInstructing()
    {
        coursesInstructingArrayList = new ArrayList<Course>();
        for(Course course : coursesInstructingHashMap.values())
        {
            coursesInstructingArrayList.add(course);
        }
        return coursesInstructingArrayList;
    }


    public boolean editDegreePlan(DegreePlan degreePlan)
    {
        return false;
    }

    public ArrayList<User> getAllStudents() 
    {
        UserList userList = UserList.getInstance();
        ArrayList<User> allUsers = userList.getUsers();
        ArrayList<User> students = new ArrayList<User>();
        for(User user : allUsers)
        {
            if (user.userType.toString() == "STUDENT")
            {
                students.add(user);
            }
        }
        return students;
    }

    public String toString()
    {
        return "This Faculty Member is Classified As: " + userType;
    }

    public String advisingStudentsToString()
    {
        if(advisingStudentsArrayList.size() == 0)
            return "You have no students you are advising";
        String result = "";
        for(int i = 0; i < advisingStudentsArrayList.size(); i++)
        {
            result += ((Student) advisingStudentsArrayList.get(i)).toString() + "\n";
        }
        return result;
    }

}