# Tests

## Tipos de Tests

### AnalisisFinalTest
- **Propósito**: Test principal de análisis de seguridad
- **Funcionalidad**: Navega por la aplicación y genera reporte ZAP
- **Ejecución**: `mvn test -Dtest=AnalisisFinalTest`

### SecurityAnalysisTest
- **Propósito**: Test con anotaciones Allure detalladas
- **Funcionalidad**: Análisis completo con reportes avanzados
- **Ejecución**: `mvn test -Dtest=SecurityAnalysisTest`

### SimpleAllureTest
- **Propósito**: Test básico para verificar Allure
- **Funcionalidad**: Validación de configuración de reportes
- **Ejecución**: `mvn test -Dtest=SimpleAllureTest`

### CucumberSecurityTest
- **Propósito**: Test BDD con Cucumber
- **Funcionalidad**: Escenarios de seguridad en formato Gherkin
- **Ejecución**: `mvn test -Dtest=CucumberSecurityTest`

## Ejecución de Tests

### Todos los tests
```bash
mvn clean test
```

### Test específico
```bash
mvn test -Dtest=NombreDelTest
```

### Con reportes Allure
```bash
mvn clean test allure:report
mvn allure:serve
```

## Estructura de Tests
- **Given**: Configuración inicial (ZAP, navegador)
- **When**: Acciones de navegación
- **Then**: Validaciones y generación de reportes

## Reportes Generados
- **ZAP Report**: `scan-results/SeleniumTest.html`
- **Allure Report**: `target/site/allure-maven-plugin/`
- **Surefire Reports**: `target/surefire-reports/`