import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient {
    public static void main(String[] args) throws Exception {
        //read data
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        //socket for client
        DatagramSocket clientSocket = new DatagramSocket();
        //local network
        InetAddress IPAddress = InetAddress.getByName("localhost");
        System.out.println("IP" + IPAddress);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while(true){
            // take user input
            System.out.println("Enter the data in lower case");
            String sentence = inFromUser.readLine();
            //data in bytes
            sendData = sentence.getBytes();
            //datagram which is to be sent
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            //send data to server
            clientSocket.send(sendPacket);
            //recived datagram
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //client socket recive listener to listen to coming messages
            clientSocket.receive(receivePacket);
            //bytes to String
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
        //clientSocket.close();
    }
}
