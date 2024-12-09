package meryem.userservice;

import meryem.userservice.entities.Role;
import meryem.userservice.entities.User;
import meryem.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserServiceApplication {
	@Autowired
	UserService userService;
	public static void main(String[] args) {SpringApplication.run(UserServiceApplication.class, args);}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	void initial_users(){
		userService.addRole(new Role(null, "ADMIN"));
		userService.addRole(new Role(null, "CLIENT"));


		userService.saveUser(new User(null, "admin", "123", true, null));
		userService.saveUser(new User(null, "meryem", "123", true, null));


		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("meryem", "CLIENT");

	}
}
