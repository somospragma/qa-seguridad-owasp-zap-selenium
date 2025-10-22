package org.demo.seleniumzappom.interfaces;

import org.zaproxy.clientapi.core.ClientApiException;
import java.io.IOException;

public interface ZapInterface {
    void initializeZap() throws ClientApiException;
    void generateReport() throws ClientApiException, IOException, InterruptedException;
    void cleanup();
}