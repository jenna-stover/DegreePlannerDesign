// package degreeplanner.design_code;
// //TESTING ONLY, NOT FOR FINAL IMPLEMENTATION
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.UUID;

// import degreeplanner.design_code.Course;
// import degreeplanner.design_code.ReadFile;
// import degreeplanner.design_code.User;

// import java.util.Random;

// public class testFile {
//     public static void main(String[] args) 
//     {
//         System.out.println("Testing readFile");
//         System.out.println("Test 1: Read in courses successful?");
//         ArrayList<Course> courseArray = new ArrayList<Course>();
//         courseArray = ReadFile.readCourses();
//         if (courseArray == null)
//         {
//             System.out.println("Test 1 failed, no courses read in");
//             System.exit(0);
//         } else {
//             System.out.println("Test 1 success");
//         }
//         System.out.println("Test 2: Read in students successful?");
//         ArrayList<User> studentArray = new ArrayList<User>();
//         studentArray = ReadFile.readStudents();
//         if (studentArray == null)
//         {
//             System.out.println("Test 2 failed, No students read in");
//             System.exit(0);
//         } else {
//             System.out.println("Test 2 success.");
//         } 
//         System.out.println("Test 3: Print random course simple and reqs");
//         Random rand = new Random();
//         Course tempCourse = courseArray.get(rand.nextInt(courseArray.size()));
//         if (tempCourse.testToString() != null)
//         {
//             System.out.println(tempCourse.testToString());
//             System.out.println(tempCourse.reqsToString());
//         }
//         else {
//             System.out.println("Noting was in random course object");
//         }
//         DegreeList degreeList = DegreeList.getInstance();
//         if (degreeList != null)
//         {
//             System.out.println("Successful read-in of degrees");
//         }
        

//     }
// }
