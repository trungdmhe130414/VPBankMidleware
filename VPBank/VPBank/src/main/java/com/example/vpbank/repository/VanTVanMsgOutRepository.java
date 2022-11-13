package com.example.vpbank.repository;

import com.example.vpbank.model.VanTVanMsgOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VanTVanMsgOutRepository extends JpaRepository<VanTVanMsgOut , Integer> {
    @Query(value = "SELECT * FROM VANP_TVAN_MSG_OUT WHERE STATUS = 0" , nativeQuery = true)
    List<VanTVanMsgOut> getVanTVanMsgOutByStatus();
    @Query(value = "UPDATE VANP_TVAN_MSG_OUT SET TRAN_CODE = :tranCode , STATUS = 1\n" +
            "WHERE SEND_MSG_TYPE = :sendMSGType and SEND_MSG_SRC_ID = :sendMSGSrcId", nativeQuery = true)
    void updateTrainCode(String sendMSGType , String sendMSGSrcId , String tranCode);
}
