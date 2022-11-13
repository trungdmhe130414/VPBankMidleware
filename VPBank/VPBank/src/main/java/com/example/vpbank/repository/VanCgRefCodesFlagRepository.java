package com.example.vpbank.repository;

import com.example.vpbank.model.VanpCgRefCodesFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface VanCgRefCodesFlagRepository extends JpaRepository<VanpCgRefCodesFlag , Integer> {
    @Query(value = "select * from VANP_CG_REF_CODES_FLAG where id = :id " , nativeQuery = true)
    VanpCgRefCodesFlag getJobById(int id);

    @Query(value = "Update VANP_CG_REF_CODES_FLAG SET STATUS_JOB = :status WHERE  id = :id" , nativeQuery = true)
    void updateStatusJob(int id , String status);
}
