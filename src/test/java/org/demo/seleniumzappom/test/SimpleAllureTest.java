package org.demo.seleniumzappom.test;

import io.qameta.allure.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@Epic("Test de Validación")
@Feature("Validación de Allure")
public class SimpleAllureTest {

    @Test
    @Story("Verificar que Allure funciona")
    @Description("Test simple para verificar que Allure genera reportes correctamente")
    @Severity(SeverityLevel.NORMAL)
    public void testAllureFunciona() {
        paso1();
        paso2();
        paso3();
    }

    @Step("Ejecutar paso 1")
    private void paso1() {
        System.out.println("Ejecutando paso 1");
        assertTrue("Paso 1 debe ser exitoso", true);
    }

    @Step("Ejecutar paso 2")
    private void paso2() {
        System.out.println("Ejecutando paso 2");
        assertTrue("Paso 2 debe ser exitoso", true);
    }

    @Step("Ejecutar paso 3 y validar resultado")
    private void paso3() {
        System.out.println("Ejecutando paso 3");
        assertTrue("Paso 3 debe ser exitoso", true);
    }
}