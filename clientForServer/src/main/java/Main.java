import client.Client;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter your name, please..");
        input = scanner.nextLine();
        Client.sendToServer(input);
        while (true) {
            input = scanner.nextLine();
            Client.sendToServer(input);
            System.out.println("Enter your name or enter - 'stop'");
            input = scanner.nextLine();
            if(input.equals("stop")) {
                System.out.println("Close connection...");
                Client.sendToServer(input);
                break;
            } else {
                Client.sendToServer(input);
            }
        }

    }
}
