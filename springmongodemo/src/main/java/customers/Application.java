package customers;

import customers.domain.Address;
import customers.domain.Student;
import customers.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public Application(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        studentRepository.deleteAll();

        List<Student> students = List.of(
                new Student("Alice Johnson", "555-1001", "alice.johnson@example.com",
                        new Address("123 Oak St", "Fairfield", "52556")),
                new Student("Bob Smith", "555-1002", "bob.smith@example.com",
                        new Address("77 Main Ave", "Chicago", "60601")),
                new Student("Alice Johnson", "555-1003", "alice2.johnson@example.com",
                        new Address("14 Pine Rd", "Fairfield", "52557")),
                new Student("Carlos Diaz", "555-1004", "carlos.diaz@example.com",
                        new Address("902 Lake Dr", "Seattle", "98101")),
                new Student("Nina Patel", "555-1005", "nina.patel@example.com",
                        new Address("8 River Ln", "Chicago", "60602"))
        );

        studentRepository.saveAll(students);

        System.out.println("--------- ALL STUDENTS ---------");
        studentRepository.findAll().forEach(System.out::println);

        String targetName = "Alice Johnson";
        System.out.println("--------- STUDENTS BY NAME: " + targetName + " ---------");
        studentRepository.findByName(targetName).forEach(System.out::println);

        String targetPhoneNumber = "555-1004";
        System.out.println("--------- STUDENT BY PHONE NUMBER: " + targetPhoneNumber + " ---------");
        System.out.println(studentRepository.findByPhoneNumber(targetPhoneNumber));

        String targetCity = "Chicago";
        System.out.println("--------- STUDENTS BY CITY: " + targetCity + " ---------");
        studentRepository.findByAddressCity(targetCity).forEach(System.out::println);
    }
}
