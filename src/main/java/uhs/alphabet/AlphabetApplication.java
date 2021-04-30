package uhs.alphabet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// todo: db 안쓴다고 exclude 해야 build에서 에러가 없음, 애초에 db 사용 안하는데 왜 에러인지 의문
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaAuditing
@SpringBootApplication
public class AlphabetApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphabetApplication.class, args);
    }

}
