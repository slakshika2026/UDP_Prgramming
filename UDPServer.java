import java.net.*;
import java.io.*;

public class UDPServer {

   public static void main(String[] args) {
      DatagramSocket socket = null;

      try {
         socket = new DatagramSocket(8080);
         byte[] buffer = new byte[256];

         DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
         System.out.println("Server is waiting for a client packet");

         socket.receive(packet);
         String recieved = new String(packet.getData(), 0, packet.getLength());
         System.out.println("Received from client: " + recieved);

         String response = "Hello from the server";
         byte[] responseData = response.getBytes();

         DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(),
               packet.getPort());

         socket.send(responsePacket);
         System.out.println("Response sent to client: " + response);

      } catch (IOException e) {
         System.out.println("I/O Error" + e.getMessage());

      } finally {
         if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("Socket closed");
         }
      }
   }

}