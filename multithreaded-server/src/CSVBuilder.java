import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVBuilder {
    static PrintWriter logPrinter;

    public CSVBuilder(Long pid) throws IOException {
        
        String logFileName = "/home/felipe/Documentos/ptcc/multithreaded-server/src/log/log-PID_" + pid
                + ".csv";

        File logFile = new File(logFileName);
        FileWriter fileWriter = new FileWriter(logFile);
        logPrinter = new PrintWriter(fileWriter);

        logPrinter.println("Request URI, Com interrupção?, Número a verificar, Primo?, Sequência testada"); // colunas do .CSV
    }

    static public void writeCSV(String logMessage) {
        logPrinter.println(logMessage);
    }

    static public void close() {
        logPrinter.close();
    }
}