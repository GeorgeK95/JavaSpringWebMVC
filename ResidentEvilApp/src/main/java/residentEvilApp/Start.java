package residentEvilApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
@SpringBootApplication
public class Start {

    public static void main(String[] args) {
        //please execute the viruses script in resources/data
        //NOT FOUND PAGE: http://localhost:8080/adadsa/adsdas
        //VIRUS NOT FOUND: http://localhost:8080/viruses/edit/222 with admin admin
        //UNAUTHORIZED: http://localhost:8080/viruses/edit/222 with user user

        SpringApplication.run(Start.class, args);
    }
}
