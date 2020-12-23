package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable
{
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    // активна/неактивна задача
    private boolean active;
    // конструктор (неактивна задача без повторення)
    public Task(String title, LocalDateTime time)
    {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null.");
        }
        this.title = title;
        this.time = time;
        start = time;
        end = time;
        active = false;
    }
    // конструктор (неактивна задача з повторенням)
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval)
    {
        if (start == null || end == null || interval < 0) {
            throw new IllegalArgumentException("Time cannot be null or period cannot be less then zero.");
        }
        if (end.compareTo(start) < 0) {
            throw new IllegalArgumentException("Time <start> cannot be less then time <end>.");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
    }
    // метод для зчитування назви задачі
    public String getTitle()
    {
        return title;
    }
    // метод для встановлення назви задачі
    public void setTitle(String title)
    {
        this.title = title;
    }
    // метод для зчитування стану задачі
    public boolean isActive()
    {
        return active;
    }
    // метод для встановлення стану задачі
    public void setActive(boolean active)
    {
        this.active = active;
    }
    // метод для зчитування часу виконання для задач, що не повторюються
    public LocalDateTime getTime()
    {
        return start;
    }
    // метод для встановлення часу виконання для задач, що не повторюються
    public void setTime(LocalDateTime time)
    {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null.");
        }
        start = time;
        end = time;
        interval = 0;
    }
    // метод для зчитування часу виконання для задач, що повторюються
    public LocalDateTime getStartTime() {
        return start;
    }
    public LocalDateTime getEndTime() {
        return end;
    }
    public int getRepeatInterval() {
        return interval;
    }
    // метод для встановлення часу виконання для задач, що повторюються
    public void setTime(LocalDateTime start, LocalDateTime end, int interval)
    {
        if (start == null || end == null || interval < 0) {
        throw new IllegalArgumentException("Time cannot be null or period cannot be less then zero/");
        }
        if (end.compareTo(start) < 0) {
            throw new IllegalArgumentException("Time <start> cannot be less then time <end>.");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    // метод для перевірки повторюваності задачі
    public boolean isRepeated() {
        return interval != 0;
    }
    // метод, що повертає час наступного виконання задачі після вказаного часу
    public LocalDateTime nextTimeAfter(LocalDateTime current)
    {
        if (current == null) {
            throw new IllegalArgumentException("Time cannot be null.");
        }
        if (!active)
            return null;
        else if (interval == 0) {
            if (end.compareTo(current) > 0)
                return end;
            else return null;
        }
        else {
            for (LocalDateTime i = start; i.compareTo(end) <= 0; i = i.plusSeconds(interval)) {
                if (i.compareTo(current) > 0)
                    return i;
            }
        }
        return null;
    }
    // метод для перевірки рівності 2 об'єктів
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof Task)) return false;
        Task task = (Task) object;
        if (start == task.start && end == task.end && interval == task.interval
                && active == task.active && Objects.equals(title, task.title)) {
            return true;
        }
        else return false;
    }
    // метод для перевірки рівності 2 об'єктів повищеної продуктивності
    @Override
    public int hashCode()
    {
        return Objects.hash(title, start, end, interval, active);
    }
    // метод, що створює копію об'єкта (клонування)
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Task task = new Task(title, time);
        task.active = active;
        task.start = start;
        task.end = end;
        task.interval = interval;
        return task;
    }
    // метод, що повертає символьний рядок, яка описує відповідний об'єкт
    @Override
    public String toString()
    {
        if (interval != 0) {
            return "Task {" + "title = " + title + ", active = " + active + ", start = " + start + ", end = " + end +
                    ", interval = " + interval + '}';
        } else {
            return "Task {" + "title = " + title + ", active = " + active + ", time =" + time + '}';
        }
    }
}