package com.example.vpbank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FTVanRequest {
    private String base64XML;
    private String transId;
}
