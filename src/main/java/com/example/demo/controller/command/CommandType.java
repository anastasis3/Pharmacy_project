package com.example.demo.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTER(new RegisterCommand()),
    CREATEORDER(new CreateOrderCommand()),
    VIEWORDERS(new ViewOrdersCommand()),
    UPDATEORDER(new UpdateOrderCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    DELETEORDER(new DeleteOrderCommand());

    private final BaseCommand command;
    private static final Logger logger = LogManager.getLogger(CommandType.class);
    CommandType(BaseCommand command) {
        this.command = command;
    }

    public BaseCommand getCommand() {
        return command;
    }

    public static BaseCommand defineCommand(String commandStr) {
        if (commandStr == null) {
            throw new IllegalArgumentException("commandStr cannot be null");
        }
        try {
            return CommandType.valueOf(commandStr.toUpperCase()).getCommand();
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.warn("Invalid command: {}", commandStr);
            return CommandType.DEFAULT.getCommand();
        }
    }
}
