package com.github.wangchenning.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;

public class MockServer {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");


    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "utf-8").toArray(), "\n");

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
