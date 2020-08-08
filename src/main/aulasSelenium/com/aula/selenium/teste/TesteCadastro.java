package com.aula.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.manage().window().setSize(new Dimension(1200, 765));
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Pablo");
		page.setSobrenome("Ferreira");
		page.setSexoMasc();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterResultadoNome().endsWith("Pablo"));
		Assert.assertEquals("Sobrenome: Ferreira", page.obterResultadoSobrenome());
		Assert.assertEquals("Sexo: Masculino", page.obterResultadoSexo());
		Assert.assertEquals("Comida: Pizza", page.obterResultadoComida());
		Assert.assertEquals("Escolaridade: mestrado", page.obterResultadoEscolaridade());
		Assert.assertEquals("Esportes: Natacao", page.obterResultadoEsportes());
	}
}
