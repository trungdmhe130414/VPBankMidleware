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
@Table(name = "VANP_TVAN_MSG_OUT")
@AllArgsConstructor
@NoArgsConstructor
public class VanTVanMsgOut {
    @Id
    private Integer id;
    @Column(name = "SEND_MSG_ID")
    private String sendMsgId;
    @Column(name = "SEND_MSG_TYPE")
    private String sendMsgType;
    @Column(name = "SEND_MSG_SRC_ID")
    private String sendMsgSrcId;
    @Column(name = "SEND_MSG_CONTENT")
    private String sendMsgContent;
    @Column(name = "SEND_DT")
    private Timestamp sendDt;
    @Column(name = "SEND_ERROR_MSG")
    private String sendErrorMsg;
    @Column(name = "RECEIVE_MSG")
    private String receiveMsg;
    @Column(name = "RECEIVE_DT")
    private String receiveDt;
    @Column(name = "RECEIVE_TRAN_NO")
    private String receiveTranNo;
    @Column(name = "RECEIVE_BUSINESS_MSG")
    private String receiveBusinessMsg;
    private Integer status;
    @Column(name = "TRAN_CODE")
    private String tranCode;
    @Column(name = "STATUS_RECV")
    private Integer statusReCv;
    @Column(name = "JSON_RECV")
    private String jsonReCv;
}
