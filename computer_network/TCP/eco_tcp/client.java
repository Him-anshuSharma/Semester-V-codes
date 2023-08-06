import java.io.*;
import java.net.Socket;

class TCPC {
    public static void main(String[] args) throws Exception {
        // creates socket, communication channel
        Socket sock = new Socket("127.0.0.1", 4000);
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));

        String userInput;
        while (true) {
            // read input from the user
            userInput = keyRead.readLine();

            // send the user's input to the server
            pwrite.println(userInput);

            if (userInput.equalsIgnoreCase("exit")) {
                break; // Exit the loop if the user inputs "exit"
            }

            // read and print the server's response
            String serverResponse = socketRead.readLine();
            System.out.println(serverResponse);
        }

        // close and release resources
        pwrite.close();
        socketRead.close();
        keyRead.close();
        sock.close();
    }
}
