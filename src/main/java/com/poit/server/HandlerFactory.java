package com.poit.server;

import com.poit.repository.entity.User;
import com.poit.server.handlers.AdminHandler;
import com.poit.server.handlers.ClientHandler;
import java.net.Socket;

public class HandlerFactory {

    public Runnable getHandler(User user, Socket socket) {
        return switch (user.getRole()) {
            case USER -> new ClientHandler(socket, user);
            case ADMIN -> new AdminHandler(socket, user);
        };
    }
}
