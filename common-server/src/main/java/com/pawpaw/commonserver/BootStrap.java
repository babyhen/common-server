package com.pawpaw.commonserver;

import com.pawpaw.framework.core.PawpawApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BootStrap {

    public static void main(String[] args) {
        new PawpawApplication().run(BootStrap.class, args);
    }
}
