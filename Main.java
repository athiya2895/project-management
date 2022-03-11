import java.util.*;  
import java.text.*;

class Task{
    int id;
    String name;
    List<Task> taskRequired;
    List<Resource> resourceRequired;
    List<User> userRequired;
    int daysToComplete;
    Boolean isComplete;
    public Task(int _id, String _name, int _daysToComplete){
        id = _id;
        name = _name;
        daysToComplete = _daysToComplete;
    }
}
class Resource{
    int id;
    String name;
    Boolean isAvailable;
}
class User{
    int id;
    String name;
    Boolean isAvailable;
}
class ProjectManagement {
    public static Boolean isOnTime(Date deadline, ArrayList<Task> tasks){
        for(Task t : tasks){
            System.out.println(t.id);   
        }
        return true;
    }
    
    public static void main(String[] args) {
        ArrayList<Task> tasks =  new ArrayList<Task>();
        tasks.add(new Task(1, "make API", 10));
        tasks.add(new Task(2, "db setup", 5));
        tasks.add(new Task(3, "UI design", 15));
        tasks.add(new Task(4, "Authentication", 7));
        tasks.add(new Task(5, "testing", 12));
        tasks.add(new Task(6, "Logic layer", 10));
        
        
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
        try {
             isOnTime(ft.parse("2022-04-01"),tasks);
        } 
        catch (ParseException e) { 
             System.out.println("Unparseable using " + ft); 
        }
    }
}
