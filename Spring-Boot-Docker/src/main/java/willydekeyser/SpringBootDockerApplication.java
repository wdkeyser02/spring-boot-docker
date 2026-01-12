package willydekeyser;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerApplication.class, args);
	}
	
	@Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }

}

record User(String username, String password, boolean enabled) {}
record Authorities(String username, String authority) {}

@Repository
class UserRepository {

    JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAllUsers() {
        String sql = "select * from users;";
        return jdbcTemplate.query(sql, new DataClassRowMapper<>(User.class));
    }

    public List<Authorities> findAllAuthorities() {
        String sql = "select * from authorities;";
        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Authorities.class));
    }
}

@RestController
class UserController {

    UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @GetMapping("/authorities")
    public List<Authorities> findAllAuthorities() {
        return userRepository.findAllAuthorities();
    }
}