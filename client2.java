import java.io.*;
import java.net.Socket;

public class client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Thread to read messages from server
            Thread receiveThread = new Thread(() -> {
                String response;
                try {
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by server.");
                }
            });
            receiveThread.start();

            System.out.println("Enter messages (type QUIT to exit):");
            String inputLine;
            while ((inputLine = userInput.readLine()) != null) {
                out.println(inputLine);
                if (inputLine.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
