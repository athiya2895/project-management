// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.temporal.*;
import java.time.DayOfWeek;

class ProjectManagement {
    public static Boolean isOnTime(LocalDate deadline, ArrayList<Task> tasks){
        int requiredDays = 0;
        // calculating the total number of days to complete all tasks
        for(Task t : tasks){
            if(!t.isComplete) {
                requiredDays += t.daysToComplete;
                t.isComplete = true;
            }
        }
        LocalDate t = LocalDate.now();
        long workingDays = calcWeekDays(t,deadline);
        if(workingDays < requiredDays){
            return false;
        }

        return true;
    }

    public static long calcWeekDays(LocalDate start, LocalDate end) {
        DayOfWeek startW = start.getDayOfWeek();
        DayOfWeek endW = end.getDayOfWeek();

        long days = ChronoUnit.DAYS.between(start, end);
        long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks =  new ArrayList<Task>();

        // considering all tasks indeoendent for first iteration
        tasks.add(new Task(1, "make API", 10));
        tasks.add(new Task(2, "db setup", 5));
        tasks.add(new Task(3, "UI design", 15));
        tasks.add(new Task(4, "Authentication", 7));
        tasks.add(new Task(5, "testing", 12));
        tasks.add(new Task(6, "Logic layer", 10));

        String date = "2022-03-16";

        LocalDate dueDate = LocalDate.parse(date);
        System.out.println(isOnTime(dueDate,tasks));

        /*SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        try {
             isOnTime(ft.parse("2022-03-23"),tasks);
        }
        catch (ParseException e) {
             System.out.println("Unparseable using " + ft);
        }*/
    }
}
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
        isComplete = false;
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
