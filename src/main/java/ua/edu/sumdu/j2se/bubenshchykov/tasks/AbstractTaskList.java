package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable
{
    // поле класу, що необхідне для створення екземпляру класу за його типом
    protected ListTypes.types type;
    // абстрактний метод, що додає до списку вказану задачу
    public abstract void add(Task task);
    // абстрактний метод, що видаляє задачу зі списку
    public abstract boolean remove(Task task);
    // абстрактний метод, що повертає кількість задач у списку
    public abstract int size();
    // абстрактний метод, що повертає задачу, яка знаходиться на вказаному місці у списку
    public abstract Task getTask(int index);
    // метод, що дозволяє працювати з колекціями як з потоками.
    public Stream<Task> getStream()
    {
        Stream.Builder<Task> streamBuilder = Stream.builder();
        for (Task task : this) {
            streamBuilder.add(task);
        }
        return streamBuilder.build();
    }
    // метод, що повертає задачі, які заплановані хоча б раз за період часу від "from" до "to"
    public final AbstractTaskList incoming(int from, int to)
    {
        if (from < 0 || to < 0 ) {
            throw new IllegalArgumentException("The specified time or period must be greater than 0");
        }
        if (to - from < 0) {
            throw new IllegalArgumentException("The time <to> cannot be less then the time <from>.");
        }
        AbstractTaskList list = TaskListFactory.createTaskList(this.type);
        getStream().filter((t) -> t.nextTimeAfter(from) <= to && t.nextTimeAfter(from) != -1).forEach(list::add);
        return list;
    }
}