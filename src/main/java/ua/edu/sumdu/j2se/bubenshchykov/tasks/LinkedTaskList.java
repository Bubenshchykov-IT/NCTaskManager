package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList
{
    private NodeTask head;
    private NodeTask last;
    private int size;
    // конструктор
    public LinkedTaskList()
    {
        head = null;
        last = null;
        size = 0;
        type = ListTypes.types.LINKED;
    }
    // метод, що додає до списку вказану задачу
    public void add(Task task)
    {
        if (task == null) {
            return;
        }
        NodeTask item = new NodeTask(task);
            if (head == null) {
                head = item;
                last = item;
            }
            else {
                last.setNext(item);
                last = item;
            }
            size++;
    }
    // метод, що видаляє задачу зі списку
    public boolean remove(Task task)
    {
        if (task == null || head == null) {
            return false;
        } else {
            NodeTask item = head;
            for(NodeTask i = head; i != null; i = i.getNext())
            {
                if (task.equals(i.getTaskValue())) {
                    if(i.equals(head)) {
                        head = head.getNext();
                        if (size == 1) {
                            last = head;
                        }
                    } else if (i.equals(last)) {
                        item.setNext(null);
                        last = item;
                    } else {
                        item.setNext(i.getNext());
                    }
                    size--;
                    return true;
                }
                item = i;
            }
            return false;
        }
    }
    // метод, що повертає кількість задач у списку
    public int size() { return size; }
    // метод, що повертає задачу, яка знаходиться на вказаному місці у списку
    public Task getTask(int index)
    {
        if (index < 0 || index >= size ) {
            throw new IndexOutOfBoundsException("Incorrectly entered index in the list");
        }
        else {
            NodeTask item = head;
            for (int i = 0; i != index; i++)
            {
                item = item.getNext();
            }
            return item.getTaskValue();
        }
    }
    // клас, що характеризує окремий вузол однозв`язного списку
    private static class NodeTask
    {
        private Task task;
        private NodeTask next;
        // конструктор
        public NodeTask(Task task)
        {
            this.task = task;
            next = null;
        }
        // метод для отримання значення "task" у вузлі списку
        public Task getTaskValue() { return task; }
        // метод для отримання наступного вузла у списку
        public NodeTask getNext() { return next; }
        // метод для встановлення наступного вузла у списку
        public void setNext(NodeTask next) { this.next = next; }
    }
    // // інтерфейс, який описує абстрактну ітерацію по набору об’єктів
    @Override
    public Iterator<Task> iterator()
    {
        return new Iterator<Task>()
        {
            private NodeTask nextNode = head;
            private NodeTask previousNode = null;
            // метод для перевірки наявності наступного елемента ітератора
            @Override
            public boolean hasNext()
            {
                return nextNode != null;
            }
            // метод для переходу до наступного елемента ітератора
            @Override
            public Task next()
            {
                previousNode = nextNode;
                nextNode = nextNode.next;
                return previousNode.task;
            }
            // метод для видалення елемента ітератора
            @Override
            public void remove()
            {
                if (previousNode == null) {
                    throw new IllegalStateException("Out of the list interval.");
                }
                else {
                    LinkedTaskList.this.remove(previousNode.task);
                    previousNode = null;
                }
            }
        };
    }
    // метод для перевірки рівності 2 об'єктів
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LinkedTaskList linkedTaskList = (LinkedTaskList) object;
        int count = 0;
        for (int i = 0; i != size; i++) {
            if (getTask(i).equals(linkedTaskList.getTask(i)))
                count++;
        }
        if (count == size) return true;
        else return false;
    }
    // метод для перевірки рівності 2 об'єктів повищеної продуктивності
    @Override
    public int hashCode()
    {
        int hash = 0;
        if (head  == null) return hash;
        NodeTask i; int j;
        for (i = head, j = 0; i != null && j != size; i = i.getNext(), j++) {
            hash += (j + 1) * i.getTaskValue().hashCode();
        }
        return hash;
    }
    // метод, що створює копію об'єкта (клонування)
    @Override
    public LinkedTaskList clone()
    {
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        for (int i = 0; i != size; i++) {
            linkedTaskList.add(getTask(i));
        }
        return linkedTaskList;
    }
    // метод, що повертає символьний рядок, яка описує відповідний об'єкт
    @Override
    public String toString()
    {
        String str = "";
        for(int i = 0; i != size; i++){
            str += getTask(i).toString() + "\n";
        }
        return str;
    }
}