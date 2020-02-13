package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
//    private Vector<ClientHandler> clients;
    private Vector<ClientHandler> clients;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<ClientHandler>();
        int clientNum = 1;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                clients.add(new ClientHandler(this, socket));
                broadcastMsg("Клиент Client" +
                        (clientNum < 10 ? "00" : clientNum < 100 ? "0" : "") +
                        clientNum + " подключился!");
                System.out.printf("Клиент Client%03d подключился! %n", clientNum);
                clients.get(clientNum - 1).setClientNum(clientNum);
                clientNum ++;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void broadcastMsg( String msg ) {
        for (ClientHandler c : clients) {
           c.sendMsg(msg);
        };
    }
}
