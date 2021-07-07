package AdditionalTask.server;

import AdditionalTask.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8181);
            System.out.println("Server start");
            socket = server.accept();
            System.out.println("Client connect");
            try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                Player player = (Player)in.readObject();
                System.out.println(player);
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
