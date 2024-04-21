package degreeplanner.design_code;
import java.time.LocalDate;
import java.util.UUID;

public class AdvisementNote {
    private final LocalDate date;
    private final String note;
    private final String advisee;

    // public AdvisementNote(LocalDate date, String advisee, String note) {
    //     this.date = date;
    //     this.advisee = advisee;
    //     this.note = note;
    // }
    public AdvisementNote(UUID uuid, LocalDate date, String advisee, String note) {
    // Assign uuid to some property if needed
    this.date = date;
    this.advisee = advisee;
    this.note = note;
}

    public LocalDate getDate() { 
        return date; 
    }
    
    public String getNote() { 
        return note; 
    }
}
