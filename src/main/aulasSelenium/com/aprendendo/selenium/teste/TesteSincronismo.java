package com.aprendendo.selenium.teste;

import java.util.concurrent.TimeUnit;

import com.aprendendo.selenium.core.BaseTeste;
import com.aprendendo.selenium.core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSincronismo extends BaseTeste {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", //diz o tipo
				"C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe"); //diz onde esta
		driver = new ChromeDriver(); // instancia o tipo agora
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.manage().window().setSize(new Dimension(1200, 765));
		dsl = new DSL();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000); // espera em milesegundos... não recomendado
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException{
		//Esta é a melhor espera visão uma visão geral... porem muito lenta pois atrasa todas etapoas em 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	

	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		// Esta por outro lado é realmente a melhor opção pois permite escolher exatamente o que quer esperar...
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
	}
}
