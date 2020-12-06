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
        DataInputStream fin = ((FileDownloadStage)yes.getScene().getWindow()).fin;

        FileChooser fileChooser = new FileChooser();
        File fileToDownload = fileChooser.showSaveDialog(yes.getScene().getWindow());
        try (FileOutputStream fos = new FileOutputStream(fileToDownload)) {
                byte[] b;
                try {
                    b = new byte [fin.available()];
                    if (fin.read(b) > 0) {
                        fos.write(b);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((FileDownloadStage) yes.getScene().getWindow()).close();
    }

    public void rejectDownloading() {
        ((FileDownloadStage)no.getScene().getWindow()).close();
    }
}
