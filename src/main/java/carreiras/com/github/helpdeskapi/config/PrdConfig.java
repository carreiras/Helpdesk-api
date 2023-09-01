package carreiras.com.github.helpdeskapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import carreiras.com.github.helpdeskapi.service.DBService;

@Configuration
@Profile("prd")
public class PrdConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Bean
    public void instanciaDB() {
        if (ddlAuto.equals("create"))
            dbService.instanciaDB();
    }

}
