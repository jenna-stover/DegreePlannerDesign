package degreeplanner.design_code;
import java.time.LocalDate;

public class AdvisementNote {
    private final LocalDate date;
    private final String advisee;
    private final String note;

    public AdvisementNote(LocalDate date, String advisee, String note) {
        this.date = date;
        this.advisee = advisee;
        this.note = note;
    }

    public LocalDate getDate() { 
        return date; 
    }
    public String getAdvisee() { 
        return advisee; 
    }
    public String getNote() { 
        return note; 
    }
}
