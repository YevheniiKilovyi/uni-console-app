package yevhkil.uniconsoleapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "yevhkil.uniconsoleapp.repository")
public class JpaConfig {
}
