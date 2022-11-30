package com.poit.server.handlers;

import com.poit.dao.impl.StudentsDAOImpl;
import com.poit.repository.entity.Student;
import com.poit.repository.entity.User;
import com.poit.repository.service.StudentsService;
import com.poit.repository.service.impl.StudentsServiceImpl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AdminHandler implements Runnable {

    private Socket socket;
    private User user;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private StudentsService dossierService;

    public AdminHandler(Socket socket, User user) {
        try {
            this.socket = socket;
            this.user = user;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dossierService = new StudentsServiceImpl(new com.poit.repository.impl.StudentsRepositoryImpl(new StudentsDAOImpl()));
        } catch (IOException e) {
            closeEverything(socket, dataInputStream, dataOutputStream);
        }
    }

    public AdminHandler(User user) {
        this.user = user;
    }

    public AdminHandler() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public AdminHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String initMessage = """
                    1. Show all students
                    2. Create student
                    3. Change student
                    """;

                sendMessage(initMessage);

                var msg = getMessageFromClient();

                switch (msg) {
                    case "1" -> showAllDossiers();
                    case "2" -> createDossier();
                    case "3" -> changeDossier();
                }
            } catch (IOException e) {
                closeEverything(socket, dataInputStream, dataOutputStream);
                break;
            }
        }
    }

    private void showAllDossiers() throws IOException {
        sendMessage(dossierService.findAll().toString());
    }

    private void createDossier() throws IOException {
        dossierService.add(parseDossier());
    }

    private void changeDossier() throws IOException {
        dossierService.update(parseDossier());
    }

    private String getMessageFromClient() throws IOException {
        int messageLength = dataInputStream.readInt();
        return new String(dataInputStream.readNBytes(messageLength), StandardCharsets.UTF_8);

    }

    private void sendMessage(String msg) throws IOException {
        dataOutputStream.writeInt(msg.getBytes(StandardCharsets.UTF_8).length);
        dataOutputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        dataOutputStream.flush();
    }

    private Student parseDossier() throws IOException {
        sendMessage("Please enter card num");
        var cardNum = getMessageFromClient();
        sendMessage("Please enter faculty");
        var faculty = getMessageFromClient();
        sendMessage("Please enter specialty");
        var specialty = getMessageFromClient();
        sendMessage("Please enter name");
        var name = getMessageFromClient();
        sendMessage("Please enter surname");
        var surname = getMessageFromClient();

        return new Student(cardNum, faculty, specialty, name, surname);
    }
    private void closeEverything(Socket socket,
        DataInputStream dataInputStream,
        DataOutputStream dataOutputStream) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
