package degreeplanner.design_code;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.Property;


public class AdvisementPlan
{
    private UUID AdvisementPlanUUID;
    private User student; //advisee
    private User advisor;
    public ArrayList<Course> courses;
    public String notes;
    public String title;
    public LocalDate date;
    public SimpleStringProperty note;
    public SimpleStringProperty advisee;


    public AdvisementPlan(UUID planID, User student, User advisor, ArrayList<Course> courses, String attachedNotes, String title, LocalDate date)
    {
        this.AdvisementPlanUUID = planID;
        this.student = student;
        this.advisor = advisor;
        this.courses = courses;
        this.notes = attachedNotes;
        this.title = title;
        this.date = date;
    }

    public AdvisementPlan(UUID planID, User student, User advisor, String attachedNotes)
    {
        this.AdvisementPlanUUID = planID;
        this.student = student;
        this.advisor = advisor;
        this.notes = attachedNotes;
        this.date = LocalDate.now();
    }

    public AdvisementPlan(User student, String attachedNotes, LocalDate inDate)
    {
        this.AdvisementPlanUUID = UUID.randomUUID();
        this.student = student;
        this.notes = attachedNotes;
        this.date = inDate;
        this.note = new SimpleStringProperty(notes);
        this.advisee = new SimpleStringProperty(student.getUserFullName());
    }

    public Property<String> noteProperty()
    {
        return note;
    }

    public Property<String> adviseeProperty()
    {
        return advisee;
    }

    public UUID getPlanID()
    {
        return this.AdvisementPlanUUID;
    }

    public User getPlanStudent()
    {
        return this.student;
    }

    public User getPlanAdvisor()
    {
        return this.advisor;
    }

    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    public String getNotes()
    {
        return this.notes;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public String getDateToString()
    {
        return this.date.toString();
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