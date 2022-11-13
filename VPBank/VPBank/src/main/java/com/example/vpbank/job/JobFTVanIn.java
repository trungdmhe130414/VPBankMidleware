package com.example.vpbank.job;

import com.example.vpbank.api.FTVanClient;
import com.example.vpbank.chain.ChainProperties;
import com.example.vpbank.model.VanTVanMsgOut;
import com.example.vpbank.model.VanpCgRefCodesFlag;
import com.example.vpbank.service.VanCgRefCodesFlagService;
import com.example.vpbank.service.VanTVanMsgInService;
import com.example.vpbank.service.VanTVanMsgOutService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class JobFTVanIn{

    @Autowired
    public FTVanClient ftVanClient ;
//
    @Autowired
    public VanTVanMsgInService vanTVanMsgInService;

    private static final Logger logger = LogManager.getLogger(JobFTVanIn.class);
    @Autowired
    public VanCgRefCodesFlagService vanCgRefCodesFlagService;

    @Scheduled(fixedDelay = 100)
    public void scheduledJob2(){
        if (ChainProperties.CHAINJOB == 1) {
            VanpCgRefCodesFlag jobCurrent = vanCgRefCodesFlagService.getJob(7);
            if(jobCurrent.getStatusJob().equals("0")) {
                logger.info("Start job 2 !");
                try {
                    vanCgRefCodesFlagService.updateStatusJob(7, "1");
                    String data = ftVanClient.getInfo();
                    JSONArray arrData = new JSONArray(data);
                    vanTVanMsgInService.saveVanTVanMsgIn(arrData);
                    logger.info("oke job 2");
                } catch (Exception e) {
                    logger.error("Error job 2 : " + e);
                }finally {
                    ChainProperties.CHAINJOB = 2;
                }
                vanCgRefCodesFlagService.updateStatusJob(7 , "0");
                logger.info("End job 2 !");
            }else {
                logger.info("Job 2 is running or stop");
            }
        }
    }
}
