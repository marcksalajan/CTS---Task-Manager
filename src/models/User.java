package models;

import enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final UserRole role;
    private final List<Task> tasks = new ArrayList<>();

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public UserRole getRole() { return role; }
    public List<Task> getTasks() { return tasks; }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return this.username + " " + "(" + this.role + ")";
    }
}
