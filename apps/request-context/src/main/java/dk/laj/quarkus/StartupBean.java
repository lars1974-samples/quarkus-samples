package dk.laj.quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.inject.Inject;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class StartupBean {
    Logger LOG = LoggerFactory.getLogger(StartupBean.class);

    JobBean jobBean;
    RequestContextBean requestContextBean;
    RequestContextController requestContextController;
    ManagedExecutor managedExecutor;

    @Inject
    public StartupBean(RequestContextController requestContextController, JobBean jobBean, RequestContextBean requestContextBean, ManagedExecutor managedExecutor) {
        this.requestContextController = requestContextController;
        this.jobBean = jobBean;
        this.requestContextBean = requestContextBean;
        this.managedExecutor = managedExecutor;
    }

    int scheduleCounter = 0;

    @Scheduled(every = "10s")
    void startJobs() {
        scheduleCounter++;

        for (int i = 1; i <= 3; i++) { // Starter 3 tråde
            String jobId = "job-" + scheduleCounter + "-" + i;
            managedExecutor.submit(() -> runJob(jobId));
        }
    }

    private void runJob(String jobId) {
        LOG.info("start: {} on {}", jobId, Thread.currentThread().getName());
        requestContextController.activate();
        requestContextBean.setJobId(jobId);
        LOG.debug("Context Bean Ref Id: {}", requestContextBean);
        jobBean.doTheJob(jobId);
        requestContextController.deactivate();
        LOG.info("end: {} on {}", jobId, Thread.currentThread().getName());
    }
}
