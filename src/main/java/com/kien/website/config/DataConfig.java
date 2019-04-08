package com.kien.website.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    private static Logger logger = LoggerFactory.getLogger(DataConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder embeddedDatabaseBuilder =
                    new EmbeddedDatabaseBuilder();
            embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);
            return embeddedDatabaseBuilder.build();
        } catch (Exception e) {
            logger.error("Embedded Datasouce bean cannot be created!");
            return null;
        }
    }

}
