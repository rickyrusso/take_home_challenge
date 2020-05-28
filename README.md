The Take Home Challenge project consists of one RESTful servie, a service, and a repository.  This project also has a unit test to ensure metric as inserted and summed correctly. The service allows callers to insert metric values and associate it to a list.  The service keep track of the time it was inserted.  The service allows the caller to retreive the sum of metrics for a list by providing a key. The sum will only include metrics for the past one hour.  Metrics older than an hour will be removed from the list.

The RESTful service is called MetricController.  This control has two actions, postMetric and getSum.
The uri to the postMetric action is http://<HostName>:8080/metric/<Key>, where HostName is the fully qualified domain name and Key is the name of the Metric list.
The uri to the getSum action is http://<HostName>:8080/metric/<Key>/sum, where HostName is the fully qualified domain name and Key is the name of the Metric list you with to get the for.  This will only sum the values for metrics that are within an hour old.

The Metric service has two functions, saveMetric and getSum.  
The saveMetric function receives two values, a Key and a metric object. It validates input and if valid, will pass the Key and Metric to the metric repository for storage.
The getSum function receives a key value. It validates the key value and if valid, will pass the key valud to the metric repository and receives a sum of metrics for the list specified by the key.  Is then returns the sum to the caller of this function.

The Metric reository has two functions, saveMetric and getSum.
The saveMetric function receives two values, a Key and a metric object. If first checks if there is a list specified by the key.  If there is not one, then one will be created. The new Metric object is then added to the apporpriate list.
The getSum function receives a key value. If retrived the list specified by the key value and sums all the values that were added to the list with the past hour.  It returns the sum value to the service.
