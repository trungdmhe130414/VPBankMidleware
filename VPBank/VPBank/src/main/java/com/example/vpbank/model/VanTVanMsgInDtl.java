package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "VANP_TVAN_MSG_IN_DTL")
@AllArgsConstructor
@NoArgsConstructor
public class VanTVanMsgInDtl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(name = "seq_name", sequenceName = "vanp_tvan_msg_in_dtl_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "tax_code")
    private String taxCode;
    @Column(name = "name_notice")
    private String nameNotice;
    @Column(name = "type_notice")
    private String typeNotice;
    @Column(name = "content_notice")
    private String contentNotice;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "createddtnotice")
    private Timestamp createdDtNotice;
    @Column(name = "status_scan")
    private Integer statusScan;
    @Column(name = "updateddate")
    private Timestamp updatedDate;
    @Column(name = "transid")
    private String transId;
    @Column(name = "msg_in_id")
    private Integer msgInId;
    @Column(name = "receiveJson")
    private String receiveJson;
}
