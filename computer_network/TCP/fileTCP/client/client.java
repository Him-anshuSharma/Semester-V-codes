import java.io.*;
import java.net.Socket;

class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 12345;

        try (Socket clientSocket = new Socket(serverAddress, port);
             DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())) {

            // Sending file from client to server
            dataOutputStream.writeBoolean(true); // Request to send file
            sendFile(clientSocket, dataInputStream, dataOutputStream);

            // Receiving file from server
            dataOutputStream.writeBoolean(false); // Request to receive file
            receiveFile(clientSocket, dataInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(Socket clientSocket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        String fileName = "hello.txt"; // Change this to the name of the file you want to send
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File '" + fileName + "' not found.");
            return;
        }

        dataOutputStream.writeUTF(fileName);

        long fileSize = file.length();
        dataOutputStream.writeLong(fileSize);

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesSent = 0;

            while (totalBytesSent < fileSize && (bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
                totalBytesSent += bytesRead;
            }
            System.out.println("File '" + fileName + "' sent to the server.");
        }
    }

    private static void receiveFile(Socket clientSocket, DataInputStream dataInputStream) throws IOException {
        boolean fileFound = dataInputStream.readBoolean();

        if (!fileFound) {
            System.out.println("Requested file not found on the server.");
            return;
        }

        String fileName = dataInputStream.readUTF();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            long fileSize = dataInputStream.readLong();
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesReceived = 0;

            while (totalBytesReceived < fileSize && (bytesRead = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesReceived))) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                totalBytesReceived += bytesRead;
            }

            System.out.println("File '" + fileName + "' received and saved.");
        }
    }
}
