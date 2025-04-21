package dk.laj.quarkus;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestContextBean {
    public static int counter = 0;
    String jobId;

    public int getCounter() {
        return counter;
    }

    public RequestContextBean() {
        counter++;
    }

   String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
