package Lesson_6.Client;

import javafx.fxml.Initializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.Socket;
import java.util.Scanner;

public class Controller  {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    public void sendMsg() {
        try {
            System.out.println("Место для соообщения");
            out.writeUTF(new Scanner(System.in).next());
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void initialize() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Клиент запущен");

            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            while (true) {
                                String str = in.readUTF();

                                System.out.println(str);
                            }
                        }
                        catch (IOException exc)  {
                            exc.printStackTrace();
                        }
                        finally {
                            try {
                                socket.close();
                            }
                            catch (IOException exc) {
                                exc.printStackTrace();
                            }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            out.writeUTF(new Scanner(System.in).next());
                        }
                    }
                    catch (IOException exc)  {
                        exc.printStackTrace();
                    }
                    finally {
                        try {
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
}
