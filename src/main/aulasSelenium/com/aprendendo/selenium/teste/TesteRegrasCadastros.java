package com.aprendendo.selenium.teste;

import com.aprendendo.selenium.core.BaseTeste;
import com.aprendendo.selenium.core.DSL;
import com.aprendendo.selenium.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.aprendendo.selenium.core.DriverFactory.getDriver;
import static com.aprendendo.selenium.core.DriverFactory.killDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastros extends BaseTeste {

    private DSL dsl;
    private CampoTreinamentoPage page;

    //necessario para ser passado como parametro em getCollection e para serem usados nas regras da deveValidarCadastro()
    @Parameterized.Parameter(value = 0)
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter(value = 3)
    public List<String> comida;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String mensagem;

    public TesteRegrasCadastros() {
    }

    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        //driver.manage().window().setSize(new Dimension(1200, 765));
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }


    @Parameterized.Parameters // esta tag faz com que seja captador dos parametros acima
    // este metodo, faz todos os testes com base e regra no deveValidarCadastro() pois todos os parametros dados estão nesta classe
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
                //Cada um dos elementos abaixo, alimenta um teste de deveValidarCadastro()
            {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
            {"Pablo", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
            {"Pablo", "Ferreira", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
            {"Pablo", "Ferreira", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
            {"Pablo", "Ferreira", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"},
        });
    }

    @Test
    public void deveValidarCadastro(){
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if(sexo.equals("Masculino")){
            page.setSexoMasc();
        }
        else if(sexo.equals("Feminino")){
            page.setSexoFem();
        }
        if(comida.contains("Carne")){
            page.setComidaCarne();
        }
        if(comida.contains("Frango")){
            page.setComidaFrango();
        }
        if(comida.contains("Pizza")){
            page.setComidaPizza();
        }
        if(comida.contains("Vegetariano")){
            page.setComidaVegetal();
        }
        page.setEsporte(esportes);

        page.cadastrar();
        Assert.assertEquals(mensagem, dsl.alertaObterTextoEAceita());
    }
}
