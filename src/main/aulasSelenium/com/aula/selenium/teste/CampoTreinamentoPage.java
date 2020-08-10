package com.aula.selenium.teste;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }

    public void setNome(String nome){
        dsl.escrever("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escrever("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasc(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFem(){
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setComidaCarne(){
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }
    public void setComidaFrango(){
        dsl.clicarRadio("elementosForm:comidaFavorita:1");
    }
    public void setComidaPizza(){
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }
    public void setComidaVegetal(){
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String escolaridade){
        dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
    }

    public void setEsporte(String... esportes){
        for(String escolha : esportes) {
            dsl.selecionarCombo("elementosForm:esportes", escolha);
        }
    }

    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }

    public String obterResultadoNome(){
        return dsl.obterTexto("descNome");
    }

    public String obterResultadoSobrenome(){
        return dsl.obterTexto("descSobrenome");
    }

    public String obterResultadoSexo(){
        return dsl.obterTexto("descSexo");
    }

    public String obterResultadoComida(){
        return dsl.obterTexto("descComida");
    }

    public String obterResultadoEscolaridade(){
        return dsl.obterTexto("descEscolaridade");
    }

    public String obterResultadoEsportes(){
        return dsl.obterTexto("descEsportes");
    }


    @Test
    public void deveClicarBotaoTabela(){

    }


}
