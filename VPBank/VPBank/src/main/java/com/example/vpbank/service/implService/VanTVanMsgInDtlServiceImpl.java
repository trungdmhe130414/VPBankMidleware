package com.example.vpbank.service.implService;

import com.example.vpbank.job.JobFTVan;
import com.example.vpbank.model.VanTVanMsgIn;
import com.example.vpbank.model.VanTVanMsgInDtl;
import com.example.vpbank.repository.VanInvCollRepository;
import com.example.vpbank.repository.VanTVanMsgInDtlRepository;
import com.example.vpbank.service.VanTVanMsgInDtlService;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VanTVanMsgInDtlServiceImpl implements VanTVanMsgInDtlService {

    private static final Logger logger = LogManager.getLogger(JobFTVan.class);
    @Autowired
    private VanTVanMsgInDtlRepository vanTVanMsgInDtlRepository;

    @Override
    public List<VanTVanMsgInDtl> addVanTVanMsgInDtl(List<VanTVanMsgIn> listProcess) {
        List<VanTVanMsgInDtl> listAdd = new ArrayList<>();
        try {
            listProcess.forEach(
                    x -> {
                        String jsonVanIn = x.getReceiveArrJson();
                        // parse về obj để xử lý
                        JSONArray arrayObj = new JSONArray(jsonVanIn);
                        for(int i = 0; i < arrayObj .length(); i++)
                        {
                            JSONObject obj = arrayObj.getJSONObject(i);
                            int infoType = obj.getInt("loaiTBao");
                            if(infoType == 13 || infoType == 12){
                                // get ra các thông tin cần thiết để insert
                                String nameNotice = getStringFromJson(obj ,"tenTBao");
                                String taxCode = getStringFromJson(obj , "mst");
                                JSONObject contentNotice = obj.getJSONObject("ndungTBao");
                                String contentNoticeString = contentNotice.toString();
                                String tranId = getStringFromJson(contentNotice , "maGDichTChieu");
                                String receiveJson = obj.toString();
                                Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
                                String createdDTNotice = getStringFromJson(obj , "ngayTaoTBao");
                                Timestamp createdDTNoticeSql = Timestamp.valueOf(createdDTNotice.replace("T" , " "));
                                VanTVanMsgInDtl vTVMID = new VanTVanMsgInDtl();


                                if(infoType == 13){
                                    try {
                                        JSONObject returnDataInfo = contentNotice.getJSONObject("tbaoKTraDLieu");
                                        JSONArray listError = returnDataInfo.getJSONArray("dsachHDonBTHLoi");
                                        if(listError.isEmpty()){
                                            vTVMID.setTypeNotice(infoType + "");
                                        }else {
                                            vTVMID.setTypeNotice("14");
                                        }
                                    }catch (Exception e){
                                        vTVMID.setTypeNotice(infoType + "");
                                    }
                                }

                                vTVMID.setNameNotice(nameNotice);
                                vTVMID.setTaxCode(taxCode);
                                vTVMID.setCreatedDtNotice(createdDTNoticeSql);
                                vTVMID.setContentNotice(contentNoticeString);
                                vTVMID.setReceiveJson(receiveJson);
                                vTVMID.setMsgInId(x.getId());
                                vTVMID.setTransId(tranId);
                                vTVMID.setCreatedDate(createdDate);
                                listAdd.add(vTVMID);
                            }
                        }
                        if(listAdd != null){
                            vanTVanMsgInDtlRepository.saveAll(listAdd);
                        }
                    }
            );
            return listAdd;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    public String getStringFromJson(JSONObject object ,String info){
        try {
            return object.getString(info);
        }catch (JSONException e){
            logger.info(e.getMessage());
        }
        return null;
    }
}
