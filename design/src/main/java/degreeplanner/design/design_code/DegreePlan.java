package testClasses;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import testClasses.Course;

public class DegreePlan 
{ 
    public Major currentMajor;
    public ArrayList<Semester> semesterCourses;
    public ArrayList<Elective> degreeElectives;
    public ArrayList<ApplicationArea> degreeApplicationArea;
    private ArrayList<Course> courses;

    public DegreePlan(Major major, ArrayList<Semester> inCourses, ArrayList<Elective> inElectives, ArrayList<ApplicationArea> inAppArea)
    {
        this.currentMajor = major;
        this.semesterCourses = inCourses;
        this.degreeElectives = inElectives;
        this.degreeApplicationArea = inAppArea;
    }
    public DegreePlan(Major major, ArrayList<Course> courses) {
        this.currentMajor = major;
        this.courses = courses;
    }

    /**
     * creates the generalized degree plan; includes semester courses, degree electives, and application areas (if applicable)
     * @return a String representation of the generalized degree plan
     */
    public String renderDegreePlan()
    {
        StringBuilder sb = new StringBuilder("General DegreePlan for Major: " + currentMajor + "\n");

        sb.append("Semester Courses:\n");

        for(int i = 0; i < 8; i++){
            Semester semester = semesterCourses.get(i);
            sb.append("Semester ").append(semester.getSemesterNum()).append(":\n");
            for(ArrayList<Course> coursesInSem : semester.getCourses())
            {
                for(int k = 0; k<coursesInSem.size(); k++)
                {
                    Course course = coursesInSem.get(k);
                    sb.append("\tCourse ID: ").append(course.getCourseID())
                    .append(", Name: ").append(course.getCourseName())
                    .append(", Credit Hours: ").append(course.getCourseHours());

                    if(k < coursesInSem.size()-1){
                        sb.append("OR");
                    }
                    else{
                        sb.append("\n");
                    }
                }
            }
        }
            
        sb.append("Degree Electives:\n");
        for(Elective elective : this.degreeElectives) 
        {
            sb.append("\tElective Type: ").append(elective.type)
            .append(", Credits: ").append(elective.credits)
            .append("\n\tOptions:\n");

            for(Course course : elective.getOptions()) 
            {
                sb.append("\t\t").append(course.getCourseName())
                .append(" (").append(course.getCourseID()).append(")\n");
            }
        }

        sb.append("Application Areas:\n");
        for(ApplicationArea appArea : this.degreeApplicationArea) {
            sb.append("\t").append(appArea.ApplicationAreaName).append("\n"); 
        }

        return sb.toString();     
    }

    public String toString()
    {
       return renderDegreePlan();
    }
}
