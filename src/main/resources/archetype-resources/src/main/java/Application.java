/**
 * 运行：
 * mvn spring-boot:run
 * <p>
 * 打包：
 * mvn clean package -Dmaven.test.skip=true
 */
package ${groupId};

import ${groupId}.base.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class
)
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(${groupId}.Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(${groupId}.Application.class, args);
    }
}