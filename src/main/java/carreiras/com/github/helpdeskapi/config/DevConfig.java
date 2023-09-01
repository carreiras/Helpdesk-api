package carreiras.com.github.helpdeskapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import carreiras.com.github.helpdeskapi.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instanciaDB() {
        dbService.instanciaDB();
    }

}
