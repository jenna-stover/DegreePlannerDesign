package degreeplanner.design.design_code;
public abstract class DataConstants {
    //start of student.json properties
    protected static final String STUDENT_FILE_NAME = "jsonFiles/student.json";
    protected static final String USER_UUID = "userUUID";
    protected static final String USER_ID = "userID";
    protected static final String FIRSTNAME = "firstName";
    protected static final String LASTNAME = "lastName";
    protected static final String USER_EMAIL = "userEmail";
    protected static final String USER_PASSWORD = "userPassword";
    protected static final String USER_TYPE = "userType";
    protected static final String DEGREE_PLAN = "degreePlan";
    protected static final String COMPLETED_COURSES = "completedCourses";
    protected static final String COURSE_UUID = "courseUUID";
    protected static final String GRADE = "grade";
    protected static final String CURRENT_COURSES = "currentCourses";
    protected static final String INCOMPLETE_COURSES = "incompleteCourses";
    protected static final String WARNINGS = "warnings";
    protected static final String COMPLETED_HOURS = "completedHours";
    protected static final String CURRENT_HOURS = "currentHours";
    protected static final String TOTAL_DEGREE_HOURS = "totalDegreeHours";
    protected static final String CURRENT_MAJOR = "currentMajor";
    protected static final String CURRENT_MINOR = "currentMinor";
    protected static final String CURRENT_CONCENTRATION = "currentConcentration";
    protected static final String APPOINTED_ADVISOR = "appointedAdvisor";
    protected static final String ADVISEMENT_PLAN = "advisementPlan";
    protected static final String GPA = "GPA";
    protected static final String HAS_SCHOLARSHIPS = "hasScholarships";
    //end of student.json properties
    //start of facutly.json properties
    //Note: userUUID, firstName, lastName, userEmail, userPassword, and userType are already listed in the listing
    // of student.json properties
    protected static final String FACULTY_FILE_NAME = "jsonFiles/faculty.json";
    protected static final String ADVISING_STUDENTS = "advisingStudents";
    protected static final String COURSES_INSTRUCTING = "coursesInstructing";
    //end of faculty.json properties
    //start of advisement_plan.json properties
    protected static final String ADVISEMENT_PLAN_FILE_NAME = "jsonFiles/advisement_plan.json";
    protected static final String PLANID = "planID";
    protected static final String STUDENT_UUID = "studentUUID";
    protected static final String ADVISOR_UUID = "advisorUUID";
    protected static final String ADVISED_COURSES = "advisedCourses";
    protected static final String ATTACHED_NOTES = "attachedNotes";
    //end of advisement_plan.json properties
    //start of course.json properties
    //Note: courseUUID property is already listed in the degreePlan section of student.json
    protected static final String COURSE_FILE_NAME = "jsonFiles/course.json";
    protected static final String COURSE_ID = "courseID";
    protected static final String COURSE_NAME = "courseName";
    protected static final String COURSE_DESCRIPTION = "courseDescription";
    protected static final String COURSE_PREREQ = "coursePrereq";
    protected static final String COURSE_COREQ = "courseCoreq";
    //Note: courseUUID and grade is already listed in the degreePlan section of student.json
    protected static final String ORNEXT = "ORnext";
    protected static final String COURSE_HOURS = "courseHours";
    protected static final String REQUIRED_GRADE = "requiredGrade";
    protected static final String SEMESTER_PROVIDED = "semesterProvided";
    //start of degree.json properties
    protected static final String DEGREE_FILE_NAME = "jsonFiles/degree.json";
    protected static final String MAJOR_NAME = "major_name";
    protected static final String REQUIRED_COURSES = "required_courses";
    protected static final String CHOICES = "choices";
    protected static final String ELECTIVES = "electives";
    protected static final String TYPE = "type";
    protected static final String CREDITS = "credits";
    protected static final String APPLICATION_AREA = "application_areas";
    protected static final String APPLICATION_AREA_NAME = "application_area_name";
    protected static final String COURSES = "courses";
}
