package kisonar.poc.services.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockApp {

      public static void main(String[] args) {
            WireMockServer wireMockServer = new WireMockServer(options().port(8089)); //No-args constructor will start on port 8080, no HTTPS
            wireMockServer.start();
      }
}