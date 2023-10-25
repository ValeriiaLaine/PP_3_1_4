package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleanUpListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        jdbcTemplate.execute("DROP DATABASE flyway_demo_db");
        jdbcTemplate.execute("CREATE DATABASE flyway_demo_db");
    }

}

