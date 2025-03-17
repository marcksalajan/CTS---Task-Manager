package interfaces;

import enums.TaskStatus;
import models.Task;

import java.util.List;

public interface ITaskManager {
    void createTask(String title, String description, TaskStatus status);
    void toggleTaskVisibility(int taskId);
    void deleteTask(int taskId);
    List<Task> viewTasks();
    List<Task> filterTasks(TaskStatus status);
}
