import java.io.*;
import java.net.Socket;

public class client1 {
    public static void main(String[] args) {
        try {
            Socket s1 = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(s1.getOutputStream(), true);

            // Thread to read messages from server
            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = serverInput.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            readThread.start();

            // Main thread to send messages
            System.out.println("Enter messages (type QUIT to exit):");
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                serverOutput.println(userMessage);
                if (userMessage.equalsIgnoreCase("QUIT")) break;
            }

            s1.close();
        } catch (Exception e) {
            System.out.println("Client1 error: " + e.getMessage());
        }
    }
}
