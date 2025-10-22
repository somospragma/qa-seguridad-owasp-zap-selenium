package org.demo.selenium_zap_pom;

import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ZapManager implements ZapInterface {
    private ClientApi api;
    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8080;
    private static final String ZAP_API_KEY = "5abdrhung2c4d7o921nt6631l8";
    private static final String SCAN_URL = "https://www.demoblaze.com/";

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
        String reportHtml = api.core.htmlreport("").toString();
        byte[] report = reportHtml.getBytes();
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
    }

    @Override
    public void cleanup() {
        // Implementar limpieza si es necesario
    }

    public ClientApi getApi() {
        return api;
    }
}