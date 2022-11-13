package com.example.vpbank.util;

import java.util.Date;

public class VPBankUtil {
    public static String getTransId(String sendMSGType , String sendMSGSrcId , Date sendDt){
        String transId = "";
        if(sendMSGSrcId != null && sendMSGSrcId != null && sendDt != null){
            String sendDtConvert = sendDt.toString().substring(0 , 10).replace("-" , "");
            transId = sendMSGSrcId + "_" + sendMSGSrcId + "_" + sendDtConvert;
            return transId;
        }
        return null;
    }
}
