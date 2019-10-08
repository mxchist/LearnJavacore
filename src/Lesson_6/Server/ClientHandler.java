package Lesson_6.Server;
import com.sun.deploy.util.SessionState;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private int clientNum;
    private DataInputStream in;
    private DataOutputStream out;


    public ClientHandler (Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in =  new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.isEmpty() == false) {
                                if (str.equals("/end")) {
                                    sendMsg("Сервер закрыт" + "\n");
                                    break;
                                }
                                str = "Client" +
                                        (clientNum < 10 ? "00" : clientNum < 100 ? "0" : "") +
                                        clientNum + ": " + str;
                                server.broadcastMsg(str);
                            }
                        }
                    }
                        catch (IOException exc) {
                                exc.printStackTrace();
                        }
                    finally {
                        try {
                            in.close();
                            out.close();
                            socket.close();
                        }
                        catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    void setClientNum (int clientNum) {
        this.clientNum = clientNum;
    }

    int getClientNum () {
        return clientNum;
    }

    public void sendMsg(String msg) {
        try{
            out.writeUTF(msg);
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
