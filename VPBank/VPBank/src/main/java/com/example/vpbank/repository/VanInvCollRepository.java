package com.example.vpbank.repository;

import com.example.vpbank.model.VanInvColl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VanInvCollRepository extends JpaRepository<VanInvColl , Integer> {
    @Query(name = "SELECT id from VANP_INV_COLL WHERE TRAN_CODE = :tranCode" , nativeQuery = true)
    VanInvColl getAllIdByTranCode(String tranCode);
}
