package AdditionalTask.client;

import AdditionalTask.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectTransferMain {


    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8181;

    public static void main(String[] args) {
        objectTransfer();
    }

    private static void objectTransfer(){
        try(Socket socket = new Socket(IP_ADDRESS,PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            Player player = new Player("Vladimir","Yakimov", 31);
            System.out.println(player);
            out.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

