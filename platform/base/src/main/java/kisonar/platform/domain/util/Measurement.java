package kisonar.platform.domain.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;

public final class Measurement {

    private int start = 0;
    private int stop = 0;
    private long startLong = 0;
    private long stopLong = 0;

    private final Logger LOG = Logger.getLogger(Measurement.class.getCanonicalName());

    public Measurement() {
    }

    public void prepare() {
        start = 0;
        stop = 0;
        startLong = 0;
        stopLong = 0;
    }

    public void start() {
        start = LocalDateTime.now().getNano();
        startLong = Calendar.getInstance().getTime().getTime();
    }

    public void finish() {
        stop = LocalDateTime.now().getNano();
        stopLong = Calendar.getInstance().getTime().getTime();
    }

    public void printResult() {
        LOG.log(Level.INFO,String.format("Execution took %d", stop - start));
        LOG.log(Level.INFO,String.format("Execution took %d", stopLong - startLong));
       // System.out.println(String.format("Execution took %d", stop - start));
       // System.out.println(String.format("Execution took %d", stopLong - startLong));
    }
}
