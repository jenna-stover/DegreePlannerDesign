package degreeplanner.design_code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

public class AdvisementPlanList 
{
    private static AdvisementPlanList advisementPlanList;
    private HashMap<UUID, AdvisementPlan> advisementPlanByUUID;

    private AdvisementPlanList() 
    {
        advisementPlanByUUID = new HashMap<UUID, AdvisementPlan>();
        ArrayList<AdvisementPlan> tempPlanList = ReadFile.readAdvisePlans();
        for(AdvisementPlan plan : tempPlanList)
        {
            advisementPlanByUUID.put(plan.getPlanID(), plan);
        }
    }

    public static AdvisementPlanList getInstance()
    {
        if (advisementPlanList == null)
        {
            //System.out.println("Making a new advisement plan list");
            advisementPlanList = new AdvisementPlanList();
        }
        return advisementPlanList;
    }

    public HashMap<UUID, AdvisementPlan> getAllListHash()
    {
        return advisementPlanByUUID;
    }
    public ArrayList<AdvisementPlan> getAllList()
    {
        ArrayList<AdvisementPlan> tempRet = new ArrayList<>();
        for(Entry<UUID, AdvisementPlan> entry : advisementPlanByUUID.entrySet())
        {
            tempRet.add(entry.getValue());
        }
        return tempRet;
    }
    public AdvisementPlan getPlanByUUID(UUID inKey)
    {
        return advisementPlanByUUID.get(inKey);
    }

    public boolean AddPlan(AdvisementPlan newPlan)
    {
        return true;
    }

    public boolean saveList()
    {
        return WriteFile.writePlans();
    }

    public void updatePlan(AdvisementPlan plan) {
        // Check if the plan exists in the list
        if (advisementPlanByUUID.containsKey(plan.getPlanID())) {
            // Update the plan in the hashmap
            advisementPlanByUUID.put(plan.getPlanID(), plan);
            // Optionally, you can save the updated list to a file or database
            saveList();
            System.out.println("Advisement plan updated successfully.");
        } else {
            System.out.println("Advisement plan does not exist in the list. Cannot update.");
        }
    }
    

  
}
