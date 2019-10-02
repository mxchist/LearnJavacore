package Lesson_6.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
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
                            System.out.println("Client " + str);
                            if (str.equals("/end")) {
                                out.writeUTF("Сервер закрыт" + "\n");
                                break;
                            }
                            server.broadcastMsg(str);
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

    public void sendMsg(String msg) {
        try{
            out.writeUTF(msg);
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
