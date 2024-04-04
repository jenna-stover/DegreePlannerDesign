package degreeplanner.design.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class CourseList
{
    private static CourseList courseList;
    private static HashMap<UUID, Course> courses; // courseUUID, course
    private static HashMap<String, UUID> courseNames; //courseID, courseUUID

    private CourseList()
    {
        courses = new HashMap<UUID, Course>();
        courseNames = new HashMap<String, UUID>();
        ArrayList<Course> temp = ReadFile.readCourses();
        for (Course course : temp) 
        {
            UUID temp2 = course.getCourseUUID();
            String temp3 = course.getCourseID();
            courses.put(temp2, course);
            courseNames.put(temp3, temp2);
        }
        
    }

    public static CourseList getInstance()
    {
        if(courseList == null)
        {
            courseList = new CourseList();
        }
        return courseList;
    }

    //added this method for test class
    public static void resetInstance(){
        courseList = null;
        courses.clear();
        courseNames.clear();
    }

    /**
     * looks up the associated UUID using courseID from courseNames HashMap, 
     * uses the UUID to directly access the Course object associated with the courseID
     * @param courseID
     * @return
     */
    public static Course getCourse(String courseID)
    {
        UUID courseUUID = courseNames.get(courseID);

        if(courseUUID != null){
            return courses.get(courseUUID); 
        }
        return null;
    }

    /** 
     * uses the UUID to directly access the Course object associated with the courseUUID
     * this method should only be used for back end programming
     * @param courseUUID
     * @return
     */
    public static Course getCourseByUUID(UUID courseUUID)
    {
        if(courseUUID != null){
            return courses.get(courseUUID); 
        }
        return null;
    }

    /**
     * accesses the full list of courses
     * @return an ArrayList containing all of the Course objects
     */
    public ArrayList<Course> getCourses()
    {
        ArrayList<Course> temp = new ArrayList<Course>();
        for(Map.Entry<UUID, Course> entry : courses.entrySet())
        {
            temp.add(entry.getValue());
        }
        return temp;
    }

   /**
     * adds a new course to the course list
     * @param newCourse
     * @return true if the course was successfully added, and false if the course already exists
     */
    public boolean addCourse(Course newCourse)
    {
        UUID newCourseUUID = newCourse.getUUID();
        String newCourseID = newCourse.courseID;

        if(!courses.containsKey(newCourseUUID) && !courseNames.containsKey(newCourseID)){
            courses.put(newCourseUUID, newCourse);
            courseNames.put(newCourseID, newCourseUUID);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeCourse(String courseID)
    {
        UUID courseUUID = courseNames.get(courseID);
        if(courses.containsKey(courseUUID) && courseNames.containsKey(courseID))
        {
            courses.remove(courseUUID);
            courseNames.remove(courseID);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * checks if the user has permission to edit courses, and directly accesses public fields
     * to edit the details of an existing course
     * @param courseID
     * @param userType
     * @param newName
     * @param newDescription
     * @return
     */
    public boolean editCourse(String courseID, UserType userType, String newName, String newDescription)
    {
        if(userType == UserType.ADVISOR || userType == UserType.PROFESSOR){
            UUID courseUUID = courseNames.get(courseID);
            if(courseUUID != null){
                Course course = courses.get(courseUUID);
                if(course != null){
                    if(newName != null){
                        course.courseName = newName;
                    }
                    if(newDescription != null){
                        course.courseDescription = newDescription;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
