package models;

import enums.TaskStatus;

public class Task {
    private static int idCounter = 1;
    private final int id;
    private final String title;
    private final String description;
    private boolean hidden;
    private TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.hidden = false;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isHidden() { return hidden; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public void toggleVisibility() { hidden = !hidden; }

    @Override
    public String toString() {
        return "Task[" + id + "] " + title + " - " + description + " (" + status + ", " + (hidden ? "Hidden" : "Visible") + ")";
    }
}
