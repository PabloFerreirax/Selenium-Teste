package com.aula.selenium.teste;

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
