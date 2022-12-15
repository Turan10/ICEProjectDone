import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    /* The client class will have 2 threads - One thread is going to be the one that receives all the messages from the
    server and the other one is going to receive our consoleLineInput so that we're able to enter the messages
    NOTE: A thread in Java is the path followed when executing a program. All Java programs have at least one thread,
    known as the main thread, which is created by the Java Virtual Machine (JVM) at the program’s start, when the main()
    method is invoked.*/

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    public void run() {
        //We're going to need a client socket here, instead of a server socket
        try {
            //We're using our localHost since we don't have an actual database - had it been some1 elses server, we'd
            //just put in their public IP-address
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {

        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
                    if (message.equals("/quit")) {
                        out.println(message);
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(message);
                    }
                }
            } catch (IOException e) {
                shutdown();

            }

        }


    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

}
