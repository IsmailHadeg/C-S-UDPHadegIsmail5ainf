import java.net.*;

public class Client {
    public static void main(String args[]) {
        try {
            // Creazione del socket del client
            DatagramSocket clientSocket = new DatagramSocket();

            // Indirizzo IP del server (localhost nel nostro caso)
            InetAddress IPAddress = InetAddress.getByName("localhost");

            // Array per memorizzare i dati da inviare e ricevere
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            // Messaggio da inviare al server
            String sentence = "Ciao dal client";
            sendData = sentence.getBytes(); // Conversione del messaggio in array di byte

            // Preparazione del pacchetto da inviare al server sulla porta 9876
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket); // Invio del messaggio al server

            // Preparazione del pacchetto per ricevere la risposta dal server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // Ricezione della risposta dal server

            // Conversione della risposta ricevuta in stringa
            String risposta = new String(receivePacket.getData());
            System.out.println("Risposta dal server: " + risposta); // Stampa della risposta ricevuta dal server

            // Chiusura del socket del client
            clientSocket.close();
        } catch (Exception e) {
            System.err.println("Errore nel client: " + e.getMessage());
        }
    }
}
