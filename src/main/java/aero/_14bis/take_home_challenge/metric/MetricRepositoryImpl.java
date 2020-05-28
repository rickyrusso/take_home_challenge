package aero._14bis.take_home_challenge.metric;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricRepositoryImpl implements MetricRepository {

    private static HashMap<String, ArrayList<Metric>> metrics = new HashMap<String, ArrayList<Metric>>();

    @Override
    public int getSum(String key) throws Exception {
        final int EXPIRE_MINUTES = 60;

        if(!metrics.containsKey(key)){
            throw new Exception(String.format("There are no metrics with the key %s", key));
        }

        Date currentDataTime = new Date(System.currentTimeMillis());
        List<Metric> metricsList = metrics.get(key);

        // filter out metrics over MINUTES minutes
        List<Metric> filteredList = metricsList.stream()
            .filter(metric -> minutesDiff( metric.getDate(), currentDataTime ) <= EXPIRE_MINUTES)
            .collect(Collectors.toList());

        Integer sum = filteredList.stream()
                .mapToInt(metric -> metric.getValue())
                .sum();

        // filter out metrics over MINUTES minutes
        List<Metric> metricsToRemoveList = metricsList.stream()
                .filter(metric -> minutesDiff( metric.getDate(), currentDataTime ) > EXPIRE_MINUTES)
                .collect(Collectors.toList());

        // remove metric older then EXPIRE_MINUTES minutes
        for(Metric m : metricsToRemoveList){
            metricsList.remove(m);
        }

        return sum;
    }

    // helper function that returns the difference between the two times in minutes
    private int minutesDiff(Date date1, Date date2) {
        final int MINUTES_IN_MILLI = 1000 * 60;
        int minutes =(int) (date2.getTime() - date1.getTime()) / MINUTES_IN_MILLI;
        return minutes;
    }

    @Override
    public void saveMetric(String key, Metric metric) throws Exception {

        ArrayList<Metric> list;
        if(metrics.containsKey(key)){
            list = metrics.get(key);
        } else {
            list = new ArrayList<>();
            metrics.put(key, list);
        }

        list.add(metric);
    }
}
