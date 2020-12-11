package ua.edu.sumdu.j2se.bubenshchykov.tasks;

public class TaskListFactory
{
    // статичний метод, що необхідний для створення екземпляру класу ArrayTaskList або LinkedTaskList
    public static AbstractTaskList createTaskList(ListTypes.types type)
    {
        AbstractTaskList list = null;
        switch (type) {
            case ARRAY:
                list = new ArrayTaskList();
                break;
            case LINKED:
                list = new LinkedTaskList();
                break;
        }
        return list;
    }
}
