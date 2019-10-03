package Lesson_6.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.Socket;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
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
                                textArea.appendText(str + "\n");
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
//        finally {
//            try {
//                socket.close();
//            }
//            catch (IOException exc) {
//                exc.printStackTrace();
//            }
//        }
    }
}
