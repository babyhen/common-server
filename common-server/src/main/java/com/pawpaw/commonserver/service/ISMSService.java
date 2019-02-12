package com.pawpaw.commonserver.service;

public interface ISMSService {

    /**
    *发送注册验证短信
    */
    public void sendRegistCode(String mobile, String regisCode);

}
