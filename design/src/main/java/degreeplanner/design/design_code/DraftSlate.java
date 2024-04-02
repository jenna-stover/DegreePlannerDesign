package testClasses;
import java.util.ArrayList;

import testClasses.Course;

public class DraftSlate {
    public ArrayList<Course> userSelectedCourses;

    public DraftSlate()
    {

    }

    public boolean generatePDF(DraftSlate draftSlate, String fileName)
    {
        return true;
    }
    
    public void generateEmptyTable()
    {

    }

    public DegreePlan generateDraft(DegreePlan completedCourses, DegreePlan incompleteCourses,
        DegreePlan currentCourses, ArrayList<Course> userSelectedCourses)
    {
        return currentCourses;
    }

    public void autoLogout()
    {

    }

    public boolean selectNewMajor(Major major)
    {
        return true;
    }

    public boolean selectNewMinor(Major minor)
    {
        return true;
    }

    public boolean selectNewConcentration(Concentration concentrations)
    {
        return true;
    }

    public Major searchMajor(Major major)
    {
        return major;
    }

    public Course searchCourse(Course courseNumber)
    {
        return courseNumber;
    }

    public boolean addCourse(Course course)
    {
        return true;
    }

    public void renderCourseDetails(String courseID)
    {

    }

}
