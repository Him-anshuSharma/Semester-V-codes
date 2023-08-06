import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer {
    public static void main(String args[]) throws Exception {
        //creating a server at port 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server is Up");

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            //datagram packet to store recieved data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //listener to listen to coming data
            serverSocket.receive(receivePacket);
            //bytes to string
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            //local network ip of client
            InetAddress IPAddress = receivePacket.getAddress();
            //client port number
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            //captilized data to bytes
            sendData = capitalizedSentence.getBytes();
            
            //send back
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
