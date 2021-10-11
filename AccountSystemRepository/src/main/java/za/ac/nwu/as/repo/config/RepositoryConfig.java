package za.ac.nwu.as.repo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("za.ac.nwu.as.repo.persistence")
@EntityScan("za.ac.nwu.as.domain.persistence")
@PropertySource(value = "classpath:application-db.properties")
public class RepositoryConfig {

}
