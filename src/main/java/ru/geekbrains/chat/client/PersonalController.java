package ru.geekbrains.chat.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.lang.Thread;
public class PersonalController {
    @FXML
    Button btnSent;

    @FXML
    Button btnUpload;

    @FXML
    TextArea textArea;

    DataOutputStream out = ((MiniStage)btnSent.getScene().getWindow()).out;
    String nickTo = ((MiniStage)btnSent.getScene().getWindow()).nickTo;

    public void btnClick() {
        if(!((MiniStage)btnSent.getScene().getWindow()).parentList.contains(textArea)) {
            ((MiniStage)btnSent.getScene().getWindow()).parentList.add(textArea);
        }
        try {
            out.writeUTF("/w " + nickTo + " " + textArea.getText());
            textArea.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonClickUpload() {
        DataOutputStream fout = ((MiniStage)btnUpload.getScene().getWindow()).fout;

        FileChooser fileChooser = new FileChooser();
        File fileToUpload = fileChooser.showOpenDialog((MiniStage)btnUpload.getScene().getWindow());
        try (FileInputStream fis = new FileInputStream(fileToUpload)) {
                byte b[];
                try {
                    b = new byte[fis.available()];
                        fout.write(b);
                        out.writeUTF("/putFile " + nickTo);
                }
                catch (IOException exc) {
                    exc.printStackTrace();
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
