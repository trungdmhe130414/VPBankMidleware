package com.example.vpbank.repository;

import com.example.vpbank.model.VanTVanMsgIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VanTVanMsgInRepository extends JpaRepository<VanTVanMsgIn , Integer> {
    @Query(value = "SELECT * from VANP_TVAN_MSG_IN where STATUS_SCAN = 0" , nativeQuery = true)
    ArrayList<VanTVanMsgIn> getVanTVanMsgInByStatusScan();

    @Query(value = "UPDATE VANP_TVAN_MSG_IN SET STATUS_SCAN = 1 WHERE ID = :id" , nativeQuery = true)
    void updateStatusScan(int id);
}
