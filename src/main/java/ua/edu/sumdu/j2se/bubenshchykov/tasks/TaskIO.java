package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import com.google.gson.Gson;
import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

public class TaskIO
{
    // метод, що записує задачі із списку у потік у бінарному форматі
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException
    {
        try(DataOutputStream outputStream = new DataOutputStream(out))
        {
            outputStream.writeInt(tasks.size());
            Iterator<Task> taskIterator = tasks.iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeLong(task.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                    outputStream.writeLong(task.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                } else {
                    outputStream.writeLong(task.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод, що зчитує задачі із потоку у даний список задач
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException
    {
        try(DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();
            while (size > 0) {
                int titleSize = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                Task task;
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
                size--;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод, що записує задачі із списку у файл
    public static void writeBinary(AbstractTaskList tasks, File file)
    {
        try (FileOutputStream fileOutput = new FileOutputStream(file)) {
            write(tasks, fileOutput);
            fileOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод, зчитує задачі із файлу у список задач
    public static void readBinary(AbstractTaskList tasks, File file)
    {
        try (FileInputStream fileInput = new FileInputStream(file)) {
            read(tasks, fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод, що записує задачі зі списку у потік у форматі JSON
    public static void write(AbstractTaskList tasks, Writer out) throws IOException
    {
        Gson gson = new Gson();
        ArrayTaskList list = new ArrayTaskList();
        tasks.getStream().forEach(list::add);
        gson.toJson(list, out);
        out.flush();
    }
    // метод, що зчитує задачі із потоку у список
    public static void read(AbstractTaskList tasks, Reader in)
    {
        Gson gson = new Gson();
        ArrayTaskList list = gson.fromJson(in, ArrayTaskList.class);
        list.getStream().forEach(tasks::add);
    }
    // метод, що записує задачі у файл у форматі JSON
    public static void writeText(AbstractTaskList tasks, File file)
    {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод, що зчитує задачі із файлу
    public static void readText(AbstractTaskList tasks, File file)
    {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}