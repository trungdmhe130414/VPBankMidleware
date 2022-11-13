package com.example.vpbank.service;

import com.example.vpbank.model.VanTVanMsgOut;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VanTVanMsgOutService {

    public void updateTrainCode(List<VanTVanMsgOut> vanTVanMsgOutList);

    public List<VanTVanMsgOut> getVanTVanMsgOutByStatus();
}
