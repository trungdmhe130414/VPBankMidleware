package com.example.vpbank.service.implService;

import com.example.vpbank.job.JobFTVan;
import com.example.vpbank.model.VanTVanMsgIn;
import com.example.vpbank.repository.VanTVanMsgInRepository;
import com.example.vpbank.service.VanTVanMsgInService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VanTVanMsgInServiceImpl implements VanTVanMsgInService {

    private static final Logger logger = LogManager.getLogger(JobFTVan.class);
    @Autowired
    private VanTVanMsgInRepository vanTVanMsgInRepository;

    @Override
    public void saveVanTVanMsgIn(JSONArray arrData) {
        try {
            if(arrData != null){
                for(int i = 0 ; i < arrData.length() ;  i++){
                    VanTVanMsgIn o = new VanTVanMsgIn();
                    LocalDateTime dateNow = LocalDateTime.now();
                    Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
                    o.setReceiveArrJson(arrData.get(i).toString());
                    o.setCreatedDate(createdDate);
                    o.setStatusScan(0);
                    // inser to data
                    vanTVanMsgInRepository.save(o);
                }
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    @Override
    public List<VanTVanMsgIn> getListVanTVanMsgProcess() {
        try {
            ArrayList<VanTVanMsgIn> listProcess = vanTVanMsgInRepository.getVanTVanMsgInByStatusScan();
            return listProcess;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }
}
