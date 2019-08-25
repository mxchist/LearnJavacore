package Lesson_6.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main (String ... args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.print("Клиент подключился!");
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter outpw = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String str = sc.nextLine();
                System.out.println("Client " + str);
                if (str.equals("/end")) break;
                outpw.println("echo " + str);
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
}
