package com.example.vpbank.service;

import com.example.vpbank.model.VanTVanMsgInDtl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VanInvCollService {
    void updateStatusCqt(List<VanTVanMsgInDtl> listProcess);
}
