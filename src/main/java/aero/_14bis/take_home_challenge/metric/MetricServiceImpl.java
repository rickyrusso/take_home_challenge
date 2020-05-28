package aero._14bis.take_home_challenge.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MetricServiceImpl implements MetricService {

    final MetricRepository metricRepository;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository){
        this.metricRepository = metricRepository;
    }

    @Override
    public int getSum(String key) throws Exception {
        if(key == null || key.compareTo("") == 0){
            throw new Exception("key must have a value");
        }

        return metricRepository.getSum(key);
    }

    @Override
    public void saveMetric(String key, Metric metric) throws Exception {

        if(key == null || key.compareTo("") == 0){
            throw new Exception("key must have a value");
        }

        if(metric.getValue() <= 0){
            throw new Exception("The value must be positive");
        }

        if(metric.getDate().getTime() > System.currentTimeMillis()){
            throw new Exception("The date can not be in the future");
        }

        metricRepository.saveMetric(key, metric);
    }
}
