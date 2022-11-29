package com.paymybuddy.paywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaywebappApplication {
//public class PaywebappApplication implements CommandLineRunner {
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private TransferService transferService;

    public static void main(String[] args) {
        SpringApplication.run(PaywebappApplication.class, args);
//	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	String rawPassword = "123456";
//	String encodedPassword = encoder.encode(rawPassword);
//		System.out.println(encodedPassword);

    }
}
//
//
//
//	@Transactional
//	@Override
//	public void run(String... args) throws Exception {
//		//Iterable<User> users = userService.getAllUsers();
//		//users.forEach(user -> System.out.println("le nom de l'utilisateur est : " + user.getFirstname()));
//
//
//		Optional<User> users1 = userService.getUserById(1);
//		User user = users1.get();
//		user.getTransferList().forEach(transfer -> System.out.println(transfer.getAmount()));
////  	System.out.println(user.getFirstname());
////		users1.ifPresent(user -> System.out.println("l'email 'de l'user dont l'id = 1 est : " + user.getEmail()));
//
////		User user1 = new User();
////		user1.setFirstname("prenom1");
////		user1.setLastname("nom1");
////		user1.setDescription("description 1");
////		user1.setEmail("email1");
////		user1.setPassword("pass1");
////		user1.setBalance(500);
////		this.userService.addUser(user1); //enregistrement en bdd
//	}
