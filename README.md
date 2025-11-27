# Proyecto base de Pragma

Este repositorio cuenta con la siguiente estructura base para su documentación:

- catalog-info.yaml
- docs
  - index.md
  - topicos.md
  - tecnologias.md
  - consideraciones.md
  - instalacion.md
  - descarga.md
  - tests.md
- mkdocs.yaml

## Estructura

### catalog-info.yaml

Este archivo es necesario pensando en una plataforma de ingeniería (Backstage), con la intención de centralizar y visualizar todos los repositorios de una manera diferente a lo que puede proveer Github. Dentro del mismo documento se encontrarán enlaces a la documentación oficial que indicará el significado de cada uno de los parámetros solicitados (recuerda actualizar su contenido).

### /docs

Dentro de la carpeta docs se encontrarán las diferentes partes de un readme con un pequeño ejemplo dentro de cada una de ellas (recuerda actualizar su contenido).

### mkdocs.yaml

Este documento contiene la ruta de navegación estructurada de los diferentes archivos que están dentro de la carpeta docs. Esto para una mejor categorización y vizualización de la documentación dentro de la plataforma de ingeniería.

**[Nota]**

Este README es utilizado para dar claridad sobre la documentación de este repositorio y la estructuración de la misma, toda la documentación del repositorio se encuentra en la carpeta /docs.

## Resumen del proyecto

Framework de automatización de pruebas de seguridad con Selenium WebDriver y OWASP ZAP, con reporting en Allure. El código de pruebas vive en `src/test/java`, los reportes se generan en `target/allure-results` (Allure) y `scan-results` (ZAP).

## Requisitos

- Java 17 (JDK)
- Maven (se recomienda usar `mvnw` incluido)
- OWASP ZAP en ejecución local (`localhost:8080`) con API Key configurada

## Configuración

Variables de entorno de ZAP configuradas en `src/test/java/org/demo/seleniumzappom/utils/.env`:

- `ZAP_PROXYHOST=localhost`
- `ZAP_PROXYPORT=8080`
- `ZAP_API_KEY=...`
- `SCAN_URL=https://www.demoblaze.com/`

## Ejecución de pruebas y generación de reportes

1. Ejecutar pruebas (incluye generación de reportes ZAP):

  ```bash
  ./mvnw test
  ```

  - Reporte ZAP HTML: `scan-results/SeleniumTest.html`
  - Reporte ZAP JSON: `scan-results/SeleniumTest.json`
  - Capturas por paso: adjuntadas automáticamente al informe de Allure

2. Generar reporte Allure (estático):

  ```bash
  ./mvnw allure:report
  open target/site/allure-maven-plugin/index.html
  ```

3. Servir Allure (interactivo):

  ```bash
  ./mvnw allure:serve
  ```

## Nota sobre compatibilidad

El proyecto está configurado para compilar con Java 17 (ver `pom.xml`). Si tu entorno usa otra versión de Java, ajusta `JAVA_HOME` o actualiza los parámetros de compilación en Maven.

## Atajos en VS Code (opcional)

- Tarea para generar reporte Allure: aparece como "Generar reporte Allure" en el menú de tareas.
- Tarea para abrir el reporte Allure: aparece como "Abrir reporte Allure".
- Tarea end-to-end: "E2E: Tests + Allure" ejecuta pruebas, genera el reporte y lo abre automáticamente.

Las tareas están definidas en `.vscode/tasks.json` y no afectan la funcionalidad del proyecto.