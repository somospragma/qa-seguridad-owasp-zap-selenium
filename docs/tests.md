# Pruebas y Reportes

Esta guía describe cómo ejecutar las pruebas de seguridad y generar los reportes asociados (Allure y OWASP ZAP).

## Requisitos

- Java 17 (JDK)
- Maven (se recomienda usar el wrapper `mvnw`)
- OWASP ZAP en ejecución local (por defecto `localhost:8080`) con API Key configurada

## Configuración de ZAP

Edite el archivo `.env` en `src/test/java/org/demo/seleniumzappom/utils/.env`:

```
ZAP_PROXYHOST=localhost
ZAP_PROXYPORT=8080
ZAP_API_KEY=<tu_api_key>
SCAN_URL=https://www.demoblaze.com/
```

## Ejecutar pruebas

Ejecuta todos los tests (incluye la generación de reportes ZAP cuando ZAP está disponible):

```bash
./mvnw test
```

- Reporte ZAP HTML: `scan-results/SeleniumTest.html`
- Reporte ZAP JSON: `scan-results/SeleniumTest.json`

## Generar reporte Allure

Reporte estático (HTML):

```bash
./mvnw allure:report
open target/site/allure-maven-plugin/index.html
```

Servidor interactivo:

```bash
./mvnw allure:serve
```

## Notas

- Si ZAP no está corriendo o no es accesible, las pruebas continúan sin análisis de seguridad.
- El proyecto está configurado para compilar con Java 17 (`pom.xml`). Asegúrate de que `JAVA_HOME` apunte a un JDK 17.
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