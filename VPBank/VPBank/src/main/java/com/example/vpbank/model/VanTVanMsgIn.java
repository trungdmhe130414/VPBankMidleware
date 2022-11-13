package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "VANP_TVAN_MSG_IN")
@AllArgsConstructor
@NoArgsConstructor
public class VanTVanMsgIn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(name = "seq_name", sequenceName = "vanp_tvan_msg_in_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "receive_arr_json")
    private String receiveArrJson;
    @Column(name = "status_scan")
    private Integer statusScan;
    @Column(name = "CREATEDDATE")
    private Timestamp createdDate;
    @Column(name = "UPDATEDDATE")
    private Timestamp updatedDate;
}
