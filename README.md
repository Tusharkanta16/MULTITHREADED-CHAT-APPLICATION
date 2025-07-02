# MULTITHREADED-CHAT-APPLICATION
COMPANY: CODTECH IT SOLUTIONS

NAME: TUSHARKANTA BEHERA

INTERN ID: CT04DG24

DOMAIN NAME: Java Programming

DURATION: 4 WEEKS

MENTOR: NEELA SANTOSH


overview

This application enables multiple clients to connect to a server and communicate with each other in real-time. Here's an overview:

Key Components
1. Server: Handles incoming client connections, manages client threads, and broadcasts messages.
2. Client: Connects to the server, sends and receives messages.

How it Works
1. Client Connection: A client connects to the server using a socket.
2. Thread Creation: The server creates a new thread for each client connection.
3. Message Handling: Each client thread handles incoming messages from its client and broadcasts them to other clients.

Benefits
1. Concurrent Communication: Multiple clients can chat simultaneously.
2. Real-time Messaging: Messages are delivered instantly.

Java Implementation
We'll use Java's built-in socket and threading APIs ((link unavailable) and java.util.concurrent) to implement the multithreaded chat application.
