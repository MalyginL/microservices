package settings;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SettingsServer {
    public static void main(String[] args) {
        SpringApplication.run(SettingsServer.class, args);
    }


}
