package com.example.vpbank.job;


import com.example.vpbank.chain.ChainProperties;
import com.example.vpbank.model.VanTVanMsgOut;
import com.example.vpbank.model.VanpCgRefCodesFlag;
import com.example.vpbank.service.VanCgRefCodesFlagService;
import com.example.vpbank.service.VanTVanMsgOutService;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableScheduling
public class JobFTVan {
    @Setter(onMethod = @__({@Autowired, @Qualifier("dataSource")}))
    private DataSource dataSource;
    @Autowired
    public VanTVanMsgOutService vanTVanMsgOutService;

    @Autowired
    public VanCgRefCodesFlagService vanCgRefCodesFlagService;
    private static final Logger logger = LogManager.getLogger(JobFTVan.class);

    @Scheduled(fixedDelay = 100)
    public void scheduledJob1(){
        if(ChainProperties.CHAINJOB == 0){
            VanpCgRefCodesFlag jobCurrent = vanCgRefCodesFlagService.getJob(6);
            if(jobCurrent.getStatusJob().equals("0")){
                vanCgRefCodesFlagService.updateStatusJob(6 , "1");
                try {
                    logger.info("Start job 1 !" );
                    List<VanTVanMsgOut> vanTVanMsgOutList = vanTVanMsgOutService.getVanTVanMsgOutByStatus();
                    if(vanTVanMsgOutList != null){
                        vanTVanMsgOutService.updateTrainCode(vanTVanMsgOutList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("Error job 1 : " + e);
                }finally {
                    vanCgRefCodesFlagService.updateStatusJob(6 , "0");
                    ChainProperties.CHAINJOB = 1;
                }
                logger.info("End job 1 !" );
            }else {
                logger.info("Job 1 is running or stop");
            }
        }
    }
}

