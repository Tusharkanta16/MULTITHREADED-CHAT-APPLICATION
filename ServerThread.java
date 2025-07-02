import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    // Shared list of all connected client output streams
    private static final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            clientWriters.add(out);

            String line;
            while ((line = in.readLine()) != null && !line.equalsIgnoreCase("QUIT")) {
                System.out.println("Client: " + line);
                broadcast("Client: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error in ServerThread: " + e.getMessage());
        } finally {
            try {
                if (out != null) clientWriters.remove(out);
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    // Send message to all clients
    private void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}
