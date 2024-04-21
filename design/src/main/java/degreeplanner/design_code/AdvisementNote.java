package degreeplanner.design_code;
import java.time.LocalDate;

public class AdvisementNote {
    private final LocalDate date;
    private final String note;

    public AdvisementNote(LocalDate date, String note) {
        this.date = date;
        this.note = note;
    }

    public LocalDate getDate() { 
        return date; 
    }
    
    public String getNote() { 
        return note; 
    }
}
