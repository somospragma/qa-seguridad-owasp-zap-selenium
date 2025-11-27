package org.demo.seleniumzappom.utils;

import org.demo.seleniumzappom.interfaces.ZapInterface;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.nio.charset.StandardCharsets;

public class ZapManager implements ZapInterface {
    private ClientApi api;
    private static final String ZAP_PROXYHOST = EnvConfig.getZapProxyHost();
    private static final int ZAP_PROXYPORT = EnvConfig.getZapProxyPort();
    private static final String ZAP_API_KEY = EnvConfig.getZapApiKey();
    private static final String SCAN_URL = EnvConfig.getScanUrl();

    @Override
    public void initializeZap() throws ClientApiException {
        api = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_API_KEY);
        
        try {
            // Verificar conexión con ZAP
            api.core.version();
            
            // Limpiar sesiones anteriores
            api.ascan.removeAllScans();
            api.core.newSession("", "");
            
            // Habilitar escáneres pasivos
            api.pscan.enableAllScanners();
            
            System.out.println("ZAP inicializado correctamente");
        } catch (ClientApiException e) {
            System.err.println("Error conectando con ZAP: " + e.getMessage());
            System.err.println("Asegúrate de que ZAP esté ejecutándose en " + ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
            throw e;
        }
    }

    @Override
    public void generateReport() throws ClientApiException, IOException, InterruptedException {
        System.out.println("Iniciando spider scan...");
        api.spider.scan(SCAN_URL, null, null, null, null);
        
        // Esperar a que termine el spider
        while (Integer.parseInt(api.spider.status("").toString()) < 100) {
            Thread.sleep(1000);
        }
        
        System.out.println("Iniciando active scan...");
        api.ascan.scan(SCAN_URL, "true", "false", null, null, null);
        
        // Esperar a que termine el active scan
        while (Integer.parseInt(api.ascan.status("").toString()) < 100) {
            Thread.sleep(5000);
        }
        
        System.out.println("Generando reporte...");
        // Intento 1: usar API no deprecada (other)
        byte[] report;
        try {
            report = api.callApiOther("core", "htmlreport", ZAP_API_KEY, Collections.<String, String>emptyMap());
            // Si ZAP devuelve un error JSON (p.ej. {"code":"bad_type"...}), hacer fallback
            String maybeJson = new String(report, StandardCharsets.UTF_8);
            if (maybeJson.startsWith("{") && maybeJson.contains("\"code\"")) {
                // Fallback a método deprecado pero estable que retorna bytes directamente
                report = getHtmlReportLegacy();
            }
        } catch (Exception e) {
            // Fallback seguro
            report = getHtmlReportLegacy();
        }
        String folderReport = "scan-results";
        File carpeta = new File(folderReport);
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada exitosamente");
            } else {
                System.out.println("No se pudo crear la carpeta");
            }
        }
        Path filePath = Paths.get(System.getProperty("user.dir") + "/scan-results/SeleniumTest.html");
        Files.deleteIfExists(filePath);
        Files.write(filePath, report);
        System.out.println("Reporte generado: " + filePath.toString());
        
            // Generar adicionalmente el reporte en JSON
            try {
                byte[] jsonReport;
                try {
                    jsonReport = api.callApiOther("core", "jsonreport", ZAP_API_KEY, Collections.<String, String>emptyMap());
                    String body = new String(jsonReport, StandardCharsets.UTF_8);
                    // Si el body indica error, intentar fallback legacy
                    if (body.startsWith("{") && body.contains("\"code\"")) {
                        jsonReport = getJsonReportLegacy();
                    }
                } catch (Exception ex) {
                    jsonReport = getJsonReportLegacy();
                }
                Path jsonPath = Paths.get(System.getProperty("user.dir") + "/scan-results/SeleniumTest.json");
                Files.deleteIfExists(jsonPath);
                Files.write(jsonPath, jsonReport);
                System.out.println("Reporte JSON generado: " + jsonPath.toString());
            } catch (Exception ex) {
                System.err.println("No fue posible generar el reporte JSON de ZAP: " + ex.getMessage());
            }
    }

    @SuppressWarnings("deprecation")
    private byte[] getHtmlReportLegacy() throws ClientApiException {
        // La API legacy retorna directamente el HTML como bytes
        return api.core.htmlreport("");
    }
    
        @SuppressWarnings("deprecation")
        private byte[] getJsonReportLegacy() throws ClientApiException {
            return api.core.jsonreport();
        }

    @Override
    public void cleanup() {
        // Implementar limpieza si es necesario
    }

    public ClientApi getApi() {
        return api;
    }
}