package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable
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
    // метод, що дозволяє працювати з колекціями як з потоками
    public Stream<Task> getStream()
    {
        Stream.Builder<Task> streamBuilder = Stream.builder();
        for (Task task : this) {
            streamBuilder.add(task);
        }
        return streamBuilder.build();
    }
}