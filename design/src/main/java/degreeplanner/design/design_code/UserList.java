package degreeplanner.design.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import degreeplanner.design.design_code.Faculty;

public class UserList
{
    public User user;
    private static UserList userList;
    private ArrayList<User> allStudents;
    private ArrayList<User> allFaculty;
    private ArrayList<User> allUsers;
    private HashMap<UUID, User> users; //String userUUID, User user
    private HashMap<String, UUID> userEmails; //String email, String userUUID
    private HashMap<String, UUID> userNames; //String fullName, String userUUID

    private UserList()
    {
        allStudents = new ArrayList<User>();
        allFaculty = new ArrayList<User>();
        users = getUserList();
        userEmails = getUserEmails();
        userNames = getUserFullname();
    }

    public static UserList getInstance()
    {
        if (userList == null)
        {
            //System.out.println("Making a new user list");
            userList = new UserList();
        }
        return userList;
    }

    public User getUserByEmailAndPass(String email, String password)
    {
        for(User user : getUsers()) {
            if(user.userEmail == email && user.getUserPass() == password) 
            {
                return user;
            }
        }
        return null;
    }

    public User getUser(String userID, UserType userType)
    {
        if ((userType.toString() == "ADVISOR" || userType.toString() == "PROFESSOR"))
        {
            for(User tempuser : getUsers()) {
                if (tempuser.getUserID().compareTo(userID) == 0) 
                {
                    return tempuser;
                }
            }
        }
        return null;
    }

    public ArrayList<User> getUsers()
    {
        if (allUsers == null)
        {
            allUsers = ReadFile.readUsers();
            return allUsers;
        }
        else
            return allUsers;
    }

    //changed return type to User instead of boolean
    public User login(String email, String userPass)
    {

        user = getUserByEmail(email);
        if(user != null && user.checkLoginCredentials(email, userPass))
        {
            System.out.println("Login Successful");
            return user;
        }
        else
        {
            System.out.println("Login Unsuccessful");
            return null;
        }
    }

    public User getUserByEmail(String email)
    {
        for(User user : getUsers())
        {
            if(user.userEmail.equals(email))
            {
                return user;
            }
        }
        return null;
    }


    public boolean logout()
    {
        if(user != null)
        {
            //tbd-- clear necessary data
            user = null;
            System.out.println("Logout Successful");
            return true;
        }
        else
        {
            System.out.println("Logout Unsuccessful");
            return false;
        }
    }


    public HashMap<UUID, User> getUserList()
    {
        HashMap<UUID, User> result = new HashMap<UUID, User>();
        ArrayList<User> tempUsers = getUsers();
        for(User user : tempUsers)
        {
            result.put(user.getUUID(), user);
            if ((user.userType).toString() == "ADVISOR" || (user.userType).toString() == "PROFESSOR")
            {
                allFaculty.add(user);
            }
            else
            {
                allStudents.add(user);
            }
        }
        return result;
    }


    public HashMap<String, UUID> getUserEmails()
    {
        HashMap<String, UUID> result = new HashMap<String, UUID>();
        ArrayList<User> tempUsers = getUsers();
        for(User user : tempUsers)
        {
            result.put(user.userEmail, user.getUUID());
        }
        return result;
    }

    public HashMap<String, UUID> getUserFullname()
    {
        HashMap<String, UUID> result = new HashMap<String, UUID>();
        ArrayList<User> tempUsers = getUsers();
        for(User user : tempUsers)
        {
            String toPut = "";
            toPut += user.firstName + " " + user.lastName;
            result.put(toPut, user.getUUID());
        }
        return result;
    }

    public boolean addUser(User user)
    {
        if(user != null)
        {
            //WriteFile.writeUser(user);
            return true;
        }
        return false;
    }

    // public void updateAdviseStuList()
    // {
    //     ArrayList<UUID> studentUUIDs;
    //     for (int i = 0; i < allFaculty.size(); i++) 
    //     {
    //         Faculty faculty = (Faculty)allFaculty.get(i);
    //         if((faculty.userType).toString() == "ADVISOR")
    //         {
    //             studentUUIDs = ((Faculty) faculty).getAdviseStuUUIDList();
    //             if(studentUUIDs != null)
    //             {
    //                 for (UUID stuUUID : studentUUIDs)
    //                 {
    //                     User student = users.get(stuUUID);
    //                     faculty.addAdvisingStudent(student);
    //                 }
    //                 users.replace(faculty.getUUID(), faculty);
    //             }
    //         }
    //     }
    // }

    public void updateAdviseStuList() 
    {
        ArrayList<UUID> studentUUIDs;
        for (int i = 0; i < allFaculty.size(); i++) 
        {
            Faculty faculty = (Faculty) allFaculty.get(i);
            if (faculty.getUserType() == UserType.ADVISOR) 
            { 
                studentUUIDs = faculty.getAdviseStuUUIDList();
                if (studentUUIDs != null) 
                {
                    for (UUID stuUUID : studentUUIDs) 
                    {
                        User student = users.get(stuUUID);
                        if (student != null) 
                        { 
                            faculty.addAdvisingStudent(student);
                        } else {
                            System.out.println("Student with UUID " + stuUUID + " not found.");
                        }
                    }
                    users.replace(faculty.getUUID(), faculty);
                }
            }
        }
    }
}
