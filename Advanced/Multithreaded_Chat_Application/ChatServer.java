package assignments.Multithreaded_Chat_Application;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345; // Port number
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Accept new client connections and spawn a new thread for each
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    // Method to broadcast a message to all clients except the sender
    public static void broadcast(String message, PrintWriter senderWriter) {
        for (PrintWriter writer : clientWriters) {
            if (writer != senderWriter) {
                writer.println(message);
            }
        }
    }

    // ClientHandler handles communication with a single client
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Setup input and output streams for communication with the client
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add the client's output stream to the set of writers
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Welcome message to the client
                out.println("Welcome to the chat! Type 'exit' to quit.");

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    // Broadcast the message to all other clients
                    broadcast(message, out);
                }
            } catch (IOException e) {
                System.out.println("Error with client communication: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
                // Remove the client from the writers list
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
        }
    }
}
