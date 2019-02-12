package com.pawpaw.commonserver.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.pawpaw.commonserver.service.ISMSService;
import com.pawpaw.framework.core.ali.AliConfigProperties;
import com.pawpaw.framework.core.common.util.AssertUtil;
import com.pawpaw.framework.core.common.util.JsonUtil;
import com.pawpaw.framework.core.exception.BusinessException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信服务
 *
 * @author liujixin
 * @date 2019-02-12
 */
@Primary
@Service
public class AliSMSService implements ISMSService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliSMSService.class);


    @Autowired
    private AliConfigProperties properties;

    @PostConstruct
    public void checkKey() {
        AssertUtil.notBlank(properties.getAccessKey(), "阿里accesskey不能为空");
        AssertUtil.notBlank(properties.getSecrectKey(), "阿里secrectkey不能为空");
        AssertUtil.notBlank(properties.getSmsSignName(), "阿里signName不能为空");
    }


    @Override
    /**
     *发送注册验证短信
     */
    public void sendRegistCode(String mobile, String regisCode) {
        AssertUtil.notBlank(mobile, "手机号码不能为空");
        AssertUtil.notNull(regisCode, "注册码能为空");

        DefaultProfile profile = DefaultProfile.getProfile("default", properties.getAccessKey(), properties.getSecrectKey());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", this.properties.getSmsSignName());
        request.putQueryParameter("TemplateCode", "SMS_145593043");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", regisCode);
        String param = JsonUtil.object2Json(paramMap);
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            LOGGER.info("发送短信响应:{}", data);
            SendSMSResp r = JsonUtil.json2Object(data, SendSMSResp.class);
            if (StringUtils.equalsIgnoreCase(r.getCode(), "OK")) {
                return;
            } else {
                throw new BusinessException("发送短信失败,cause:" + r.getMessage());
            }

        } catch (Exception e) {
            throw new BusinessException(e);
        }


    }


    @Data
    public static class SendSMSResp {
        private String Message;
        private String RequestId;
        private String BizId;
        private String Code;

    }
}
