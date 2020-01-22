package com.project.seenit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("cloud")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }
}

