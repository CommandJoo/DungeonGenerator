package de.johannes;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    public final static byte INFO = 0, ERROR = 2, WARNING = 1;
    private final static List<String> log = new ArrayList<>();
    private static PrintStream out = System.out;

    public void setOut(File output) throws FileNotFoundException {
        out = new PrintStream(new BufferedOutputStream(new FileOutputStream(output)));
    }

    public void setOut(PrintStream stream) {
        out = stream;
    }

    public static void log(byte level, String message) {
        StringBuilder result = new StringBuilder("[").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/mm hh:mm:ss"))).append("]");
        result.append(switch (level) {
            case INFO:
                yield "[INFO]";
            case ERROR:
                yield "[ERROR]";
            case WARNING:
                yield "[WARNING]";
            default:
                yield "";
        });
        result.append(" ");
        result.append(message);
        out.println(result.toString());
        log.add(result.toString());
    }

    public static List<String> log() {
        return log;
    }
}
