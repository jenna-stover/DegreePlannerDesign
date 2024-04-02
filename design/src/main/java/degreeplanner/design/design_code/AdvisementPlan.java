package testClasses;
import java.util.ArrayList;
import java.util.UUID;

import testClasses.Course;
import testClasses.User;

public class AdvisementPlan
{
    private UUID AdvisementPlanUUID;
    private User student;
    private User advisor;
    //public ArrayList<Course> courses;
    public String notes;

    public AdvisementPlan(UUID planID, UUID studentUUID, UUID advisorUUID, ArrayList<UUID> courses,String attachedNotes)
    {

    }

    public AdvisementPlan(UUID planID, UUID studentUUID, UUID advisorUUID, String attachedNotes)
    {

    }

    public void renderListView(AdvisementPlanList advisementPlanList)
    {

    }

    public void renderAdvisementPlan()
    {

    }

    public void renderEditPlan(AdvisementPlan advisementPlan)
    {

    }


    public AdvisementPlan renderNewPlan()
    {
        return null;
    }

    // return a course?
    public Course searchCourse(String courseID)
    {
        return null;
    }

    public void renderCourseTable(Course course)
    {

    }

    public void saveCourse(Course course)
    {

    }

    public String renderNotesEntry()
    {

        return "";
    }


    public AdvisementPlan savePlan(AdvisementPlan advisementPlan)
    {

        return null;
    }

    public void generatePDF(AdvisementPlan advisementPlan)
    {

    }

    // obsolete because of renderCourseTable? also should take course object parameter
    public void renderCourseDetails(String courseID)
    {

    }
}