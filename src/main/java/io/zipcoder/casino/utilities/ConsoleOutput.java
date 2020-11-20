package io.zipcoder.casino.utilities;

import io.zipcoder.casino.utilities.Console;

public class ConsoleOutput {

    private Console console = new Console(System.in, System.out);

    public Console getConsole(){
        return this.console;
    }


}