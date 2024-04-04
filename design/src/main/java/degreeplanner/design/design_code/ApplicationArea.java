package degreeplanner.design.design_code;
import java.util.ArrayList;

import degreeplanner.design.design_code.Course;

public class ApplicationArea {
    public String ApplicationAreaName;
    public ArrayList<Course> choices;

    public ApplicationArea(String AppAreaName, ArrayList<Course> choices)
    {
        this.ApplicationAreaName = AppAreaName;
        this.choices = choices;
    }

    public ArrayList<Course> getChoices()
    {
        return this.choices;
    }
}
