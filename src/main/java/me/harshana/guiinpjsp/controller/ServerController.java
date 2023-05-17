package me.harshana.guiinpjsp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    public TextArea server_text_area;
    public TextField txtserver;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(2000);
                server_text_area.appendText("Server Started");
                socket = serverSocket.accept();
                txtserver.appendText("\nClient Accepted");
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                while (!message.equalsIgnoreCase("finish")) {
                    message = dataInputStream.readUTF();
                    server_text_area.appendText("\nClient: " + message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void serverSendOnAction(ActionEvent actionEvent) throws IOException {

        dataOutputStream.writeUTF(txtserver.getText().trim());
        dataOutputStream.flush();
    }
}
