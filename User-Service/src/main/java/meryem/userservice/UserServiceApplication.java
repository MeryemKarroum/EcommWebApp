package meryem.userservice;


import meryem.userservice.entities.Role;
import meryem.userservice.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
public class UserServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application started");
		Role role = accountService.findRoleByRoleName("CLIENT");
		if (role == null) {
			logger.info("Role CLIENT not found, creating...");
			role = new Role("CLIENT");
			accountService.saveRole(role);
			logger.info("Role CLIENT created");
		} else {
			logger.info("Role CLIENT found");
		}
	}
}