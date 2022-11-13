package com.example.vpbank.service;

import com.example.vpbank.model.VanpCgRefCodesFlag;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VanCgRefCodesFlagService {
    VanpCgRefCodesFlag getJob(int id);

    void updateStatusJob(int id , String status);

}
