package ua.edu.sumdu.j2se.bubenshchykov.tasks;

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
}