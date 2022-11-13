package com.example.vpbank.service.implService;

import com.example.vpbank.api.FTVanClient;
import com.example.vpbank.job.JobFTVan;
import com.example.vpbank.model.VanTVanMsgOut;
import com.example.vpbank.repository.VanTVanMsgOutRepository;
import com.example.vpbank.request.FTVanRequest;
import com.example.vpbank.service.VanTVanMsgOutService;
import com.example.vpbank.util.VPBankUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@Order(1)
@Slf4j
public class VanTVanMsgOutServiceImpl implements VanTVanMsgOutService {
    @Autowired
    private VanTVanMsgOutRepository vanTVanMsgOutRepository;
    @Autowired
    public FTVanClient ftVanClient ;

    private static final Logger logger = LogManager.getLogger(JobFTVan.class);


    @Override
    public void updateTrainCode(List<VanTVanMsgOut> vanTVanMsgOutList) {
        try {
            if(vanTVanMsgOutList != null){
                vanTVanMsgOutList.stream().parallel().forEach(
                        x -> {
                            String base64 = Base64.getEncoder().encodeToString(x.getSendMsgContent().getBytes());
                            String transId = VPBankUtil.getTransId(x.getSendMsgType() , x.getSendMsgSrcId() , x.getSendDt());
                            FTVanRequest request = new FTVanRequest();
                            request.setBase64XML(base64);
                            request.setTransId(transId);
                            String maGDich = ftVanClient.getTradingCode(request).getMaGDich();
                            try {
                                vanTVanMsgOutRepository.updateTrainCode(x.getSendMsgType() , x.getSendMsgSrcId() , maGDich);
                            }catch (Exception e){
                                logger.info("Error update tran code job1 : " + e);
                            }
                        }
                );
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public List<VanTVanMsgOut> getVanTVanMsgOutByStatus() {
        try {
            List<VanTVanMsgOut> list = vanTVanMsgOutRepository.getVanTVanMsgOutByStatus();
            return list;
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }
}
