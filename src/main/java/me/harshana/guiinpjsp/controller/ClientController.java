package me.harshana.guiinpjsp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientController {
    public TextArea client_txt_area;
    public TextField txtClient;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost",2000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                while (!message.equalsIgnoreCase("finish")) {
                    message = dataInputStream.readUTF();
                    client_txt_area.appendText("\nServer: " + message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void clientSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtClient.getText().trim());
        dataOutputStream.flush();
    }
}
