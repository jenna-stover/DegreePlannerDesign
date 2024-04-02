package testClasses;
import java.util.ArrayList;

import testClasses.ReadFile;

public class DegreeList 
{
    private static DegreeList degreeList;
    private ArrayList<DegreePlan> degrees;

    private DegreeList()
    {
        degrees = getDegrees();
    }

    public static DegreeList getInstance()
    {
        if (degreeList == null)
        {
            System.out.println("Making a new degree list");
            degreeList = new DegreeList();
        }
        return degreeList;
    }

    public ArrayList<DegreePlan> getDegrees()
    {
        return ReadFile.readDegreePlan();
    }

    public DegreePlan getDegree(Major major)
    {
        for (DegreePlan degreePlan : degrees) {
            if (degreePlan.currentMajor.equals(major))
            {
                return degreePlan;
            }
        }
        return null;
    }
}
