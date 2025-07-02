import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class mainserver {
    public static void main(String[] args) {
        final int PORT = 1234;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT + "...");

            while (true) {
                try {
                    // Accept client connection
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    // Handle client in new thread
                    ServerThread clientHandler = new ServerThread(clientSocket);
                    clientHandler.start();

                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Could not start server on port " + PORT);
            e.printStackTrace();
        }
    }
}
