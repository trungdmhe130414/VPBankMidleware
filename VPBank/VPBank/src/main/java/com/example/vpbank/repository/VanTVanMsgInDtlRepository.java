package com.example.vpbank.repository;

import com.example.vpbank.model.VanTVanMsgInDtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanTVanMsgInDtlRepository extends JpaRepository<VanTVanMsgInDtl , Integer> {
}
