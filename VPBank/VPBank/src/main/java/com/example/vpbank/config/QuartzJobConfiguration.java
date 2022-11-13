//package com.example.vpbank.config;
//
//import com.example.vpbank.api.FTVanClient;
//import com.example.vpbank.job.JobFTVan;
//import com.example.vpbank.job.JobFTVanIn;
//import com.example.vpbank.job.JobFTVanInDtl;
//import com.example.vpbank.repository.VanInvCollRepository;
//import com.example.vpbank.repository.VanTVanMsgInDtlRepository;
//import com.example.vpbank.repository.VanTVanMsgInRepository;
//import com.example.vpbank.repository.VanTVanMsgOutRepository;
//import lombok.Setter;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.quartz.listeners.JobChainingJobListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
//@Configuration
//public class QuartzJobConfiguration {
//
//    private static final Logger logger = LogManager.getLogger(QuartzJobConfiguration.class);
//
//    @Bean
//    public JobDetail JobFTVan(){
//        return JobBuilder.newJob(JobFTVan.class)
//                .withIdentity("JobFTVan")
//                .storeDurably(true)
//                .build();
//    }
//    @Bean
//    public JobDetail JobFTVanIn(){
//        return JobBuilder.newJob(JobFTVanIn.class)
//                .withIdentity("JobFTVanIn")
//                .storeDurably(true)
//                .build();
//    }
//    @Bean
//    public JobDetail JobFTVanInDtl(){
//        return JobBuilder.newJob(JobFTVanInDtl.class)
//                .withIdentity("JobFTVanInDtl")
//                .storeDurably(true)
//                .build();
//    }
//    @Bean
//    public JobChainingJobListener jobChainingJobListener(){
//        JobChainingJobListener jobListener = new JobChainingJobListener("ChainListener");
//        jobListener.addJobChainLink(JobFTVan().getKey() , JobFTVanIn().getKey());
//        jobListener.addJobChainLink(JobFTVanIn().getKey() , JobFTVanInDtl().getKey());
//        return jobListener;
//    }
//    @Bean
//    public Trigger jobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
//                .simpleSchedule()
//                .withIntervalInSeconds(1)
//                .repeatForever();
//
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(JobFTVan())
//                .withIdentity("QuartzJobTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
////    @Bean
////    public Scheduler jobScheduler() throws SchedulerException {
////        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
////        scheduler.getListenerManager().addJobListener(jobChainingJobListener());
////
////        scheduler.scheduleJob(JobFTVan(), jobTrigger());
////        scheduler.addJob(JobFTVanIn(), true);
////        scheduler.addJob(JobFTVanInDtl(), true);
////        scheduler.getListenerManager().addJobListener(jobChainingJobListener());
////        scheduler.start();
////        return scheduler;
////    }
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//        scheduler.setTriggers(jobTrigger());
//        scheduler.setJobDetails(JobFTVan() , JobFTVanIn() ,JobFTVanInDtl());
//        scheduler.setApplicationContextSchedulerContextKey("applicationContext");
//        scheduler.start();
//        return scheduler;
//    }
//}
