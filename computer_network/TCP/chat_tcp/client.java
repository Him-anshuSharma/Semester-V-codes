import java.io.*;
import java.net.Socket;

class TCPC {

    //two functions in chat 

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
        // creates socket, communication channel
        Socket sock = new Socket("127.0.0.1", 4000);

        // create a StringBuilder to store the conversation
        StringBuilder conversation = new StringBuilder();

        // create separate threads for reading and writing messages
        //Thread varname  = new Thread(function)
        Thread readThread = new Thread(() -> {
            try {
                InputStream istream = sock.getInputStream();
                BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));

                String serverResponse;
                while ((serverResponse = socketRead.readLine()) != null) {
                    conversation.append("Server: " + serverResponse + "\n");
                    clearConsole();
                    // Display the conversation after each message
                    System.out.println(conversation);
                    System.out.print("Client: ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //sending messages
        Thread writeThread = new Thread(() -> {
            try {
                //rasta
                OutputStream ostream = sock.getOutputStream();
                //gaadi
                PrintWriter pwrite = new PrintWriter(ostream, true);

                //dukan wala jo rasta batyega
                BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

                String userInput;
                while (true) {
                    System.out.print("Client: ");
                    userInput = keyRead.readLine();

                    // send the user's input to the server
                    conversation.append("Client: ").append(userInput).append("\n");
                    clearConsole();
                    // Display the conversation after each message
                    System.out.println(conversation);
                    pwrite.println(userInput);

                    if (userInput.equalsIgnoreCase("exit")) {
                        break; // Exit the loop if the user inputs "exit"
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
