package yevhkil.uniconsoleapp;

import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import yevhkil.uniconsoleapp.command.CommandInvoker;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class UniConsoleAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UniConsoleAppApplication.class, args);

        if (args.length > 0) {
            log.error("Invalid usage. Use the interactive mode to enter commands.");
            return;
        }

        CommandInvoker commandInvoker = context.getBean(CommandInvoker.class);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the University Console Application!");
        System.out.println("Type 'exit' to quit the application.");

        while (true) {
            System.out.print("Enter a command: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            commandInvoker.executeCommand(userInput);
        }

        scanner.close();
        System.out.println("Thank you for using the University Console Application. Goodbye!");
    }

}
