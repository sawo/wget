package com.github.axet.wget;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Properties;

public class ProxyManager {

    public static HttpURLConnection openConnection(URL url) throws IOException {
        Proxy proxy = createProxy();
        return proxy != null ? (HttpURLConnection) url.openConnection(proxy) : (HttpURLConnection) url.openConnection();
    }

    private static Proxy createProxy() {
        Proxy proxy = null;
        Properties properties = System.getProperties();
        String proxyHost = properties.getProperty("http.proxyHost");
        String proxyPort = properties.getProperty("http.proxyPort");
        boolean proxyAvailable = (proxyHost != null) && (proxyHost.length() > 0) && (proxyPort != null) && (proxyPort.length() > 0);
        if (proxyAvailable) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
        }
        return proxy;
    }
}
