package degreeplanner.design_code;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import degreeplanner.design_code.DataConstants;
import degreeplanner.design_code.User;
import degreeplanner.design_code.Course;
import degreeplanner.design_code.CourseList;
import degreeplanner.design_code.UserType;
import degreeplanner.design_code.Warnings;
import degreeplanner.design_code.Major;
import degreeplanner.design_code.Student;
import degreeplanner.design_code.Faculty;
import degreeplanner.design_code.DegreePlan;
import degreeplanner.design_code.AdvisementPlan;
import degreeplanner.design_code.ApplicationArea;
import degreeplanner.design_code.Elective;
import degreeplanner.design_code.Semester;

/**
 * This reads in the following files = course.json, degree.json, student.json, faculty.json, advisement_plan.json
 * The methods need to be executed in the following order readCourses(), readUsers(), readDegreePlan(), readAdvisementPlan()
 * The reason for method order is later methods depend on earlier methods being executed first.
 * @author Christopher Schweninger
 */
public class ReadFile extends DataConstants 
{

	/**
	 * This will execute both readStudents() and readFaculty();
	 * @return The list of all users
	 */
	public static ArrayList<User> readUsers()  // Second & Third
	{
		ArrayList<User> allUsers = new ArrayList<User>();
		ArrayList<User> students = readStudents();
		ArrayList<User> faculty = readFaculty();
		// Check if students or faculty is null
		if (students != null) 
		{
			for (User student : students) 
			{
				allUsers.add(student);
			}
		}
		//Check if students or faculty is null
		if (faculty != null) 
		{
			for (User facultyMember : faculty) 
			{
				allUsers.add(facultyMember);
			}
		}	
		return allUsers;
	}
	/**
	 * This reads in all students in student.json
	 * @return The list of all students
	 */
    public static ArrayList<User> readStudents()  // Second
	{
		ArrayList<User> students = new ArrayList<User>();
		
		try 
		{
			FileReader reader = new FileReader(STUDENT_FILE_NAME);  // This finds the file
			JSONParser parser = new JSONParser();	
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);  // This reads in the whole list of students in student.json
			CourseList tempCList = CourseList.getInstance();
			
			for(int i=0; i < peopleJSON.size(); i++)  // This iterates (progressively goes through each) student in the list of students
			{
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);  // This reads in a specific student
				UUID uuid = UUID.fromString((String)personJSON.get(USER_UUID));  // String -> UUID
				String id = (String)personJSON.get(USER_ID);  // String -> String
				String firstName = (String)personJSON.get(FIRSTNAME);
				String lastName = (String)personJSON.get(LASTNAME);
				String userEmail = (String)personJSON.get(USER_EMAIL);
				String userPassword = (String)personJSON.get(USER_PASSWORD);
				UserType userType = UserType.valueOf((String)personJSON.get(USER_TYPE));  // String -> UserType
				//DegreePlan stuDegreePlan = readDegreePlan((JSONObject)personJSON.get(DEGREE_PLAN));  // JSONObject -> DegreePlan (Object)
				// Student Specific information
				HashMap<Course, String> compCourses = new HashMap<Course, String>();
				compCourses = readCompCourse((JSONArray)personJSON.get(COMPLETED_COURSES), tempCList);  // JSONArray of JSONObjects -> HashMap<UUID, String>
				ArrayList<String> currentCoursesID = (JSONArray)personJSON.get(CURRENT_COURSES);
				ArrayList<Course> currentCourses = new ArrayList<Course>();
				for (String courseID : currentCoursesID)
				{
					currentCourses.add(tempCList.getCourse(courseID));
				}
				ArrayList<String> incompleteCoursesID = (JSONArray)personJSON.get(INCOMPLETE_COURSES);
				ArrayList<Course> incompleteCourses = new ArrayList<Course>();
				for (String incourseID : incompleteCoursesID)
				{
					incompleteCourses.add(tempCList.getCourse(incourseID));
				}
				//ArrayList<Warnings> warnings = (JSONArray)personJSON.get(WARNINGS);
				ArrayList<String> tempWarn = (JSONArray)personJSON.get(WARNINGS);
				ArrayList<Warnings> warnings = new ArrayList<Warnings>();
				for (String temp : tempWarn)
				{
					warnings.add(Warnings.valueOf(temp));
				}
				int completedHours = ((Long)personJSON.get(COMPLETED_HOURS)).intValue();  // String -> int
				int currentHours = ((Long)personJSON.get(CURRENT_HOURS)).intValue();
				//UUID appAdvisor = UUID.fromString((String)personJSON.get(APPOINTED_ADVISOR));
				// ArrayList<UUID> advisementPlans = (JSONArray)personJSON.get(ADVISEMENT_PLAN);  // JSONArray -> ArrayList<advisement_plan_uuids>
				ArrayList<String> tempPlans = (JSONArray)personJSON.get(ADVISEMENT_PLAN);
				ArrayList<UUID> advisementPlans = new ArrayList<UUID>();
				for (String temp : tempPlans)
				{
					advisementPlans.add(UUID.fromString(temp));
				}
				double gpa = ((Double)personJSON.get(GPA)).doubleValue();  // String -> Double
				boolean hasScholarships = (Boolean)personJSON.get(HAS_SCHOLARSHIPS);  // String -> Boolean
				Major currMajor = Major.valueOf((String)personJSON.get(CURRENT_MAJOR));
				
				// Student constructor with the read in details, as well as adding that student to the arrayList of students
				students.add(new Student(uuid, firstName, lastName, userEmail, userPassword, id, userType, compCourses, currentCourses, incompleteCourses, warnings, completedHours, currentHours, advisementPlans, gpa, hasScholarships, currMajor));

			}
			
			return students;
			
		} 
		catch (Exception e)  // If something goes wrong throws this error
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<DegreePlan> readDegreePlan()
	{
		ArrayList<DegreePlan> degrees = new ArrayList<DegreePlan>();
		try
		{
			FileReader reader = new FileReader(DEGREE_FILE_NAME);  // This finds the file
			JSONParser parser = new JSONParser();	
			JSONArray degreesJSON = (JSONArray)new JSONParser().parse(reader);  // This reads in the whole list of degrees in degree.json
			CourseList tempCList = CourseList.getInstance();
			for(int i = 0; i < degreesJSON.size(); i++)  // This iterates over each degree
			{
				JSONObject degreeJSON = (JSONObject)degreesJSON.get(i);
				Major major = Major.valueOf((String)degreeJSON.get(MAJOR_NAME));
				ArrayList<Semester> degreeSemesters = new ArrayList<Semester>();
				JSONArray degreeCourses = (JSONArray)degreeJSON.get(REQUIRED_COURSES);
				for (int j = 0; j < degreeCourses.size(); j++)  // This iterates over each semester
				{
					JSONArray semesterCourses = (JSONArray)degreeCourses.get(j);
					ArrayList<ArrayList<Course>> semester = new ArrayList<ArrayList<Course>>();
					for (int k = 0; k < semesterCourses.size(); k++) // This gets the courses and their options for each semester
					{
						JSONObject choicesJSON = (JSONObject)semesterCourses.get(k);
						ArrayList<String> choicesNames = (JSONArray)choicesJSON.get(CHOICES);
						ArrayList<Course> choices = new ArrayList<Course>();
						for (String name : choicesNames)
						{
							choices.add(tempCList.getCourse(name));
						}
						semester.add(choices);
					}
					Semester tempSemester = new Semester(j + 1, semester);
					degreeSemesters.add(tempSemester);
				}
				ArrayList<Elective> degreeElects = new ArrayList<Elective>();
				JSONArray degreeElectives = (JSONArray)degreeJSON.get(ELECTIVES);
				for (int j = 0; j < degreeElectives.size(); j++)
				{
					JSONObject electiveJSON = (JSONObject)degreeElectives.get(j);
					String type = (String)electiveJSON.get(TYPE);
					int credits = ((Long)electiveJSON.get(CREDITS)).intValue();
					ArrayList<String> choicesNames = (JSONArray)electiveJSON.get(CHOICES);
					ArrayList<Course> choices = new ArrayList<Course>();
					for (String name : choicesNames)
					{
						choices.add(tempCList.getCourse(name));
					}
					Elective tempElective = new Elective(type, credits, choices);
					degreeElects.add(tempElective);
				}
				ArrayList<ApplicationArea> degreeAppAreas = new ArrayList<ApplicationArea>();
				JSONArray degreeApplicationAreaJSON = (JSONArray)degreeJSON.get(APPLICATION_AREA);
				for (int j = 0; j < degreeApplicationAreaJSON.size(); j++)
				{
					JSONObject appAreaJSON = (JSONObject)degreeApplicationAreaJSON.get(j);
					String appAreaName = (String)appAreaJSON.get(APPLICATION_AREA_NAME);
					ArrayList<String> coursesNames = (JSONArray)appAreaJSON.get(COURSES);
					ArrayList<Course> courses = new ArrayList<Course>();
					for (String name : coursesNames)
					{
						courses.add(tempCList.getCourse(name));
					}
					ApplicationArea tempAppArea = new ApplicationArea(appAreaName, courses);
					degreeAppAreas.add(tempAppArea);
				}
				DegreePlan tempPlan = new DegreePlan(major, degreeSemesters, degreeElects, degreeAppAreas);
				degrees.add(tempPlan);
			}
			return degrees;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static HashMap<Course, String> readCompCourse(JSONArray compCoursesIn, CourseList CList)  // compCoursesIn = JSONArray of JSONObjects. The JSONObjects = courseUUID & recievedGrade
	{
		HashMap<Course, String> tempMap;
		try
		{
			JSONArray compCourses = compCoursesIn;
			tempMap = new HashMap<Course, String>();
			for (int i = 0; i < compCourses.size(); i++)  // Getting each course and its recieved grade
			{
				JSONObject compCourse = (JSONObject)compCourses.get(i);
				String courseID = (String)compCourse.get(COURSE_ID);
				Course tempCourse = CList.getCourse(courseID);
				String grade = (String)compCourse.get(GRADE);

				tempMap.put(tempCourse, grade);  // Adding it to the HashMap
			}
			return tempMap;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<User> readFaculty()  // Third
	{
		ArrayList<User> faculty = new ArrayList<User>();
		try 
		{
			FileReader reader = new FileReader(FACULTY_FILE_NAME);	
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) 
			{
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				UUID uuid = UUID.fromString((String)personJSON.get(USER_UUID));
				String id = (String)personJSON.get(USER_ID);
				String firstName = (String)personJSON.get(FIRSTNAME);
				String lastName = (String)personJSON.get(LASTNAME);
				String userEmail = (String)personJSON.get(USER_EMAIL);
				String userPassword = (String)personJSON.get(USER_PASSWORD);
				UserType userType = UserType.valueOf((String)personJSON.get(USER_TYPE));  // Decides what information needs to be read in.
				ArrayList<UUID> readInUUID = new ArrayList<UUID>();
				//readinAdvisingStudents
				if (userType.toString() == "ADVISOR")  // IF Advisor userType, read in Advising_Students JSONArray.
				{
					ArrayList<String> tempUUID = (JSONArray)personJSON.get(ADVISING_STUDENTS);
					for (String tempuuid : tempUUID)
					{
						readInUUID.add(UUID.fromString(tempuuid));
					}
				}
				//endReadInAdvisingStudents
				//readinCoursesInstructing
				if (userType.toString() == "PROFESSOR")  // IF Professor userType, read in Courses_Instructing JSONArray.
				{
					ArrayList<String> tempUUID = (JSONArray)personJSON.get(COURSES_INSTRUCTING);
					for (String tempuuid : tempUUID)
					{
						readInUUID.add(UUID.fromString(tempuuid));
					}
				}
				//endReadInCoursesInstructing
				
				faculty.add(new Faculty(uuid, firstName, lastName, userEmail, userPassword,
				id, userType, readInUUID));
			}
			
			return faculty;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public static ArrayList<Course> readCourses()  // First
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		try
		{
			FileReader reader = new FileReader(COURSE_FILE_NAME);	
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < coursesJSON.size(); i++)
			{
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
				UUID courseUUID = UUID.fromString((String)courseJSON.get(COURSE_UUID));
				String courseID = (String)courseJSON.get(COURSE_ID);
				String courseName = (String)courseJSON.get(COURSE_NAME);
				String courseDescription = (String)courseJSON.get(COURSE_DESCRIPTION);
				ArrayList<HashMap<UUID, String>> coursePrereq, courseCoreq;  // Req layout = & requirement -> ArrayList member, OR requirement -> HashMap member
				// Each OR requirement will be in a HashMap, and each AND requirement will be the member of an ArrayList (which is a HashMap)
				// For example req: CSCE 001 (B+) & CSCE 002 (B) OR CSCE 003 (C+)
				// ArrayList<HashMap> = HashMap1, HashMap2
				// HashMap1<CourseID, Grade> = CSCE 001, B+
				// HashMap2<CourseID, Grade> = CSCE 002, B ; CSCE 003, C+
				JSONArray temp = (JSONArray)courseJSON.get(COURSE_PREREQ);
				if (temp.size() > 0 && !temp.isEmpty())
				{
					coursePrereq = readReq(temp);  // JSONArray of JSONObjects(Prereqs) -> ArrayList<HashMap<UUID, String>>
				}
				else
				{
					coursePrereq = new ArrayList<HashMap<UUID, String>>();
				}
				JSONArray temp2 = (JSONArray)courseJSON.get(COURSE_COREQ);
				if (temp2.size() > 0 && !temp2.isEmpty())
					courseCoreq = readReq(temp2);  // JSONArray of JSONObjects(Coreqs) -> ArrayList<HashMap<UUID, String>>
				else
					courseCoreq = new ArrayList<HashMap<UUID, String>>();
				int courseHours = ((Long)courseJSON.get(COURSE_HOURS)).intValue();
				String requiredGrade = (String)courseJSON.get(REQUIRED_GRADE);
				ArrayList<String> semesterProvided = (JSONArray)courseJSON.get(SEMESTER_PROVIDED);

				Course tempCourse = new Course(courseUUID, courseID, courseName, courseDescription, coursePrereq,
				courseCoreq, courseHours, requiredGrade, semesterProvided);
				courseList.add(tempCourse);
			}
			return courseList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	private static ArrayList<HashMap<UUID, String>> readReq(JSONArray reqIn)
	{
		ArrayList<HashMap<UUID, String>> preReq = new ArrayList<HashMap<UUID, String>>();
		try
		{
			JSONArray reqJSON = reqIn;
			HashMap<UUID, String> temp = new HashMap<UUID, String>();
			for (int i = 0; i < reqJSON.size(); i++)
			{
				JSONObject singleReq = (JSONObject)reqJSON.get(i);
				UUID courseUUID = UUID.fromString((String)singleReq.get(COURSE_UUID));  // Course
				String grade = (String)singleReq.get(GRADE);  // Required Grade
				Boolean orNext;
				if((Boolean)singleReq.get(ORNEXT) != null)
					orNext = (Boolean)singleReq.get(ORNEXT);  // Logic
				else
					orNext = false;
				temp.put(courseUUID, grade);  // The course will always be put in the current HashMap, the logic will dictate whether a 'new' one needs to be made.
				if(!orNext)  // ORNext = false
				{
					HashMap<UUID, String> tempCopy = (HashMap<UUID, String>) temp.clone();
					preReq.add(tempCopy);  // This course says that the next requirement (if any) is not part of this chain of OR requirements
					temp.clear();  // Clears the working HashMap (the previous line saved it to arrayList<HashMap> first)
				}
			}
			return preReq;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<AdvisementPlan> readAdvisePlans()  // Last
	{
		ArrayList<AdvisementPlan> advisePlanList = new ArrayList<AdvisementPlan>();

		try
		{
			FileReader reader = new FileReader(ADVISEMENT_PLAN_FILE_NAME);	
			JSONArray advPlansJSON = (JSONArray)new JSONParser().parse(reader);
			CourseList tempCList = CourseList.getInstance();
			UserList tempUList = UserList.getInstance();
			HashMap<UUID, User> tempHash = tempUList.getUserList();
			for (int i = 0; i < advPlansJSON.size(); i++)
			{
				JSONObject advPlan = (JSONObject)advPlansJSON.get(i);
				UUID planID = UUID.fromString((String)advPlan.get(PLANID));
				UUID studentUUID = UUID.fromString((String)advPlan.get(STUDENT_UUID));
				User student = tempHash.get(studentUUID);
				UUID advisorUUID = UUID.fromString((String)advPlan.get(ADVISOR_UUID));
				User advisor = tempHash.get(advisorUUID);
				ArrayList<String> advisedCoursesUUIDStr = (JSONArray)advPlan.get(ADVISED_COURSES);
				ArrayList<Course> advisedCourses = new ArrayList<>();
				for (String str : advisedCoursesUUIDStr)
				{
					UUID debugUUID = UUID.fromString(str);
					Course debugTemp = tempCList.getCourseByUUID(debugUUID);
					advisedCourses.add(tempCList.getCourseByUUID(UUID.fromString(str)));
				}
				// ArrayList<UUID> advisedCoursesUUID = (JSONArray)advPlan.get(ADVISED_COURSES);
				// ArrayList<Course> advisedCourses = new ArrayList<Course>();
				String attachedNotes = (String)advPlan.get(ATTACHED_NOTES);
				String title = (String)advPlan.get(TITLE);

				AdvisementPlan tempPlan = new AdvisementPlan(planID, student, advisor, advisedCourses, attachedNotes, title);
				advisePlanList.add(tempPlan);
			}
			return advisePlanList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
