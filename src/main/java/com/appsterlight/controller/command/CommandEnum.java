package com.appsterlight.controller.command;

import com.appsterlight.controller.command.impl.get.ErrorCommand;
import com.appsterlight.controller.command.impl.get.HomeCommand;

public enum CommandEnum {
    HOME(new HomeCommand()), ERROR(new ErrorCommand());

    private FrontCommand command;

    CommandEnum(FrontCommand command) {
        this.command = command;
    }

    public FrontCommand getCommand() {
        return command;
    }

}
