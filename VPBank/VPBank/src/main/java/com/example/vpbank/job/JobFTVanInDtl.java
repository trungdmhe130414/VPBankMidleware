package com.example.vpbank.job;

import com.example.vpbank.chain.ChainProperties;
import com.example.vpbank.model.VanTVanMsgIn;
import com.example.vpbank.model.VanTVanMsgInDtl;
import com.example.vpbank.model.VanpCgRefCodesFlag;
import com.example.vpbank.service.*;
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
import org.springframework.stereotype.Component;

import java.util.List;
/*
    Handle job 3 (Update data to VanTVanMsgInDtl)
 */
@Configuration
@EnableScheduling
public class JobFTVanInDtl {

    @Autowired
    public VanTVanMsgInService vanTVanMsgInService;
//
    @Autowired
    public VanTVanMsgInDtlService vanTVanMsgInDtlService;
//
    @Autowired
    public VanInvCollService vanInvCollService;

    private static final Logger logger = LogManager.getLogger(JobFTVanInDtl.class);

//    public JobFTVanInDtl(VanTVanMsgInService vanTVanMsgInService, VanTVanMsgInDtlService vanTVanMsgInDtlService, VanInvCollService vanInvCollService) {
//        this.vanTVanMsgInService = vanTVanMsgInService;
//        this.vanTVanMsgInDtlService = vanTVanMsgInDtlService;
//        this.vanInvCollService = vanInvCollService;
//    }

    /*
        Split the data in the table VanTVanMsgIn.
        Check type of notification
        If type = 14 update to table VanInvColl
    */
    @Autowired
    public VanCgRefCodesFlagService vanCgRefCodesFlagService;

    @Scheduled(fixedDelay = 100)
    public void scheduledJob3(){
        if(ChainProperties.CHAINJOB == 2){
            VanpCgRefCodesFlag jobCurrent = vanCgRefCodesFlagService.getJob(8);
            if(jobCurrent.getStatusJob().equals("0")) {
                logger.info("Start job 3 !" );
                try {
                    vanCgRefCodesFlagService.updateStatusJob(8, "1");
                    List<VanTVanMsgIn> listProcess = vanTVanMsgInService.getListVanTVanMsgProcess();
                    List<VanTVanMsgInDtl> listAdd = vanTVanMsgInDtlService.addVanTVanMsgInDtl(listProcess);
                    // insert vao cac bang VANP_INV
                    if (listAdd != null){
                        vanInvCollService.updateStatusCqt(listAdd);
                    }
                }catch (Exception e){
                    logger.error("Job 3 error : " + e);
                    e.printStackTrace();
                }finally {
                    ChainProperties.CHAINJOB = 0;
                }
                vanCgRefCodesFlagService.updateStatusJob(8 , "0");
                logger.info("End job 3 !" );
            }
        }else {
            logger.info("Job 3 is running or stop");
        }
    }
}
