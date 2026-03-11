package acal.com.acal_left.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.locations:classpath:db/migration}")
    private String locations;

    @Value("${spring.flyway.baseline-on-migrate:true}")
    private boolean baselineOnMigrate;

    @Value("${spring.flyway.baseline-version:0}")
    private String baselineVersion;

    @Value("${spring.flyway.validate-on-migrate:true}")
    private boolean validateOnMigrate;

    @Value("${spring.flyway.out-of-order:false}")
    private boolean outOfOrder;

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .baselineOnMigrate(baselineOnMigrate)
                .baselineVersion(baselineVersion)
                .validateOnMigrate(validateOnMigrate)
                .cleanDisabled(true)
                .outOfOrder(outOfOrder)
                .load();
    }

    /**
     * Garante que entityManagerFactory só seja criado após o bean "flyway" rodar.
     */
    @Bean
    public static BeanFactoryPostProcessor flywayDependencyPostProcessor() {
        return beanFactory -> {
            for (String beanName : List.of("entityManagerFactory", "jpaContext")) {
                if (beanFactory.containsBeanDefinition(beanName)) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    String[] existing = bd.getDependsOn();
                    String[] updated;
                    if (existing == null || existing.length == 0) {
                        updated = new String[]{"flyway"};
                    } else {
                        updated = new String[existing.length + 1];
                        System.arraycopy(existing, 0, updated, 0, existing.length);
                        updated[existing.length] = "flyway";
                    }
                    bd.setDependsOn(updated);
                }
            }
        };
    }
}


