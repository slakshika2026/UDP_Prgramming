import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPClient {
   public static void main(String[] args) {
      DatagramSocket socket = null;

      try {
         // Message to send
         String message = "Hello server";
         byte[] buffer = message.getBytes();

         // Create DatagramSocket
         socket = new DatagramSocket();

         // Create packet to send
         DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 8080);
         socket.send(packet);
         System.out.println("Message sent to server");

         // Prepare buffer for response
         byte[] responseBuffer = new byte[256];
         DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

         // Receive response
         socket.receive(responsePacket);
         String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
         System.out.println("Server response: " + response);
      } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
      } finally {
         if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("Socket closed");
         }
      }
   }
}
