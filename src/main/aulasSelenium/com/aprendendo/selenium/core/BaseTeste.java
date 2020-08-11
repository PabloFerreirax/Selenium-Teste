package com.aprendendo.selenium.core;

import org.junit.After;

import static com.aprendendo.selenium.core.DriverFactory.killDriver;

public class BaseTeste {

    @After
    public void finaliza(){
        if(Propriedades.FECHAR_BROWSER){
            killDriver();
        }
    }

}
