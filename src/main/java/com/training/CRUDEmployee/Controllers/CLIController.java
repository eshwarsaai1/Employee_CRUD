package com.training.CRUDEmployee.Controllers;

import java.util.Scanner;

public class CLIController {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose: \n 1.InMemoryOperations \n 2.FileSystemOperations \n 3.InDatabaseOperations \n 4.Exit");
        try {
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    InMemoryController.run();
                    break;
                case 2:
                    FileSystemController.run();
                    break;
                case 3:
                    InDatabaseController.run();
                case 4:
                    System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("input invalid" + e);
        }
    }
}