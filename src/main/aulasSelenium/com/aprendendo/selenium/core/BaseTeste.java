package com.aprendendo.selenium.core;

import org.apache.tools.ant.util.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.aprendendo.selenium.core.DriverFactory.getDriver;
import static com.aprendendo.selenium.core.DriverFactory.killDriver;

public class BaseTeste {

    @Rule
    public TestName testName = new TestName();

    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.getFileUtils().copyFile(arquivo, new File("target"+ File.separator +"Screenshot"
                + File.separator + testName.getMethodName()+".jpg"));
        if(Propriedades.FECHAR_BROWSER){
            killDriver();
        }
    }

}
