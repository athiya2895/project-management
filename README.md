# project-management
An object model for a project management application. A project can be managed as a set of tasks performed by users utilizing resources in some amount of time adhering to a schedule. 

## Classes defined as per use case
### Task
Includes fields:
1.  Id, 
2.  Name, 
3.  Number of days required for completion, 
4.  A list of sub task which need to complete first, 
5.  A list of users working on it, 
6.  A list of resources required to comeplete task, 
7.  A boolean value indicating if task is complete and 
8.  A field to keep track of number days it took to finally conclude task.
### User
Includes fields:
1.  Id
2.  Name
3.  An int value indicating number of days before they are available  
### Resource
Includes fields:
1.  Id
2.  Name
3.  An int value indicating number waiting of days before it is available  
### ProjectManagement
Includes the 
1.  Main method for initializing objects, 
2.  A method to calculate number of working days starting from today to a given date and 
3.  A method to calculate the number of days required to complete the project by following the schedule.

## Solution Explained
1.  The current solution will be able to decide which task requires how much time based on: 
      -  on the sub tasks that it depends on, 
      -  the users that work on it and 
      -  the resources it requires.
2.  The formula for calculating the total time taken is:
      > Time for single Task = time for task completed + user waiting time + resources waiting time  
      > Update user waiting time and resource waiting time after each task completion    
      > Total time taken = sum of time taken for all tasks

## Constraints
The current solution is for single user performing single task only. The tasks are completed in a first come first serve manner.
