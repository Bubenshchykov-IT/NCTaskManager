package ua.edu.sumdu.j2se.bubenshchykov.tasks;

public class ArrayTaskList extends AbstractTaskList
{
    private Task[] array;
    private int size;
    private static final int INITIAL_SIZE = 10;
    // конструктор
    public ArrayTaskList()
    {
        array = new Task[INITIAL_SIZE];
        size = 0;
        type = ListTypes.types.ARRAY;
    }
    // метод, що додає до списку вказану задачу
    public void add(Task task)
    {
        if (size < array.length) {
            array[size++] = task;
        }
        else {
            Task[] newArray = new Task[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
            array[size++] = task;
        }
    }
    // метод, що видаляє задачу зі списку
    public boolean remove(Task task)
    {
        if (task == null) {
            return false;
        }
        else {
            for (int i = 0; i != size; i++) {
                if (task.equals(array[i])) {
                    System.arraycopy(array, i + 1, array, i, size - (i + 1));
                    array[size--] = null;
                    return true;
                }
            }
            return false;
        }
    }
    // метод, що повертає кількість задач у списку
    public int size()
    {
        return size;
    }
    // метод, що повертає задачу, яка знаходиться на вказаному місці у списку
    public Task getTask(int index)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrectly entered index in the list");
        }
        else {
            return array[index];
        }
    }
}