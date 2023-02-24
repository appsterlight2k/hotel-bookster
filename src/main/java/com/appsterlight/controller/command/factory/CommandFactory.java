package com.appsterlight.controller.command.factory;

import com.appsterlight.controller.command.FrontCommand;
import com.appsterlight.controller.command.CommandEnum;
import com.appsterlight.exception.DispatcherException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CommandFactory {
    private CommandFactory() { }

    public static FrontCommand getCommand(HttpServletRequest req) throws DispatcherException {
        FrontCommand command;
        String reqCommand = req.getParameter("command");

        try {
            command = (reqCommand != null) ? CommandEnum.valueOf(reqCommand.toUpperCase()).getCommand() :
                        CommandEnum.ERROR.getCommand();
        } catch (IllegalArgumentException e) {
            command = CommandEnum.ERROR.getCommand();
            log.error("Unexpected command from request!", e.getMessage());
        }

        return command;
    }

}
