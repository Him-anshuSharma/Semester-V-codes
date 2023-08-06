import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TCPS {
    public static void main(String[] args) throws Exception {
        // creates server socket
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection");

        while (true) {
            // waits for client here
            Socket sock = sersock.accept();
            System.out.println("Connection Is successful and waiting for chatting");

            // get input stream of the socket
            InputStream istream = sock.getInputStream();
            // reader to read input stream data
            BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
            // printer to send message to the client
            OutputStream ostream = sock.getOutputStream();
            PrintWriter pwrite = new PrintWriter(ostream, true);
            String clientMssg;
            while ((clientMssg = fileRead.readLine()) != null) {
                String resp = "";
                if (clientMssg.equalsIgnoreCase("exit")) {
                    // Exit the loop if the client sends "exit"
                    break;
                }

                if (clientMssg.contains(" ")) {
                    String[] tokens = clientMssg.split(" ");
                    if (tokens[0].equals("echo")) {
                        for(int i=1;i<tokens.length;i++){
                            resp += tokens[i];
                        }
                        pwrite.println(resp);
                    } else {
                        pwrite.println("Invalid command: " + tokens[0]);
                    }
                } else {
                    pwrite.println("Invalid input format. Please use 'echo message' or 'exit'.");
                }
            }

            // close the connection with this client
            pwrite.close();
            fileRead.close();
            sock.close();
            System.out.println("Connection with client closed.");
        }
    }
}
