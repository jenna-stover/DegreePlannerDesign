package degreeplanner.design.design_code;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import degreeplanner.design.design_code.Course;
import degreeplanner.design.design_code.CourseList;
import degreeplanner.design.design_code.User;
import degreeplanner.design.design_code.UserList;

 public class WriteFile extends DataConstants 
{
    //Need to update, not in video as of 3/15 due to loss of member (Benjamin King)
    public static boolean writeUsers()  // Second & Third
	{
        UserList userList = UserList.getInstance();
        JSONObject jsonObject = new JSONObject();
        // do we need to make this a specific size?
        JSONArray usersArray = new JSONArray();
        HashMap<UUID, User> userEntry = userList.getUserList();
        for(HashMap.Entry<UUID, User> userMap : userEntry.entrySet())
        {
            //jsonObject = new JSONObject();

            if(userMap.getValue().getUserType().toString() == "STUDENT")
            {
                usersArray.add(writeStudent(userMap.getValue()));
            }
            //return false;
            //usersArray.add(usersArray.toJSONString);
        }
        //STUDENT_FILE_NAME
        try (FileWriter studentWriter = new FileWriter(STUDENT_FILE_NAME))  
        {
            studentWriter.write(usersArray.toJSONString());
            studentWriter.flush();
            studentWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        usersArray.clear();
        for(HashMap.Entry<UUID, User> userMap : userEntry.entrySet())
        {
            if((userMap.getValue()).getUserType().toString() == "PROFESSOR" || (userMap.getValue()).getUserType().toString() == "ADVISOR")
            {
                usersArray.add(writeFaculty(userMap.getValue()));     
            }
            //return false;
        }
        //FACULTY_FILE_NAME
        try (FileWriter facFileWriter = new FileWriter(FACULTY_FILE_NAME))
        {
            facFileWriter.write(usersArray.toJSONString());
            facFileWriter.flush();
            facFileWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JSONObject writeStudent(User user)  // Second
	{
        JSONObject jsonObject = new JSONObject();
        if(user == null)
        {
            return null;
        }
        //FileWriter file = new FileWriter(STUDENT_FILE_NAME);
        jsonObject.put(USER_UUID, user.getUUID().toString());
        jsonObject.put(USER_ID, user.getUserID());
        jsonObject.put(FIRSTNAME, user.getUserFirstName());
        jsonObject.put(LASTNAME, user.getUserLastName());
        jsonObject.put(USER_EMAIL, user.getUserEmail());
        jsonObject.put(USER_PASSWORD, user.getUserPass());
        jsonObject.put(USER_TYPE, user.getUserType().toString());
        HashMap<Course, String> temp = ((Student)user).getCompletedCourses();
        JSONArray jsonArray = new JSONArray();
        for (HashMap.Entry<Course, String> tempEntry : temp.entrySet())
        {
            JSONObject jsonCourseNGrade = new JSONObject();
            jsonCourseNGrade.put(COURSE_ID, (tempEntry.getKey()).getCourseID());
            jsonCourseNGrade.put(GRADE, tempEntry.getValue());
            jsonArray.add(jsonCourseNGrade);
        }
        jsonObject.put(COMPLETED_COURSES, jsonArray);
        ArrayList<Course> tempCourses = ((Student)user).getCurrentCourses();
        JSONArray jsonArray2ElecticBoogaloo = new JSONArray();
        for (Course course : tempCourses)
        {
            jsonArray2ElecticBoogaloo.add(course.getCourseID());
        }
        jsonObject.put(CURRENT_COURSES, jsonArray2ElecticBoogaloo);
        ArrayList<Course> tempIncompletes = ((Student)user).getIncompleteCourses();
        JSONArray jsonArrayTheSaga = new JSONArray();
        for (Course course : tempIncompletes)
        {
            jsonArrayTheSaga.add(course.getCourseID());
        }
        jsonObject.put(INCOMPLETE_COURSES, jsonArrayTheSaga);
        ArrayList<Warnings> tempWarnings = ((Student)user).warnings;
        JSONArray jsonArrayTheCrappyReboot = new JSONArray();
        for (Warnings warn : tempWarnings)
        {
            jsonArrayTheCrappyReboot.add(warn.toString());
        }
        jsonObject.put(WARNINGS, jsonArrayTheCrappyReboot);
        jsonObject.put(COMPLETED_HOURS, ((Student)user).completedHours);
        jsonObject.put(CURRENT_HOURS, ((Student)user).currentHours);
        jsonObject.put(TOTAL_DEGREE_HOURS, ((Student)user).totalDegreeHours);
        ArrayList<UUID> tempPlans = ((Student)user).getAdvisementPlanUUID();
        JSONArray jsonArrayTheJourneysEnd = new JSONArray();
        for (UUID id : tempPlans)
        {
            //System.out.println("Test: " + tempPlans.get(i));
            //UUID tempUUID = (UUID)tempPlans.get(i);
            String tempString = id.toString();

            //System.out.println("test: " + tempString);
            jsonArrayTheJourneysEnd.add(tempString);
        }
        jsonObject.put(ADVISEMENT_PLAN, jsonArrayTheJourneysEnd);
        jsonObject.put(GPA, ((Student)user).getGPA());
        jsonObject.put(HAS_SCHOLARSHIPS, ((Student)user).hasScholarships);
        jsonObject.put(MAJOR_NAME, (((Student)user).currentMajor).toString());
        return jsonObject;
    }

    public static JSONObject writeFaculty(User user)
    {

        try
        {
            if(user == null)
            {
                return null;
            }
            JSONObject jsonObject = new JSONObject();
            //FileWriter file = new FileWriter(FACULTY_FILE_NAME);
            if(user.userType.toString() == "PROFESSOR")
            {
                // should I be calling toString on all of these user attributes?
                jsonObject.put(USER_UUID, user.getUUID().toString());
                jsonObject.put(USER_ID, user.getUserID());
                jsonObject.put(FIRSTNAME, user.getUserFirstName());
                jsonObject.put(LASTNAME, user.getUserLastName());
                jsonObject.put(USER_EMAIL, user.getUserEmail());
                jsonObject.put(USER_PASSWORD, user.getUserPass());
                jsonObject.put(USER_TYPE, user.getUserType().toString());
                ArrayList<Course> temp = ((Faculty)user).getCoursesInstructing();
                JSONArray jsonArray = new JSONArray();
                for(Course course : temp)
                {
                    jsonArray.add(course.getCourseUUID());
                }
                jsonObject.put(COURSES_INSTRUCTING, jsonArray);
                // again not sure how to access coursesInstructing through user object
                //jsoneObject.put(COURSES_INSTRUCTING, Faculty.coursesInstructing);
                //file.write(jsonObject.toJSONString());
                //file.close();
                return jsonObject;
            }
            else if(user.userType.toString() == "ADVISOR")
            {
                
                jsonObject.put(USER_UUID, user.getUUID().toString());
                jsonObject.put(USER_ID, user.getUserID());
                jsonObject.put(FIRSTNAME, user.getUserFirstName());
                jsonObject.put(LASTNAME, user.getUserLastName());
                jsonObject.put(USER_EMAIL, user.getUserEmail());
                jsonObject.put(USER_PASSWORD, user.getUserPass());
                jsonObject.put(USER_TYPE, user.getUserType().toString());
                ArrayList<User> temp = ((Faculty)user).getAdvisingStudents();
                JSONArray jsonArray = new JSONArray();
                for(User student : temp)
                {
                    jsonArray.add(student.getUUID());
                }
                jsonObject.put(ADVISING_STUDENTS, jsonArray);
                // not sure how to access a faculty attribute through a faculty user. should i make a user = new Faculty() 
                // at the top of the if statement?
                //jsoneObject.put(ADVISING_STUDENTS, Faculty.advisingStudents);
                //file.write(jsonObject.toJSONString());
                //file.close();
                return jsonObject;
            }        
        }
        catch (Exception e)  // If something goes wrong throws this error
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeCourse()
    {
        JSONObject jsonObject;        
        // do we need to make this a specific size?  // no
        JSONArray courseArray = new JSONArray();
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> coursesIn = courseList.getCourses();
        
        for(Course course : coursesIn)
        {
            jsonObject = new JSONObject();
            jsonObject.put(COURSE_UUID, (course.getCourseUUID()).toString());
            jsonObject.put(COURSE_ID, course.getCourseID());
            jsonObject.put(COURSE_NAME, course.getCourseName());
            jsonObject.put(COURSE_DESCRIPTION, course.getCourseDescription());
            JSONObject jsonPrereq = new JSONObject();
            JSONArray jsonPrereqArray = new JSONArray();
            for(HashMap<UUID, String> prereq : course.coursePrereqUUID)
            {
                int i = 1;
                boolean orFlag = true;
                for(HashMap.Entry<UUID, String> prereqEntry : prereq.entrySet())
                {
                    jsonPrereq.put(COURSE_UUID, prereqEntry.getKey());
                    jsonPrereq.put(GRADE, prereqEntry.getValue());
                    if(i != prereq.size())
                    {
                        orFlag = true;
                    }
                    else
                    {
                        orFlag = false;
                    }
                    jsonPrereq.put(ORNEXT, orFlag);

                    i++;
                }
                jsonPrereqArray.add(jsonPrereq.toJSONString());
            }
            jsonObject.put(COURSE_PREREQ, jsonPrereqArray.toJSONString());
            JSONObject jsonCoreq = new JSONObject();
            JSONArray jsonCoreqArray = new JSONArray();
            for(HashMap<UUID, String> coreq : course.courseCoreqUUID)
            {
                int i = 1;
                boolean orFlag = false;
                for(HashMap.Entry<UUID, String> coreqEntry : coreq.entrySet())
                {
                    jsonCoreq.put(COURSE_UUID, coreqEntry.getKey());
                    jsonCoreq.put(GRADE, coreqEntry.getValue());
                    if(i != coreq.size())
                    {
                        orFlag = true;
                    }
                    else
                    {
                        orFlag = false;
                    }
                    jsonCoreq.put(ORNEXT, orFlag);
                    i++;
                }
                jsonCoreqArray.add(jsonCoreq.toJSONString());
            }
            jsonObject.put(COURSE_COREQ, jsonCoreqArray.toJSONString());
            jsonObject.put(COURSE_HOURS, course.getCourseHours());            
            JSONArray semProvidedArray = new JSONArray();
            ArrayList<String> semProv = course.getSemestersProvided();
            for(String semester : semProv)
            {
                semProvidedArray.add(semester);
            }
            jsonObject.put(SEMESTER_PROVIDED, semProvidedArray.toJSONString());
            courseArray.add(jsonObject);
        }
        try (FileWriter courseWriter = new FileWriter(COURSE_FILE_NAME))
        {
            courseWriter.write(courseArray.toJSONString());
            courseWriter.flush();
            courseWriter.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean writePlans()
    {
        JSONObject jsonObject;
        JSONArray jsonArray;
        AdvisementPlanList planList = AdvisementPlanList.getInstance();
        if(planList == null)
            return false;
        else
        {
            ArrayList<AdvisementPlan> plans = planList.getAllList();
            if(plans == null)
                return false;
            jsonArray = new JSONArray();
            for (AdvisementPlan plan : plans)
            {
                jsonObject = new JSONObject();
                jsonObject.put(PLANID, plan.getPlanID().toString());
                jsonObject.put(STUDENT_UUID, (plan.getPlanStudent()).getUUID().toString());
                jsonObject.put(ADVISOR_UUID, (plan.getPlanAdvisor()).getUUID().toString());
                jsonObject.put(ATTACHED_NOTES, plan.getNotes());
                JSONArray tempArray = new JSONArray();
                for(Course co : plan.getCourses())
                {
                    tempArray.add(co.getCourseUUID().toString());
                }
                jsonObject.put(ADVISED_COURSES, tempArray);
                jsonArray.add(jsonObject);
            }
            try (FileWriter planWriter = new FileWriter(ADVISEMENT_PLAN_FILE_NAME))
            {
                planWriter.write(jsonArray.toJSONString());
                planWriter.flush();
                planWriter.close();
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
    }
    

/*
    private static DegreePlan writeDegreePlan(DegreePlan degreePlan)
	{
		try
		{
			//JSONObject degreePlanJSON = degreePlan;
			HashMap<UUID, String> compCourses = new HashMap<UUID, String>();
            for(HashMap.Entry<UUID, String> compCoursesMap : compCourses.entrySet())
            {
                //degreePlan.put(COMPLETED_COURSES, compCoursesMap.courseUUID);
            }
			//compCourses = writeCompCourse((JSONArray)degreePlanJSON.put(COMPLETED_COURSES));  // JSONArray of JSONObjects -> HashMap<UUID, String>
			ArrayList<UUID> currentCourses = (JSONArray)degreePlanJSON.get(CURRENT_COURSES);
			ArrayList<UUID> incompleteCourses = (JSONArray)degreePlanJSON.get(INCOMPLETE_COURSES);
			ArrayList<Warnings> warnings = (JSONArray)degreePlanJSON.get(WARNINGS);
			int completedHours = ((Long)degreePlanJSON.get(COMPLETED_HOURS)).intValue();  // String -> int
			int currentHours = ((Long)degreePlanJSON.get(CURRENT_HOURS)).intValue();
			int totalDegreeHours = ((Long)degreePlanJSON.get(TOTAL_DEGREE_HOURS)).intValue();
			Major currentMajor = Major.valueOf((String)degreePlanJSON.get(CURRENT_MAJOR));  // String -> Major
			Concentration currentConcentration = Concentration.valueOf((String)degreePlanJSON.get(CURRENT_CONCENTRATION));  // String -> Major

			degreePlan = new DegreePlan(compCourses, currentCourses, incompleteCourses, warnings, completedHours, 
			currentHours, totalDegreeHours, currentMajor, currentConcentration);

			return degreePlan;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static HashMap<UUID, String> writeCompCourse(HashMap<UUID, String> compCoursesIn)  // compCoursesIn = JSONArray of JSONObjects. The JSONObjects = courseUUID & recievedGrade
	{
		HashMap<UUID, String> tempMap;
		try
		{
			JSONArray compCourses = compCoursesIn;
			tempMap = new HashMap<UUID, String>();
			for (int i = 0; i < compCourses.size(); i++)  // Getting each course and its recieved grade
			{
				JSONObject compCourse = (JSONObject)compCourses.get(i);
				UUID courseUUID = UUID.fromString((String)compCourse.get(COURSE_UUID));
				String grade = (String)compCourse.get(GRADE);

				tempMap.put(courseUUID, grade);  // Adding it to the HashMap
			}
			return tempMap;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
*/



}
