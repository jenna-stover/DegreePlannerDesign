package degreeplanner.design.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class Course
{
    private UUID courseUUID;
    public String courseID;
    public String courseName;
    public String courseDescription;
    // public ArrayList<HashMap<Course, String>> coursePrereq; 
    public ArrayList<HashMap<UUID, String>> coursePrereqUUID;
    // public ArrayList<HashMap<Course, String>> courseCoreq; 
    public ArrayList<HashMap<UUID, String>> courseCoreqUUID;
    public int courseHours;
    public String requiredGrade;
    public ArrayList<String> semesterProvided;

    /**
     * Course constructor method to initialize the attributes, and avoids null references
     * @param courseUUID
     * @param courseID
     * @param courseName
     * @param courseDescription
     * @param coursePrereqUUID
     * @param courseCoreqUUID
     * @param courseHours
     * @param requiredGrade
     * @param semesterProvided
     */
    public Course(UUID courseUUID, String courseID, String courseName, String courseDescription, ArrayList<HashMap<UUID, 
        String>> coursePrereqUUID, ArrayList<HashMap<UUID, String>> courseCoreqUUID, int courseHours, String requiredGrade, ArrayList<String> semesterProvided)
    {
        this.courseUUID = courseUUID;
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.coursePrereqUUID = (coursePrereqUUID != null) ? new ArrayList<>(coursePrereqUUID) : new ArrayList<>();
        this.courseCoreqUUID = (courseCoreqUUID != null) ? new ArrayList<>(courseCoreqUUID) : new ArrayList<>();
        this.courseHours = courseHours;
        this.requiredGrade = requiredGrade;
        this.semesterProvided = (semesterProvided != null) ? new ArrayList<>(semesterProvided) : new ArrayList<>();
    }

    /**
     * getter method for other classes to access the courseUUID private attribute
     * @param course
     * @return
     */
    public UUID getUUID()
    {
        return courseUUID;
    }

    public UUID getCourseUUID()
    {
        return this.courseUUID;
    }

    //added this method for the User class to access in the searchCourse method
    public String getCourseID()
    {
        return courseID;
    }

    public String getCourseName()
    {
        return this.courseName;
    }
    
    public String getCourseDescription()
    {
        return this.courseDescription;
    }

    public int getCourseHours()
    {
        return this.courseHours;
    }

    public String getRequiredGrade()
    {
        return this.requiredGrade;
    }

    public ArrayList<String> getSemestersProvided()
    {
        return this.semesterProvided;
    }

    public boolean getPrereqStatus(Course course)
    {
        if(coursePrereqUUID != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean getCoreqStatus(Course course)
    {
        if(courseCoreqUUID != null){
            return true;
        }
        else{
            return false;
        }
    }

    public String getReceivedGrade(Course course)
    {
        return null;
    }

    public String toString()
    {
        String temp = "Course ID: " + this.courseID + "\nCourse Name: " + this.courseName + "\nCourse Description: " + this.courseDescription + "\nCourse Hours: " + this.courseHours + "\n";
        temp += reqsToString();
        return temp;
    }

    public String testToString()
    {
        return "Course ID: " + this.courseID + "\nCourse Name: " + this.courseName + "\nCourse Description: " + this.courseDescription + "\nCourse Hours: " + this.courseHours + "\nCourse UUID: " + this.courseUUID;
    }

    public String reqsToString()
    {
        String retString = "";
        if(coursePrereqUUID.size() > 0)
        {
            retString += "Prereq:\n";
            for (int i = 0; i < coursePrereqUUID.size(); i++)
            {
                HashMap<UUID, String> temp = coursePrereqUUID.get(i);
                for (Map.Entry<UUID, String> entry : temp.entrySet())
                {
                    retString += (entry.getKey()).toString() + " " + entry.getValue() + " AND ";
                }
                retString = retString.substring(0, retString.length() - 5);
                if (i < coursePrereqUUID.size() - 1)
                    retString += "\nOR\n";
            }
        }
        if(courseCoreqUUID.size() > 0)
        {
            retString += "\nCoreqs:\n";
            for (int i = 0; i < courseCoreqUUID.size(); i++)
            {
                HashMap<UUID, String> temp = courseCoreqUUID.get(i);
                for (Map.Entry<UUID, String> entry : temp.entrySet())
                {
                    retString += (entry.getKey()).toString() + " " + entry.getValue() + " AND ";
                }
                retString = retString.substring(0, retString.length() - 5);
                if (i < courseCoreqUUID.size() - 1)
                    retString += "\nOR\n";
            }
        }
        return retString;
    }

}
