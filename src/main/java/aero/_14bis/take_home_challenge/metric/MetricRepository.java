package aero._14bis.take_home_challenge.metric;

public interface MetricRepository {
    int getSum(String key) throws Exception;
    void saveMetric(String key, Metric metric) throws Exception;
}
