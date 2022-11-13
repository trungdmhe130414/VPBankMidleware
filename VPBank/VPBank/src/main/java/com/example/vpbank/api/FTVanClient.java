package com.example.vpbank.api;

import com.example.vpbank.config.FTVanFeignConfig;
import com.example.vpbank.model.response.TradingCodeResponse;
import com.example.vpbank.request.FTVanRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "FTVanClient", url = "${ftvan-api-url}", configuration = FTVanFeignConfig.class)
public interface FTVanClient {
    @PostMapping(value = "/hdon/bthhdon" , consumes = {"application/json"})
    TradingCodeResponse getTradingCode(FTVanRequest ftVanRequest);

    @GetMapping(value = "/tbao/tcuu/tcuutbaomoi" , consumes = {"application/json"})
    String getInfo();

}
