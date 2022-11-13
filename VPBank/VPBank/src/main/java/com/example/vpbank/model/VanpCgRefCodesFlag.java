package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "VANP_CG_REF_CODES_FLAG")
@AllArgsConstructor
@NoArgsConstructor
public class VanpCgRefCodesFlag {
    @Id
    private Integer id ;
    @Column(name = "STATUS_JOB")
    private String statusJob;
    @Column(name = "TEMPLATE_CODE")
    private String templateCode;
}
