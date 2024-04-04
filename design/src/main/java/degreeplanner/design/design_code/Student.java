package degreeplanner.design.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

// import testClasses.AdvisementPlan;
// import testClasses.DegreeList;
// import testClasses.DegreePlan;
// import testClasses.Elective;
// import testClasses.Major;
// import testClasses.Warnings;

public class Student extends User
{
    //public DegreePlan degreePlan; //Old attrubute
    //public User appointedAdvisor; //maybe find a way to put this in as a string, if done have to add that to constructor
    public ArrayList<AdvisementPlan> advisementPlans;
    public double GPA;
    public boolean hasScholarships;
    public ArrayList<Course> allCourses;  //Not read nor saved
    public HashMap<Course, String> completedCourses; //Course course, String courseGrade
    public ArrayList<Course> currentCourses; // Course course
    public ArrayList<Course> incompleteCourses; // Course course
    public ArrayList<Warnings> warnings;
    public int completedHours;
    public int incompleteHours; //added this to calculate totalDegreeHours
    public int currentHours;
    public int currentSemester = 4;  //Hard coded for now, needs to be read and saved
    public int totalDegreeHours = 130;  //Hard coded for now, can either be read and saved or determined at creation.
    public ArrayList<ArrayList<Course>> eightSemesterPlan;  //Not read nor normally saved. Can only be saved after creation is made
    public Major currentMajor;

    public Student(UUID userUUID, String firstName, String lastName, String userEmail, String userPass, 
        String userID, UserType userType, HashMap<Course, String> completedCourses, ArrayList<Course> currentCourses, ArrayList<Course> incompleteCourses, ArrayList<Warnings> warnings,
        int completedHours, int currentHours, ArrayList<UUID> advisementPlans, double GPA, boolean hasScholarships, Major currMajor)
    {
        super(userUUID, firstName, lastName, userEmail, userPass, userID, userType);
        // this.degreePlan = studentDegreePlan;
        // this.advisementPlans = advisementPlans;
        this.GPA = GPA;
        this.hasScholarships = hasScholarships;
        this.eightSemesterPlan = eightSemesterPlan;
        this.completedCourses = completedCourses;
        this.currentCourses = currentCourses;
        this.incompleteCourses = incompleteCourses;
        this.completedHours = completedHours;
        this.currentHours = completedHours;
        this.currentMajor = currMajor;
        this.warnings = warnings;
    }  

    public boolean updateGPA() //should this have return type double or bool
    {
        return true;
    }

    public double getGPA()
    {
        return GPA;
    }

    // public DegreePlan getDegreePlan()
    // {
    //     return degreePlan;
    // }

    public ArrayList<AdvisementPlan> getAdvisementPlans()
    {
        return null;
    }

    public void setAdvisementPlan() //should have params?
    {

    }

    public void renderAdvisementPlanList()
    {

    }

    public void displayDegreePlan()
    {
        
    }

    public Course searchCourse(String courseID) //should this param be uuid?
    {
        for(Course course : getAllCourses()){
            if(course.getCourseID().equals(courseID)){
                return course;
            }
        }
        return null;
    }

    public void renderCourseDetails(String courseID)
    {

        Course course = searchCourse(courseID); 
        if (course != null) {
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Description: " + course.getCourseDescription());
            System.out.println("Credit Hours: " + course.getCourseHours());
            if (course.getPrereqStatus(course) || course.getCoreqStatus(course)) {
                System.out.println("Requisites: " + course.reqsToString()); 
            }
        }
        else{
            System.out.println("Course with ID " + courseID + " not found.");
        }
    }

    public ArrayList<Course> getCurrentCourses() //param uuid?
    {
        return currentCourses;
    }

    public HashMap<Course, String> getCompletedCourses()
    {
        return completedCourses;
    }

    public ArrayList<Course> getIncompleteCourses()
    {
        return incompleteCourses;
    }

    public ArrayList<Course> getAllCourses()
    {
        allCourses = new ArrayList<Course>();
        for (Course course : completedCourses.keySet())
        {
            allCourses.add(course); //adds all completed courses to arraylist of all courses
        }
        for(Course course : currentCourses)
        {
            allCourses.add(course);
        }
        for(Course course : incompleteCourses)
        {
            allCourses.add(course);
        }
        return allCourses;
    }

    public boolean isElectiveComplete(Elective elective)
    {
        int tally = 0;
        for(Course tempCourse : elective.getOptions())
        {
            if(completedCourses.containsValue(tempCourse))
            {
                tally+= tempCourse.getCourseHours();
            }
        }
        if(tally>= elective.credits)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    //compares student's current/prev courses to skeleton degree plan
    public void renderCurrentPlan()
    {
        //get skeleton and loop through to compare each course to the students current/prev courses, if not taken, put in students degree plan
        //if already taken, move a class up.
        eightSemesterPlan = new ArrayList<ArrayList<Course>>();
        DegreeList degreelist = DegreeList.getInstance();
        DegreePlan degree = degreelist.getDegree(currentMajor); //gets the skeleton
        //loop thru semesters leading up to current
        for (int i = 0; i < currentSemester; i++) 
        {
            ArrayList<Course> semester = new ArrayList<Course>();
            for (int j = 0; j < degree.semesterCourses.get(i).getCourses().size(); j++) //looping thru each individual semester + its courses??
            {
                
                for(int k = 0; k < degree.semesterCourses.get(i).getCourses().get(j).size(); k++) //each individual choice
                {
                    if (completedCourses.keySet().contains(degree.semesterCourses.get(i).getCourses().get(j).get(k))) //we found the course that we took
                    {
                        Course tempCourse; //temp arraylist bc we need to select a specific course out of the options
                        tempCourse = degree.semesterCourses.get(i).getCourses().get(j).get(k);
                        semester.add(tempCourse); //adds to the students 8semester
                    }
                }
                
            }
            eightSemesterPlan.add(semester);
        }
        //have to loop through semesters remaining
        for (int i = currentSemester; i < 8; i++) //loop through current semesters through last semester
        {
            ArrayList<Course> semester = new ArrayList<Course>();
            for(int j = 0; j < degree.semesterCourses.get(i).getCourses().size(); j++) //each semester
            {
                boolean taken = false;
                
                for(int k = 0; k < degree.semesterCourses.get(i).getCourses().get(j).size(); k++) //each individual choice
                {  
                    if(completedCourses.keySet().contains(degree.semesterCourses.get(i).getCourses().get(j).get(k))) // we completed that course already, find a replacement
                    {
                        //fill gaps with electives, use isElectiveComplete to loop through all electives and see which are not satisfied yet,
                        //whichever are not satisfied, select electives that have not been taken from that category and add in that gap
                        // for (int a = 0; a < degree.)
                        // {
                        //     if(isElectiveComplete(null))
                        // }    
                        //taken = true;
                    }
                    else if(!taken && k == degree.semesterCourses.get(i).getCourses().get(j).size() - 1) //need to take that course/its not in the completed courses set
                    {
                        Course tempCourse = degree.semesterCourses.get(i).getCourses().get(j).get(0); //adds the first choice out of the choices to 8semester
                        semester.add(tempCourse); //adds to the students 8semester
                    }
                }
                
            }
            eightSemesterPlan.add(semester);
        }
    }

    public String eightSemesterPlanToString()
    {
        renderCurrentPlan();
        String result = "--EightSemesterPlan--\n";
        for(ArrayList<Course> semester : eightSemesterPlan)
        {
            result += "Semester " + (eightSemesterPlan.indexOf(semester)+1) + ":\n";
            for(Course course : semester)
            {
                result += "Course ID: " + course.getCourseID() + " Course Name: " + course.getCourseName() + " Credits: " + course.getCourseHours() + "\n"; 
            }
            result += "\n";
        }
        return result;
    }
    public int getCurrentSemester()
    {
        int semester = 0;
        for (int i = 0; i < 8; i++) {
            ArrayList<Course> semesterCourses = eightSemesterPlan.get(i);
            if(checkForCurrentCourse(semesterCourses)) {
                semester = i + 1;
            }
        }
        return semester;
    }

    /**
     * helper method for getCurrentSemester() to find a current course within the semesterCourses ArrayList
     * @param semesterCourses
     * @return
     */
    private boolean checkForCurrentCourse (ArrayList<Course> semesterCourses) {
        for (Course course : semesterCourses) {
            if (currentCourses.contains(course)) {
                return true;
            }
        }
        return false;
    }

    //takes rendered current plan and puts into a text file
    public ArrayList<ArrayList<Course>> generatePlan(Major major)
    {
        switch(major){
            case COMPUTER_SCIENCE:
                
                break;
            case COMPUTER_INFORMATION_SYSTEMS:
                break;
        }
        
        return eightSemesterPlan;
    }

    public void generateWarnings(){
        warnings.clear();
        

        if(GPA < 2.5){
            warnings.add(Warnings.INSUFFICIENT_DEGREE_GPA);
        }

        if(hasScholarships && GPA < 3.0){
            warnings.add(Warnings.INSUFFICIENT_SCHOLARSHIP_GPA);
        }

        if(currentHours < 12){
            warnings.add(Warnings.SCHOLARSHIP_REQUIREMENTS_NOT_MET);
        }

        for(Course course : currentCourses){
            String receivedGrade = course.getReceivedGrade(course);
            if(receivedGrade == "F"){
                warnings.add(Warnings.FAILING_CLASS);
            }
            break;
        }

        // for(Course course : currentCourses){
            
        // }
    }

    //update the hours for student's completed, current, incompleted, and total courses
    //call this method when adding/removing courses
    public void updateHours()
    {
        completedHours = 0;
        for(Course course : completedCourses.keySet()){
            completedHours += course.getCourseHours();
        }

        currentHours = 0;
        for(Course course : currentCourses) {
            currentHours += course.getCourseHours();
        }

        incompleteHours = 0;
        for(Course course : incompleteCourses){
            incompleteHours += course.getCourseHours();
        }

        totalDegreeHours = completedHours + currentHours + incompleteHours;
    }

    public double getDegreeProgress()
    {
        return((double)completedHours/totalDegreeHours) * 100; //returns as a percentage
    }

    public String toString()
    {
        return "This Student's UUID: " + userUUID + "\nStudent Name: " + firstName + lastName + "\nStudent Email: " + userEmail + 
        "\nStudent ID: " + this.getUserID() + "\n" + firstName + "'s GPA: " + GPA + "\nDoes " + firstName + " Have a Scholarship? " + 
        hasScholarships + "Not Shown: User Password, Student's DegreePlan, and Student's AdvisementPlans.";
    }

    public String progressToString()
    {
        String result = "";
        // if(warnings.size() > 0)
        // {
        //     for(int i = 0; i < warnings.size(); i++)
        //         result += "!Warning! -" + (warnings.get(i)).toString() + "-\n";
        // }
        result += "-Completed Courses-\n";
        for(Course course : completedCourses.keySet())
        {
            result += "Course ID: " + course.getCourseID() + " Grade Achieved: " + completedCourses.get(course) + "\n";
        }
        result += "-Current Courses-\n";
        for(Course course : currentCourses)
        {
            result += "Course ID: " + course.getCourseID() + " Course Name: " + course.getCourseName() + "\n";
        }
        result += "-Courses to take-\n";
        for(Course course : incompleteCourses)
        {
            result += "Course ID: " + course.getCourseID() + " Course Name: " + course.getCourseName() + "\n";
        }
        result += "Student's Progress: " + getDegreeProgress() + "%";
        return result;
    }

//    public Tabs selectView()
//      return null;
    

//    public void renderView(Tabs tab){}
}
