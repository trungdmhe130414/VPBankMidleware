package com.example.vpbank.response;

import lombok.Data;

@Data
public class BaseResponse <T>{
    private T data;
}
