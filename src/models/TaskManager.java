package models;

import enums.TaskStatus;
import enums.UserRole;
import exceptions.TaskNotFoundException;
import exceptions.UnauthorizedActionException;
import exceptions.UserNotFoundException;
import interfaces.ITaskManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager implements ITaskManager {
    private final List<User> users = new ArrayList<>();
    private User currentUser;

    public void createUser(String username, UserRole role) {
        users.add(new User(username, role));
    }

    public void selectUser(String username) {
        currentUser = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().orElse(null);
        if(currentUser == null) {
            System.out.println(new UserNotFoundException("Selected user not found").getMessage());
            return;
        }
        System.out.println("Selected user: " + currentUser);
    }

    @Override
    public void createTask(String title, String description, TaskStatus status) {
        if (currentUser == null) {
            System.out.println(new UserNotFoundException("No user selected!").getMessage());
            return;
        }
        Task task = new Task(title, description, status);
        currentUser.addTask(task);
        System.out.println("Task added successfully");
    }

    @Override
    public void toggleTaskVisibility(int taskId) {
        Task task = findTaskById(taskId);
        if(task == null) {
            System.out.println(new TaskNotFoundException("Task not found by id.").getMessage());
            return;

        }
        task.toggleVisibility();
        System.out.println("Task visibility updated successfully");
    }

    @Override
    public void deleteTask(int taskId) {
        if(currentUser == null) {
            System.out.println(new UserNotFoundException("No user selected!").getMessage());
            return;
        }
        if (currentUser.getRole() != UserRole.ADMIN) {
            System.out.println(new UnauthorizedActionException("delete task").getMessage());
            return;
        }
        currentUser.getTasks().removeIf(task -> task.getId() == taskId);
        System.out.println("Task deleted successfully");
    }

    @Override
    public List<Task> viewTasks() {
        if (currentUser == null) {
            System.out.println(new UserNotFoundException("No user selected!").getMessage());
            return Collections.emptyList();
        }
        if(currentUser.getRole() == UserRole.ADMIN) {
            return users.stream().flatMap(user -> user.getTasks().stream()).collect(Collectors.toList());
        }
        return users.stream()
                .flatMap(user -> user.getTasks().stream())
                .filter(task -> !task.isHidden())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> filterTasks(TaskStatus status) {
        if (currentUser == null) {
            System.out.println(new UserNotFoundException("No user selected!").getMessage());
            return Collections.emptyList();
        }
        return users.stream()
                .flatMap(user -> user.getTasks().stream())
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    private Task findTaskById(int taskId) {
        return users.stream()
                .flatMap(user -> user.getTasks().stream())
                .filter(task -> task.getId() == taskId)
                .findFirst().orElse(null);
    }
}
