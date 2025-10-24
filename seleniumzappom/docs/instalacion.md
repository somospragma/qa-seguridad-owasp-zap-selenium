# Instalación

## Requisitos Previos
- Java 17 o superior
- Maven 3.6 o superior
- Chrome Browser
- Git

## Instalación de OWASP ZAP
1. Descargar desde [OWASP ZAP](https://www.zaproxy.org/download/)
2. Instalar siguiendo las instrucciones del sistema operativo
3. Configurar API key en ZAP

## Configuración del Proyecto
1. Clonar el repositorio:
```bash
git clone https://github.com/somospragma/qa-seguridad-owasp-zap-selenium.git
```

2. Navegar al directorio del proyecto:
```bash
cd qa-seguridad-owasp-zap-selenium
```

3. Configurar variables de entorno en `.env`:
```properties
ZAP_PROXYHOST=localhost
ZAP_PROXYPORT=8080
ZAP_API_KEY=your_api_key_here
SCAN_URL=https://www.demoblaze.com/
```

4. Instalar dependencias:
```bash
mvn clean install
```

## Verificación de Instalación
```bash
mvn test -Dtest=SimpleAllureTest
```