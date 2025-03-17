import enums.TaskStatus;
import enums.UserRole;
import exceptions.InvalidTaskStatusException;
import exceptions.InvalidUserRoleException;
import models.Task;
import models.TaskManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("1. Create User");
            System.out.println("2. Select User");
            System.out.println("3. Create Task");
            System.out.println("4. Toggle Task Visibility");
            System.out.println("5. Delete Task");
            System.out.println("6. View Tasks");
            System.out.println("7. Filter Tasks by Status");
            System.out.println("8. Exit");
            System.out.print("Enter an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> selectUser();
                case 3 -> createTask();
                case 4 -> toggleTaskVisibility();
                case 5 -> deleteTask();
                case 6 -> viewTasks();
                case 7 -> filterTasksByStatus();
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void createUser() {
        try {
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter role (ADMIN/REGULAR): ");
            UserRole role = UserRole.valueOf(scanner.nextLine().toUpperCase());
            taskManager.createUser(username, role);
        } catch(IllegalArgumentException e) {
            System.out.println(new InvalidUserRoleException().getMessage());
        }
    }

    private static void selectUser() {
        try {
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            taskManager.selectUser(username);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void createTask() {
        try {
            System.out.println();
            System.out.print("Enter task title: ");
            String title = scanner.nextLine();
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            System.out.print("Enter task status (PENDING | IN_PROGRESS | COMPLETED): ");
            TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
            taskManager.createTask(title, description, status);
        } catch(IllegalArgumentException e) {
            System.out.println("Status invalid");
        }
    }

    private static void toggleTaskVisibility() {
        try {
            System.out.println();
            System.out.print("Enter task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine();
            taskManager.toggleTaskVisibility(taskId);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deleteTask() {
        try {
            System.out.println();
            System.out.print("Enter task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine();
            taskManager.deleteTask(taskId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void viewTasks() {
        try {
            System.out.println();
            taskManager.viewTasks().forEach(System.out::println);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void filterTasksByStatus() {
        System.out.println("Enter status to filter (PENDING, IN_PROGRESS, COMPLETED): ");
        String input = scanner.nextLine().toUpperCase();
        try {
            TaskStatus status = TaskStatus.valueOf(input);
            List<Task> filteredTasks = taskManager.filterTasks(status);
            filteredTasks.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(new InvalidTaskStatusException(input).getMessage());
        }
    }
}