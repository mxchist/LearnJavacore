package ru.geekbrains.chat.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.PipedInputStream;
import java.io.IOException;
import java.util.List;

public class FileDownloadStage extends Stage {
    String nickFrom;
    PipedInputStream fin;

    public FileDownloadStage(String nickFrom, PipedInputStream fin) {
        this.nickFrom = nickFrom;
        this.fin = fin;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/fileDownload.fxml"));
            setTitle("Downloading file from " + nickFrom);
            Scene scene = new Scene(root, 200, 100);
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
