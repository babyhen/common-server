package com.pawpaw.commonserver.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("common-server")
@RequestMapping(value = "/sms")
public interface ISMSController {

    @ApiOperation("发送注册验证码短信")
    @PutMapping(value = "/sendRegistryCode")
    public boolean sendRegistryCode(@RequestParam("mobile") String mobile,
                           @RequestParam("code") String code);


}





