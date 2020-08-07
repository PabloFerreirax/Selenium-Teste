package com.aula.selenium.teste;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL { // esta class armazena todos os metodos que facilita e organiza mantem mais pratico para os testes

    private WebDriver driver;

    public DSL(WebDriver driver){
        this.driver = driver;
    }

    //************ TextField & TextArea **************//

    public void escrever(By by, String texto){
        driver.findElement(by).sendKeys();
    }

    public void escrever(String idText, String texto){
        driver.findElement(By.id(idText)).sendKeys(texto);
    }

    //************ Radio & Check **************//

    public void clicaRadio(String id){
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String idRadio){
        return driver.findElement(By.id(idRadio)).isSelected();
    }

    public void clicaCheck(String idCheck){
        driver.findElement(By.id(idCheck)).click();
    }

    public boolean isCheckMarcado(String idCheck){
        return driver.findElement(By.id(idCheck)).isSelected();
    }

    //********** Combo **************//

    public void selecionaComboBox(String idCombo, String textoVisivel){
        WebElement element = driver.findElement(By.id(idCombo));
        Select combo = new Select(element); // instancia o combo
        /*
        forma 1
        combo.selectByIndex(3); // escolhe o index a clicar
        froma 2
        combo.selectByValue("superior"); // escolhe por valor
        forma 3
        combo.selectByVisibleText("Superior"); //Exatamente o valor descrito no combo
         */
        combo.selectByVisibleText(textoVisivel);
    }

    public void descelecionaComboBox(String idCombo, String textoVisivel){
        WebElement element = driver.findElement(By.id(idCombo));
        Select combo = new Select(element);
        combo.deselectByVisibleText(textoVisivel);
    }

    public String retornaValComboBox(String idCombo){
        WebElement element = driver.findElement(By.id(idCombo)); //clica no combo
        Select combo = new Select(element); // instancia o combo
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> retornaValsComboBox(String idCombo, String textoVisivel){
        WebElement element = driver.findElement(By.id(idCombo));
        Select combo = new Select(element);
        List<WebElement> todasOpEscolhidas = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<>();
        for(WebElement e  : todasOpEscolhidas){
            valores.add(e.getText());
        }
        return valores;
    }

    public int obterSizeEscolhidos(String idCombo){
        WebElement element = driver.findElement(By.id(idCombo));
        Select combo = new Select(element);
        List<WebElement> sizeEscolhidos = combo.getOptions();
        return sizeEscolhidos.size();
    }

    public boolean verificaValCombo(String idCombo, String opPresente){
        WebElement element = driver.findElement(By.id(idCombo));
        Select combo = new Select(element);
        List<WebElement> verificados = combo.getOptions();
        for(WebElement op : verificados){
            if(op.getText().equals(opPresente)){
                return true;
            }
        }
        return false;
    }

    //********** Botao **************//

    public void clickBtn(String idBotao){
        driver.findElement(By.id(idBotao)).click();
    }

    public boolean checkaBotaoClick(String idBotao){
        return driver.findElement(By.id(idBotao)).isSelected();
    }

    public String obterValorBotao(String idBotao){
        return driver.findElement(By.id(idBotao)).getAttribute("Value");
    }

    //********** Link **************//

    public void clickLink(String idLink){
        driver.findElement(By.id(idLink)).click();
    }


    public String obterTextoBy(By by){
        return driver.findElement(by).getText();
    }

    public String obterTextoId(String idText){
        return obterTextoBy(By.id(idText));
    }

    //********** Alertas **************//

    public String alertObterTexto(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String alertObterTextoAceita(){
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        return textAlert;
    }

    public String alertObterTextoNega(){
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.dismiss();
        return textAlert;
    }

    public void alertEscrever(String texto){
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(texto);
        alert.accept();
    }

    //********** Frames & Janelas **************//

    public void entraFrame(String id){
        driver.switchTo().frame(id);
    }

    public void sairFrame(){
        driver.switchTo().defaultContent();
    }

    public void trocaJanela(String id) {
        driver.switchTo().window(id);
    }

}

