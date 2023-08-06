import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TCPS {

    // SAKSHI
    // public static void clearConsole() {

    // try {
    // // For Windows
    // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    // } catch (IOException | InterruptedException ex) {
    // ex.printStackTrace();
    // }
    // }

    // HIMANSHU
    public static void clearConsole() {
        try {
            // For Unix-like systems (macOS, Linux, etc.)
            new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        StringBuilder conversation = new StringBuilder();
        // creates server socket
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection");

        while (true) {
            // waits for client here
            Socket sock = sersock.accept();
            System.out.println("Connection Is successful and waiting for chatting");

            // create separate threads for reading and writing messages
            Thread readThread = new Thread(() -> {
                try {
                    // get input stream of the socket
                    InputStream istream = sock.getInputStream();
                    // reader to read input stream data
                    BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));

                    String clientMssg;
                    while ((clientMssg = fileRead.readLine()) != null) {

                        conversation.append("Client: " + clientMssg + "\n");
                        clearConsole();
                        System.out.println(conversation);
                        System.out.print("Server: ");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread writeThread = new Thread(() -> {
                try {
                    // printer to send message to the client
                    OutputStream ostream = sock.getOutputStream();
                    PrintWriter pwrite = new PrintWriter(ostream, true);
                    BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

                    String userInput;
                    while (true) {
                        System.out.print("Server: ");
                        userInput = keyRead.readLine();
                        conversation.append("Server: " + userInput + "\n");
                        clearConsole();
                        System.out.println(conversation);
                        // send the server's input to the client
                        pwrite.println(userInput);

                        if (userInput.equalsIgnoreCase("exit")) {
                            break; // Exit the loop if the server inputs "exit"
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // start both read and write threads
            readThread.start();
            writeThread.start();
        }
    }
}
