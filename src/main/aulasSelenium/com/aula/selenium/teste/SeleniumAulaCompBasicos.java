package com.aula.selenium.teste;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

public class SeleniumAulaCompBasicos {

    private WebDriver driver; // instancia um driver

    /*Existe boa pratica que se refere a não precisar usar o mesmo codigo sempre,
    colocando em um metodo e assim o chamando podemos diminiur a leitura do codigo e tornar mais facil
    e neste projeto temos o uso da anotação Before. que faz executar antes de tudo este metodo*/
    @Before
    public void inicializaTest(){
        System.setProperty("webdriver.chrome.driver", //diz o tipo
                "C:\\Users\\pablo\\OneDrive\\Documentos\\TrabalhoHomeOffice\\Drivers\\chromedriver.exe"); //diz onde esta
        driver = new ChromeDriver(); // instancia o tipo agora
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); // abre a pagina
    }

    // E como before(antes) existe o after(depois)... after faz a excução do metodo apos o test

    @After
    public void finalizaTest(){
        driver.quit();
    }

    @Test
    public void testTextField(){

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste campo"); //busca e escreve algo em uma caixa de texto
        //busca e compara algo de uma caixa de texto
        Assert.assertEquals("Teste campo", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void testTextArea(){
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste area");
        Assert.assertEquals("Teste area", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void testRadioButun(){
        driver.findElement(By.id("elementosForm:sexo:0")).click();//clica na opção sexo 0
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected()); //retorna um verdadeiro ou falso
    }

    @Test
    public void testCheckBox(){
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click(); // seleciona o elemento pizza 2 no caso
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
    }

    @Test
    public void testInterageCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade")); //clica no combo
        Select combo = new Select(element); // instancia o combo

        /*
        forma 1
        combo.selectByIndex(3); // escolhe o index a clicar

        froma 2
        combo.selectByValue("superior"); // escolhe por valor

        forma 3
        combo.selectByVisibleText("Superior"); //Exatamente o valor descrito no combo
         */

        combo.selectByVisibleText("Superior");
        // pega o texto da primeira op selecionada e compara se é igual a Superior
        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void testVerificaCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade")); //clica no combo
        Select combo = new Select(element); // instancia o combo
        // isto é pegar uma lista do combo e faz alguma logica com ela
        List<WebElement> list = combo.getOptions();

        //logica abaixo...

        Assert.assertEquals(8, list.size());
    }

    @Test
    public void testVerificaComboMultiplo() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> list = combo.getAllSelectedOptions();

        Assert.assertEquals(3, list.size());

        //Deselect
        combo.deselectByVisibleText("Karate");
        list = combo.getAllSelectedOptions();
        Assert.assertEquals(2, list.size());
        }

        @Test
        public void deveInteragirComBtn() {
            WebElement btn = driver.findElement(By.id("buttonSimple"));
            btn.click();

            Assert.assertEquals("Obrigado!", btn.getAttribute("value"));
        }

    @Test
    //@Ignore //usado para ignorar o test
    public void deveInteragirComLinks() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
    }

    @Test
    public void deveBuscarTextoNaPagina() {
        Assert.assertEquals("Campo de Treinamento",
                driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());
    }
}
