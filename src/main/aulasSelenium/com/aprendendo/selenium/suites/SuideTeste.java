package com.aprendendo.selenium.suites;

import com.aprendendo.selenium.teste.TesteCadastro;
import com.aprendendo.selenium.teste.TesteCampoTreinamento;
import com.aprendendo.selenium.teste.TesteRegrasCadastros;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastros.class,
        TesteCampoTreinamento.class
})
public class SuideTeste {

// Esta classe nada faz a não ser fazer rodas todos os testes da suite descritos acima na ordem que estão

}
