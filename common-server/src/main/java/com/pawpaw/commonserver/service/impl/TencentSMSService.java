package com.pawpaw.commonserver.service.impl;

import com.pawpaw.commonserver.service.ISMSService;
import org.springframework.stereotype.Service;

/**腾讯短信服务
 * @author liujixin
 * @date 2019-02-12
 */

@Service
public class TencentSMSService implements ISMSService {

    @Override
    public void sendRegistCode(String mobile, String regisCode) {

    }
}
