# Selenium ZAP Security Testing Framework

## Descripción
Framework de automatización de pruebas de seguridad que combina Selenium WebDriver con OWASP ZAP para realizar análisis de vulnerabilidades en aplicaciones web.

## Características
- **Automatización Web**: Selenium WebDriver con patrón Page Object Model (POM)
- **Análisis de Seguridad**: Integración con OWASP ZAP para detección de vulnerabilidades
- **Reportes Avanzados**: Allure Reports con métricas detalladas
- **Arquitectura Escalable**: Interfaces y Factory Pattern para fácil mantenimiento

## Aplicación Objetivo
- **URL**: https://www.demoblaze.com/
- **Funcionalidades**: Navegación por menús (Laptops, Phones, Monitors)

## Flujo de Ejecución
1. **Inicialización**: Configura ZAP proxy y ChromeDriver
2. **Navegación**: Automatiza interacciones en la aplicación web
3. **Captura**: ZAP registra todo el tráfico HTTP/HTTPS
4. **Análisis**: Ejecuta spider scan y active scan
5. **Reportes**: Genera reportes de seguridad y ejecución