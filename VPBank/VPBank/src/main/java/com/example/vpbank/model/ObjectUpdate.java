package com.example.vpbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectUpdate {
    private String invNumber;
    private String invSeries;
    private String templateCode;
}
