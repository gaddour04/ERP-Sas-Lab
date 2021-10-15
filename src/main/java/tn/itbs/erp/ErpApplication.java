package tn.itbs.erp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.entites.LigneFacture;
import tn.itbs.erp.entites.Role;
import tn.itbs.erp.repository.ArticleRepository;
import tn.itbs.erp.repository.FactureRepository;
import tn.itbs.erp.repository.LigneFactureReoisitory;
//import tn.itbs.erp.repository.RoleRepository;
import tn.itbs.erp.repository.UserAppRepository;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Configuration
@EnableScheduling
@EnableSwagger2
public class ErpApplication implements CommandLineRunner{
	//@Autowired
	//RoleRepository roleRepository;
	@Autowired
	UserAppRepository appRepository;
	@Autowired FactureRepository facturereposi;
	@Autowired ArticleRepository articlerepo;
	@Autowired LigneFactureReoisitory lignereposiotry;
	
	@Bean public PasswordEncoder passwordEncoder() { return new
			  BCryptPasswordEncoder(); }
	
	//ThreadPoolTaskExecutor est une classe spécialisée pour l'exécution de tâches.
	//ThreadPoolTaskScheduler est une classe spécialisée pour la planification des tâches.
	 @Bean
	    TaskScheduler threadPoolTaskScheduler() {
	        return new ThreadPoolTaskScheduler();
	    }

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * roleRepository.save(new Role("ADMIN")); roleRepository.save(new
		 * Role("CLIENT")); roleRepository.save(new Role("FOURNISSEUR"));
		 */
		/*
		 * AppUser user=new AppUser("ahmed@gmail.com","ahmed","1234"); Role
		 * role=roleRepository.findById(2).get(); user.addRole(role);
		 * //user.getRoles().add(role); appRepository.save(user);
		 */
		/*
		 * Date date = new Date();
		 * 
		 * //String dateStart = "11/03/14 09:29:58"; String dateStop =
		 * "2021-06-22 12:00:00";
		 * 
		 * // Custom date format SimpleDateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String dateStart=
		 * format.format(date); Date d1 = null; Date d2 = null; try { d1 =
		 * format.parse(dateStart); d2 = format.parse(dateStop); } catch (ParseException
		 * e) { e.printStackTrace(); }
		 * 
		 * // Get msec from each, and subtract. long diff = d2.getTime() - d1.getTime();
		 * //long diffSeconds = diff / 1000;
		 * 
		 * long diffMinutes = diff / (60 * 1000); long diffHours = diff / (60 * 60 *
		 * 1000); System.out.println("Time in seconds: " + diff + " seconds.");
		 * System.out.println("Time in minutes: " + diffMinutes + " minutes.");
		 * System.out.println("Time in hours: " + diffHours + " hours.");
		 */
		
		//System.out.println(lignereposiotry.moisPrecedent());
	    
	}

}
