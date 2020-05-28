package aero._14bis.take_home_challenge.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequestMapping("metric")
public class MetricController {

    final MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService){
        this.metricService = metricService;
    }

    @GetMapping("/{key}/sum")
    public MetricModel getSum(@PathVariable String key){
        try {
            int sum = metricService.getSum(key);
            MetricModel metric = new MetricModel();
            metric.setValue(sum);
            return metric;
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("/{key}")
    public void postMetric(@PathVariable String key, @RequestBody MetricModel metricModel) {
        try{
            Date currentDataTime = new Date(System.currentTimeMillis());
            Metric metric = new Metric(metricModel.getValue(), currentDataTime);

            metricService.saveMetric(key, metric);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}
