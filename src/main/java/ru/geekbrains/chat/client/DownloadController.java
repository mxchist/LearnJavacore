package ru.geekbrains.chat.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.lang.Thread;

public class DownloadController {

    @FXML
    VBox downloadPanel;

    @FXML
    Button yes;

    @FXML
    Button no;

    @FXML
    TextArea messageText;

    public void acceptDownloading() {
        DataInputStream in = ((FileDownloadStage)yes.getScene().getWindow()).in;

        FileChooser fileChooser = new FileChooser();
        File fileToUpload = fileChooser.showOpenDialog(yes.getScene().getWindow());
        try (FileOutputStream fos = new FileOutputStream(fileToUpload)) {
            new Thread(() -> {
                int x;
                try {
                    while ((x = in.read()) > -1 ) {
                        fos.write(x);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectDownloading() {
        Platform.exit();
    }
}
