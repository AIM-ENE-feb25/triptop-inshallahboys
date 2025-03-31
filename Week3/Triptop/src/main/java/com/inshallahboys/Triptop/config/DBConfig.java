package com.inshallahboys.Triptop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

// Will not be tested because we would be testing a java class that we didn't make ourselves.
@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("user")
                .addScript("classpath:db/Schema.sql")
                .addScript("classpath:db/data.sql")
                .build();
    }
}

