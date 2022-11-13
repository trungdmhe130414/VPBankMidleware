package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageContent {
    private String maGDichTChieu;
    private String ngayTBao;
    private String base64XML;
    private String maGDichDTu;
    private String maGDichTNDLieu;
    private String ngayCQTKy;
    private String mst;
}
