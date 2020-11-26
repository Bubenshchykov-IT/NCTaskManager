package ua.edu.sumdu.j2se.bubenshchykov.tasks;

public class Task
{
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    // активна/неактивна задача
    private boolean active;
    // конструктор (неактивна задача без повторення)
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        start = time;
        end = time;
        active = false;
    }
    // конструктор (неактивна задача з повторенням)
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
    }
    // метод для зчитування назви задачі
    public String getTitle() {
        return title;
    }
    // метод для встановлення назви задачі
    public void setTitle(String title) {
        this.title = title;
    }
    // метод для зчитування стану задачі
    public boolean isActive() {
        return active;
    }
    // метод для встановлення стану задачі
    public void setActive(boolean active) {
        this.active = active;
    }
    // метод для зчитування часу виконання для задач, що не повторюються
    public int getTime() {
        return start;
    }
    // метод для встановлення часу виконання для задач, що не повторюються
    public void setTime(int time) {
        start = time;
        end = time;
        interval = 0;
    }
    // метод для зчитування часу виконання для задач, що повторюються
    public int getStartTime() {
        return start;
    }
    public int getEndTime() {
        return end;
    }
    public int getRepeatInterval() {
        return interval;
    }
    // метод для встановлення часу виконання для задач, що повторюються
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    // метод для перевірки повторюваності задачі
    public boolean isRepeated() {
        return interval != 0;
    }
    // метод, що повертає час наступного виконання задачі після вказаного часу
    public int nextTimeAfter(int current) {
        if (!active)
            return -1;
        else if (interval == 0) {
            if (end - current > 0)
                return end;
            else return -1;
        }
        else {
            for (int i = start; i <= end; i += interval) {
                if (i - current > 0)
                    return i;
            }
        }
        return -1;
    }
}