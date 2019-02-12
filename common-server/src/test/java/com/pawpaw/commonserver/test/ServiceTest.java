package com.pawpaw.commonserver.test;


import com.pawpaw.commonserver.BootStrap;
import com.pawpaw.commonserver.service.ISMSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class ServiceTest {

    @Autowired
    private ISMSService service;

    @Test
    public void sendRegistCode() {
        this.service.sendRegistCode("15710001135", "JiaLiangIsAPig");


    }
}
