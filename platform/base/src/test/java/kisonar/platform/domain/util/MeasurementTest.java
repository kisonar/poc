package kisonar.platform.domain.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class MeasurementTest {

    private Measurement measurement;

    @BeforeEach
    public void setUp(){
        measurement = new Measurement();
    }

    @Test
    public void measure() throws InterruptedException {
        measurement.start();
        TimeUnit.SECONDS.sleep(1);
        measurement.finish();
        measurement.printResult();
    }
}