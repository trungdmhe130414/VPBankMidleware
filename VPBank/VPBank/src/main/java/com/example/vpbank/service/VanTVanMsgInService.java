package com.example.vpbank.service;

import com.example.vpbank.model.VanTVanMsgIn;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VanTVanMsgInService{
    void saveVanTVanMsgIn(JSONArray array);

    List<VanTVanMsgIn> getListVanTVanMsgProcess();
}
