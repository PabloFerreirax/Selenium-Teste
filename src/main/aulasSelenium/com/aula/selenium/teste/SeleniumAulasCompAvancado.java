package com.aula.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SeleniumAulasCompAvancado {

    private WebDriver driver; // instancia um driver

    @Before
    public void inicializaTest(){
        System.setProperty("webdriver.chrome.driver", //diz o tipo
                "C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe"); //diz onde esta
        driver = new ChromeDriver(); // instancia o tipo agora
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); // abre a pagina
    }

    @After
    public void finalizaTest(){
        driver.quit();
    }

    @Test
    public void testTextField() {
        driver.findElement(By.id("alert")).click(); //clica no botao alerta
        Alert alert = driver.switchTo().alert();    //instancia um nova visao do alerta para dar comando as opção do poup-up
        String alertText = alert.getText(); // armazena valor do poup-up
        Assert.assertEquals("Alert Simples", alertText);
        alert.accept(); //aceita a mensagem no poup-up
        //alert.dismiss(); //poderia ser para negar
        driver.findElement(By.id("elementosForm:nome")).sendKeys(alertText);
    }

    @Test
    public void cadastroPessoa(){
        //TextsField
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Pablo");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Ferreira");

        //CheckBox
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        //ComboBox
        WebElement elementFormacao = driver.findElement(By.id("elementosForm:escolaridade"));
        Select comboFormacao = new Select(elementFormacao);
        comboFormacao.selectByVisibleText("Superior");
        WebElement elementEsporte = driver.findElement(By.id("elementosForm:esportes"));
        Select comboEsporte = new Select(elementEsporte);
        comboEsporte.selectByVisibleText("Karate");
        //comboEsporte.selectByVisibleText("O que eh esporte?");
        List<WebElement> listaEsportePraticavel = comboEsporte.getAllSelectedOptions();

        //TextsArea
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Não tenho");

        //BotaoCadastra
        WebElement botaoCadastra = driver.findElement(By.id("elementosForm:cadastrar"));
        botaoCadastra.click();

        //BotãoConfirma
        WebElement botaoConfirma = driver.findElement(By.id("confirm"));
        botaoConfirma.click();
        Alert alertConfirma = driver.switchTo().alert();
        alertConfirma.accept();
        alertConfirma.accept();
    }

    @Test
    public void testaRegraTextFieldNome() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
    }

    @Test
    public void testaRegraTextFieldSobNome() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Pablo");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
    }

    //

    @Test
    public void frame() {
        //.switchTo() troca foco
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert allert = driver.switchTo().alert();
        String msnAlert = allert.getText();
        allert.accept();
        //trazer para pagina padrao
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msnAlert);
    }

    @Test
    public void janela(){
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup"); //muda o foco da janela
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close(); // fecha janela
        driver.switchTo().window(""); // muda foco novamente
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
    }

    @Test
    public void janelaMal() {
        driver.findElement(By.id("buttonPopUpHard")).click();
        //System.out.println(driver.getWindowHandles()); //pega o id da janelas... mas todos id é gerado aleatorio
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]); //maneira certa de entrar nesta janela sem id
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu");
    }

}
