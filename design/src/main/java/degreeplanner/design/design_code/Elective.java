package degreeplanner.design.design_code;
import java.util.ArrayList;

import degreeplanner.design.design_code.Course;

public class Elective {
    public String type;
    public int credits;
    public ArrayList<Course> options;
    
    public Elective(String type, int credits, ArrayList<Course> options)
    {
        this.type = type;
        this.credits = credits;
        this.options = options;
    }

    public ArrayList<Course> getOptions()
    {
        return this.options;
    }
}
