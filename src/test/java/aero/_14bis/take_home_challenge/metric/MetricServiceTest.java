package aero._14bis.take_home_challenge.metric;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class MetricServiceTest {

    private MetricService metricService;

    @Before
    public void setup(){
        MetricRepository metricRepository = new MetricRepositoryImpl();
        metricService = new MetricServiceImpl(metricRepository);
    }

    @Test
    public void getMetrics() throws Exception {
        Date currentDataTime = new Date(System.currentTimeMillis());

        metricService.saveMetric("testKey1", createMetric(4, 120));
        metricService.saveMetric("testKey1", createMetric(3, 30));
        metricService.saveMetric("testKey1", createMetric(7, 3));
        metricService.saveMetric("testKey1", createMetric(2, 2));

        int sum = metricService.getSum("testKey1");

        Assert.assertEquals(12, sum);
    }

    private Metric createMetric(int value, int minusMinutes) throws Exception{
        // calculate the newDateMilli so it is minusMinutes minutes in the past
        long newDateMilli = System.currentTimeMillis() - (minusMinutes * 1000 * 60);
        Date newDataTime = new Date(newDateMilli);
        return new Metric(value, newDataTime);
    }

}
