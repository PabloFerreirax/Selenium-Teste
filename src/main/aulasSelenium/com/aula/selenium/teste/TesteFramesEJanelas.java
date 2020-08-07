package com.aula.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteFramesEJanelas {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", //diz o tipo
				"C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe"); //diz onde esta
		driver = new ChromeDriver(); // instancia o tipo agora
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.manage().window().setSize(new Dimension(1200, 765));
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveInteragirComFrames(){
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComJanelas(){
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo(){
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}