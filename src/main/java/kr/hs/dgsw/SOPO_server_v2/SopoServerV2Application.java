package kr.hs.dgsw.SOPO_server_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SopoServerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(SopoServerV2Application.class, args);
	}

}
