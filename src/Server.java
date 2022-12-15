import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    //An ArrayList that will contain all of our clients
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;

    //our threadPool
    private ExecutorService pool;

    public Server() {
        connections = new ArrayList<>();
        //done = false when we initialize
        done = false;
    }

    //Runnable is an inbuild interface in java. It means that this class can now be passed through a thread
    //or a thread pool and be executed - in order to use the Runnable interface, we need to make the run method

    @Override
    /*The run method accepts the clients connection
    The idea is that we're going to have a server that constantly listens for incoming connections, so clients can
    request to connect - The client then has to be able to accept these connection requests, and to do that we're going
    to open a new connectionHandler for each client that connects which will be done below as well in an inner class.
    (Note: An inner class (aka nested class) is basically another class that is declared inside another class.
    We use inner classes to logically group classes and interfaces in one place to be more readable and maintainable.
    Additionally, it can access all the members of the outer class, including private data members and methods.)*/

    public void run() {
        try {
            /* Note: Socket and ServerSocket. Sockets are commonly used for client and server interaction.
            The Client class uses a connection() API on a stream socket to establish connection to the server.
            Through this class, we can read and write message. The ServerSocket class is used to bind and listen
            to then accept clients.
             */
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                //Accepts a client socket - When we accept a connection we get a socket
                Socket client = server.accept();

                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                //Everytime we add a new connection, we want execute the handler which is going to run the run function
                pool.execute(handler);
            }
        } catch (IOException e) {
            shutdown();
        }

    }

    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    public void shutdown() {
        try {
            done = true;
            pool.shutdown();
            if (!server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } catch (IOException e) {
            //ignore
        }
    }


    /*ConnectionHandler - Will also implement Runnable and therefor also have its own run function.
    This class will be the one that *handles* all the client connections. We're going to pass the clients into it,
    and it will then handle all the individual connections that we accept from our run method in our Runnable class
    */
    class ConnectionHandler implements Runnable {

        /*Since we need to handle several clients, we'd like to make a constructor to avoid making a new socket everytime
        (socket = the client we have to be dealing with)
        */
        private Socket client;
        //When the client sends something, we're going to get it from in
        private BufferedReader in;
        //When we want to write something to the client we're going to use out
        private PrintWriter out;
        private String nickname;


        public ConnectionHandler(Socket client) {
            this.client = client;

        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);  //autoflush = true, so that we dont always to
                //manually flush the stream in order to send the messages
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //Now that our BufferReader and PrintWriter is made, we want to promp the client for a nickName
                out.println("Please enter your nickname: ");
                //Saving the nickname the client writes as a variable
                nickname = in.readLine();
                System.out.println(nickname + " connected!"); //this is just for us
                broadcast(nickname + " joined the chat!");

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/nick ")) {
                        String[] messageSplit = message.split(" ", 2); //Cannot make a space more than 2 when creating
                        //a name
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " renamed themselves to " + messageSplit[1]);
                            System.out.println(nickname + " renamed themselves to " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Successfully changed nickname to " + nickname);
                        } else {
                            out.println("No nickname provided!");
                        }
                    } else if (message.startsWith("/quit")) {
                        //When we quit a server, we want to shutdown but also broadcast that some1 has left the server
                        broadcast(nickname + " left the chat!");
                        shutdown();
                    } else {
                        broadcast(nickname + ": " + message);
                    }
                }

        /* Now we want to broadcast this to all the other clients. For that, we need a list of clients that are
        connected. In order to do that, we go back to our server class and create an ArrayList of connectionHandlers.
        After the ArrayList is made, we need to be able to broadcast messages to all the clients that are connected
         (is done with the 'send message' method below and the broadcast function found above ).
         */
            } catch (IOException e) {
                shutdown();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void shutdown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {

            }
        }


    }

    public static void main(String[] args) {
        Server server = new Server();
        //will run a connectionHandler for each connection
        server.run();
    }


}
