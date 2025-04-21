package dk.laj.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@ApplicationScoped
public class JobBean    {
    Logger LOG = LoggerFactory.getLogger(JobBean.class);
    Random random = new Random();

    RequestContextBean requestContextBean;

    @Inject
    public JobBean(RequestContextBean requestContextBean) {
        this.requestContextBean = requestContextBean;
    }

    public void doTheJob(String jobId) {
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOG.info("Compare with request context: {} = {}", jobId, requestContextBean.getJobId());
    }
}
