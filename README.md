# Selenium ZAP Security Testing Framework

## Descripción
Framework de automatización de pruebas de seguridad que combina Selenium WebDriver con OWASP ZAP para realizar análisis de vulnerabilidades en aplicaciones web.

## Características
- **Automatización Web**: Selenium WebDriver con patrón Page Object Model (POM)
- **Análisis de Seguridad**: Integración con OWASP ZAP para detección de vulnerabilidades
- **Reportes Avanzados**: Allure Reports con métricas detalladas
- **Arquitectura Escalable**: Interfaces y Factory Pattern para fácil mantenimiento

## Estructura del Proyecto
```
src/test/java/org/demo/seleniumzappom/
├── interfaces/              # Interfaces y gestores
│   ├── MenuInterface.java   # Interfaz para menús
│   ├── PageInterface.java   # Interfaz para páginas
│   ├── ZapInterface.java    # Interfaz para ZAP
│   ├── ZapManager.java      # Gestión de OWASP ZAP
│   └── PageFactory.java     # Factory para páginas
├── pages/                   # Page Objects
│   ├── BasePage.java        # Clase base
│   ├── LaptopsPage.java     # Página de laptops
│   ├── MonitorsPage.java    # Página de monitores
│   └── PhonesPage.java      # Página de teléfonos
├── test/                    # Tests
│   ├── AnalisisFinalTest.java      # Test principal
│   ├── SecurityAnalysisTest.java   # Test con Allure
│   ├── SimpleAllureTest.java       # Test básico
│   └── CucumberSecurityTest.java   # Test con Cucumber
├── utils/                   # Utilidades
│   ├── .env                 # Variables de entorno
│   └── EnvConfig.java       # Configuración de entorno
├── runner/                  # Runners
│   └── CucumberTestRunner.java     # Runner de Cucumber
└── steps/                   # Steps de Cucumber
    └── SecuritySteps.java   # Steps de seguridad
```

## Requisitos Previos
- Java 17+
- Maven 3.6+
- Chrome Browser
- OWASP ZAP (opcional, para análisis de seguridad)

## Configuración de OWASP ZAP
1. Descargar e instalar [OWASP ZAP](https://www.zaproxy.org/download/)
2. Iniciar ZAP en modo daemon:
   ```bash
   zap.sh -daemon -host localhost -port 8080 -config api.key=YOUR_API_KEY
   ```

## Configuración de Variables de Entorno
El proyecto utiliza un archivo `.env` para la configuración:

**Ubicación**: `src/test/java/org/demo/seleniumzappom/utils/.env`

```properties
ZAP_PROXYHOST=localhost
ZAP_PROXYPORT=8080
ZAP_API_KEY=your_zap_api_key_here
SCAN_URL=https://www.demoblaze.com/
```

**Nota**: Modifica el archivo `.env` con tu API key de ZAP antes de ejecutar las pruebas.

## Ejecución de Tests

### Ejecutar todos los tests
```bash
bash mvnw clean test
```

### Ejecutar test específico
```bash
bash mvnw test -Dtest=AnalisisFinalTest
bash mvnw test -Dtest=SecurityAnalysisTest
bash mvnw test -Dtest=SimpleAllureTest
```

### Generar reportes Allure
```bash
# Generar reporte
bash mvnw allure:report

# Abrir reporte en navegador
bash mvnw allure:serve
```

## Reportes Generados

### Reportes de Seguridad (ZAP)
- **Ubicación**: `scan-results/SeleniumTest.html`
- **Contenido**: Vulnerabilidades encontradas, análisis de riesgo

### Reportes de Ejecución (Allure)
- **Ubicación**: `target/site/allure-maven-plugin/index.html`
- **Contenido**: Métricas de ejecución, pasos detallados, gráficos

## Tecnologías Utilizadas
- **Selenium WebDriver 4.15.0**: Automatización web
- **OWASP ZAP Client API**: Análisis de seguridad
- **JUnit 4**: Framework de testing
- **Allure 2.24.0**: Reportes avanzados
- **WebDriverManager**: Gestión automática de drivers
- **Maven**: Gestión de dependencias y build

## Configuración del Proyecto

### Aplicación Objetivo
- **URL**: https://www.demoblaze.com/
- **Funcionalidades**: Navegación por menús (Laptops, Phones, Monitors)

### Proxy ZAP
- **Host**: localhost
- **Puerto**: 8080
- **API Key**: Configurada en archivo `.env`

## Flujo de Ejecución
1. **Inicialización**: Configura ZAP proxy y ChromeDriver
2. **Navegación**: Automatiza interacciones en la aplicación web
3. **Captura**: ZAP registra todo el tráfico HTTP/HTTPS
4. **Análisis**: Ejecuta spider scan y active scan
5. **Reportes**: Genera reportes de seguridad y ejecución

## Mantenimiento
- **Agregar nuevas páginas**: Crear clase en `pages/` que implemente `PageInterface`
- **Nuevos menús**: Implementar `MenuInterface` y agregar al `PageFactory`
- **Nuevos tests**: Usar anotaciones Allure para reportes detallados

## Troubleshooting

### ZAP no disponible
Los tests funcionan sin ZAP, pero no generan análisis de seguridad.

### Error de proxy
Verificar que ZAP esté ejecutándose en localhost:8080.

### Tests no se ejecutan
Verificar que no haya conflictos de versiones JUnit en el classpath.