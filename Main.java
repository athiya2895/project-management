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
        int count = 0;
        // calculating the total number of days to complete all tasks
        while(count != tasks.size()){
            for(Task t :  tasks) {
                User userT = t.userRequired.get(0);

                if (t.taskRequired == null && !t.isComplete) {
                    int tempDays = t.daysToComplete + userT.availableIn;
                    for (Resource r:t.resourceRequired) {
                        tempDays += r.availableIn;
                    }
                    requiredDays += tempDays;
                    t.completedIn += tempDays;
                    userT.availableIn = requiredDays;
                    for (Resource r:t.resourceRequired) {
                        r.availableIn = requiredDays;
                    }
                    t.isComplete = true;
                    count++;
                } else if(t.taskRequired != null) {
                    int flag = 0;
                    for (Task s : t.taskRequired) {
                        if (s.isComplete) {
                            flag = 1;
                            t.completedIn += s.completedIn;
                        }
                    }
                    if (flag == 1) {
                        t.taskRequired = null; //TODO: find a better way to do this!
                    }
                }
            }
        }

        System.out.println("Number of days required = "+ requiredDays);
        LocalDate t = LocalDate.now();
        long workingDays = calcWeekDays(t,deadline);
        System.out.println("Number of working days = "+ workingDays);
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
        Resource r1 = new Resource(101, "CPU");
        Resource r2 = new Resource(102, "Memory");
        Resource r3 = new Resource(104, "Cloud");
        Resource r4 = new Resource(105, "VPN");

        User u1 = new User(201, "Mark");
        User u2 = new User(202, "John");
        User u3 = new User(203, "Lacy");
        User u4 = new User(204, "Bob");
        User u5 = new User(205, "Cady");

        //single user dependency
        Task api = new Task(1, "make API",
                10, null,
                new ArrayList<User>(){{add(u1);}},
                new ArrayList<Resource>(){{add(r1);}});

        Task db = new Task(2, "db setup",
                5, null,
                new ArrayList<User>(){{add(u2);}},
                new ArrayList<Resource>(){{add(r1);add(r2);}});

        Task ui = new Task(3, "UI design",
                15, new ArrayList<Task>(){{add(db);}},
                new ArrayList<User>(){{add(u3);}},
                new ArrayList<Resource>(){{add(r3);}});

        Task auth = new Task(4, "Authentication",
                7, new ArrayList<Task>(){{add(ui);}},
                new ArrayList<User>(){{add(u4);}},
                new ArrayList<Resource>(){{add(r4);}});

        Task testing = new Task(5, "testing",
                12, new ArrayList<Task>(){{add(api);}},
                new ArrayList<User>(){{add(u1);}},
                new ArrayList<Resource>(){{add(r3);}});

        Task logic = new Task(6, "Logic layer",
                10,null,
                new ArrayList<User>(){{add(u1);}},
                new ArrayList<Resource>(){{add(r2);}});

        // considering all tasks indeoendent for first iteration
        tasks.add(api);
        tasks.add(db);
        tasks.add(ui);
        tasks.add(auth);
        tasks.add(testing);
        tasks.add(logic);

        String date = "2023-07-16";

        LocalDate dueDate = LocalDate.parse(date);
        System.out.println("will the project be completed before deadline? = " + (isOnTime(dueDate,tasks)?"Yes":"No"));
    }
}
class Task{
    int id;
    String name;
    ArrayList<Task> taskRequired;
    ArrayList<Resource> resourceRequired;
    ArrayList<User> userRequired;
    int daysToComplete;
    Boolean isComplete;
    int completedIn;
    public Task(int _id, String _name, int _daysToComplete,
                ArrayList<Task> _taskRequired,
                ArrayList<User> _userRequired,
                ArrayList<Resource> _resourceRequired){
        id = _id;
        name = _name;
        daysToComplete = _daysToComplete;
        taskRequired = _taskRequired;
        userRequired = _userRequired;
        resourceRequired = _resourceRequired;
        isComplete = false;
        completedIn = 0;
    }
}
class Resource{
    int id;
    String name;
    int availableIn;
    public Resource(int _id, String _name){
        id = _id;
        name = _name;
        availableIn = 0;
    }
}
class User{
    int id;
    String name;
    int availableIn;
    public User(int _id, String _name){
        id = _id;
        name = _name;
    }
}
