import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) {
        int port = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection established with " + clientSocket.getInetAddress());

                handleClient(clientSocket);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            boolean receiveFile = dataInputStream.readBoolean();

            if (receiveFile) {
                receiveFile(clientSocket);
            } else {
                sendFile(clientSocket);
            }

            dataOutputStream.writeBoolean(false); // File transfer completed
            System.out.println("File transfer completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(Socket clientSocket) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

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

    private static void sendFile(Socket clientSocket) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        String fileName = "file_to_send.txt"; // Change this to the name of the file you want to send
        File file = new File(fileName);

        if (!file.exists()) {
            dataOutputStream.writeBoolean(false); // File not found
            return;
        }

        dataOutputStream.writeBoolean(true); // File found
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
            System.out.println("File '" + fileName + "' sent to the client.");
        }
    }
}
