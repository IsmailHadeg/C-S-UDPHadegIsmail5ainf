import java.net.*;

public class Server {
    public static void main(String args[]) {
        try {
            // Creazione del socket del server con la porta 9876
            DatagramSocket serverSocket = new DatagramSocket(9876);

            // Array per memorizzare i dati ricevuti e da inviare
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                // Preparazione del pacchetto per ricevere i dati dal client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket); // Ricezione dei dati dal client

                // Conversione dei dati ricevuti in stringa
                String sentence = new String(receivePacket.getData());
                InetAddress IPAddress = receivePacket.getAddress(); // Indirizzo IP del client
                int port = receivePacket.getPort(); // Porta del client

                System.out.println("Messaggio ricevuto dal client: " + sentence);

                // Preparazione della risposta
                String risposta = "Ciao dal server";
                sendData = risposta.getBytes(); // Conversione della risposta in array di byte

                // Preparazione del pacchetto da inviare al client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket); // Invio della risposta al client
            }
        } catch (Exception e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }
}
