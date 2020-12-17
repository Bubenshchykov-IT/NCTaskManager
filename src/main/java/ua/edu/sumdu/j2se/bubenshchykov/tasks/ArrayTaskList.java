package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.util.Arrays;
import java.util.Iterator;

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
    // інтерфейс, який описує абстрактну ітерацію по набору об’єктів
    @Override
    public Iterator<Task> iterator()
    {
        return new Iterator<Task>()
        {
            private int index = 0;
            // метод для перевірки наявності наступного елемента ітератора
            @Override
            public boolean hasNext()
            {
                return index != size;
            }
            // метод для переходу до наступного елемента ітератора
            @Override
            public Task next()
            {
                return array[index++];
            }
            // метод для видалення елемента ітератора
            @Override
            public void remove()
            {
                if (index == 0) {
                    throw new IllegalStateException("Out of the array interval.");
                }
                else {
                    ArrayTaskList.this.remove(array[--index]);
                }
            }
        };
    }
    // метод для перевірки рівності 2 об'єктів
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof ArrayTaskList)) return false;
        ArrayTaskList taskList = (ArrayTaskList) object;
        if (size == taskList.size && Arrays.equals(array, taskList.array)) {
            return true;
        }
        else return false;
    }
    // метод для перевірки рівності 2 об'єктів повищеної продуктивності
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(array);
    }
    // метод, що створює копію об'єкта (клонування)
    @Override
    public ArrayTaskList clone()
    {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i != size; i++) {
            arrayTaskList.add(array[i]);
        }
        return arrayTaskList;
    }
    // метод, що повертає символьний рядок, яка описує відповідний об'єкт
    @Override
    public String toString()
    {
        String str = "";
        for(int i = 0; i != size; i++) {
            str += array[i].toString() + "\n";
        }
        return str;
    }
}