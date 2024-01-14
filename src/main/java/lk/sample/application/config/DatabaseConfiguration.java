package lk.sample.application.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan("lk.sample.*")
@EnableJpaRepositories(basePackages = "lk.sample.external.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Primary
    @Bean(name = "sample-datasource")
    public DataSource ccbsDataSource(@Autowired Environment env) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("sample.jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("sample.jdbc.url"));
        dataSource.setUsername(env.getProperty("sample.jdbc.user"));
        dataSource.setPassword(env.getProperty("sample.jdbc.pass"));
        dataSource.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("sample.jdbc.connectionPool"))));
        dataSource.setMaxLifetime(Long.parseLong(Objects.requireNonNull(env.getProperty("sample.jdbc.maxLifetime"))));
        dataSource.setConnectionTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("sample.jdbc.connectionTimeout"))));
        dataSource.setMinimumIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("sample.jdbc.minIdle"))));
        dataSource.setIdleTimeout(Integer.parseInt(Objects.requireNonNull(env.getProperty("sample.jdbc.minIdle.timeout"))));
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
