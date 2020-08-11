package com.aprendendo.selenium.teste;

import com.aprendendo.selenium.core.BaseTeste;
import com.aprendendo.selenium.core.DSL;
import com.aprendendo.selenium.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax extends BaseTeste {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", //diz o tipo
				"C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe"); //diz onde esta
		driver = new ChromeDriver(); // instancia o tipo agora
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}

	/* Este teste ajax faz uso do primaryFaces que muda muita coisa e as complica para testes.
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98"))); este campo pega o id do e espera com que
	ele apareça.
	 */
	@Test
	public void testAjax(){
		dsl.escrever("j_idt85:name", "Teste");
		dsl.clicarBotao("j_idt85:j_idt88");
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt85:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt85:display"));
	}
}
