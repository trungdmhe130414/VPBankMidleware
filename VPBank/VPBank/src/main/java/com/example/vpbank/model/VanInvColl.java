package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "VANP_INV_COLL")
@AllArgsConstructor
@NoArgsConstructor
public class VanInvColl {
    @Id
    private Integer id;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "RESP_THUE")
    private String respThue;
    @Column(name = "SBTH")
    private String sbth;
    @Column(name = "BUYER_NAME")
    private String buyerName;
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Column(name = "INV_SEM_TYPE")
    private String invSemType;
    @Column(name = "INV_SEM")
    private String invSem;
    @Column(name = "INV_TURN")
    private Double invTurn;
    @Column(name = "INV_TURN_FLOAT")
    private Double invTurnFloat;
    @Column(name = "XML_REQ")
    private String xmlReq;
    @Column(name = "ITEM_TYPE")
    private Double itemType;
    @Column(name = "NOTICE_FLOAT")
    private String noticeFloat;
    @Column(name = "NOTICE_NAME")
    private String noticeName;
    @Column(name = "NOTICE_DATE")
    private Timestamp noticeDate;
    @Column(name = "ERROR_DES")
    private String errorDes;
    @Column(name = "ERROR_SOLVE")
    private String errorSolve;
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Column(name = "TESTLONG")
    private Double testLong;
    @Column(name = "INV_TURN_NUMBER")
    private Integer invTurnNumber;
    @Column(name = "NOTICE_NUMBER")
    private String noticeNumber;
    @Column(name = "BRANCH_CODE")
    private String branchCode;
    @Column(name = "TRAN_CODE")
    private String tranCode;
}
