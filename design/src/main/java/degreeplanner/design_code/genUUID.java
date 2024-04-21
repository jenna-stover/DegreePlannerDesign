package degreeplanner.design_code;
import java.util.UUID;

public class genUUID
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            UUID id = UUID.randomUUID();
            System.out.println(id);
        }
    }

    public UUID generateUUID()
    {
        UUID id = UUID.randomUUID();
        return id;
    }
}