package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks
{
    // метод, що повертає задачі, які заплановані хоча б раз за період часу від "from" до "to"
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to)
    {
        if (from == null || to == null ) {
            throw new IllegalArgumentException("The specified time cannot be null.");
        }
        if (to.compareTo(from) < 0) {
            throw new IllegalArgumentException("The time <to> cannot be less then the time <from>.");
        }
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.nextTimeAfter(from) != null && task.nextTimeAfter(from).compareTo(to) <= 0) {
                list.add(task);
            }
        }
        return list;
    }
    // метод, що будує календар задач для заданого періоду часу
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to)
    {
        if (from == null || to == null ) {
            throw new IllegalArgumentException("The specified time cannot be null.");
        }
        if (to.compareTo(from) < 0) {
            throw new IllegalArgumentException("The time <to> cannot be less then the time <from>.");
        }
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>();
        for (Task task : tasks) {
            for (LocalDateTime i = task.nextTimeAfter(from); i != null && i.compareTo(to) <= 0; i = task.nextTimeAfter(i)) {
                if (map.get(i) == null) {
                    HashSet<Task> set = new HashSet<Task>();
                    set.add(task);
                    map.put(i, set);
                }
                else {
                    map.get(i).add(task);
                }
            }
        }
        return map;
    }
}