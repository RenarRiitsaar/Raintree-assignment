package raintree.testAssignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import static raintree.testAssignment.PatientInfo.*;


@SpringBootApplication
public class TestAssignmentApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(TestAssignmentApplication.class, args);

	}


		@Override
		public void run (String...args) throws Exception {
			Scanner sc = new Scanner(System.in);
			while (true) {
				try {
					System.out.println("""
							Enter an action:\s
							 1. Display patient/insurance data\s
							 2. Display statistics\
							\s
							 3. Check insurances by current date\s
							 4. Enter the date manually\s
							 5. Exit""");
					int action = sc.nextInt();

					switch (action) {

						case 1:
							displayPatientData();
							System.out.println();
							break;

						case 2:
							letterStatistics();
							System.out.println();
							break;

						case 3:
							checkInsuranceWithCurrentDate();
							System.out.println();
							break;

						case 4:
							System.out.println("Enter the date manually in MM-dd-yy format: ");
							String input = sc.next();
							dateManualCheck(input);
							System.out.println();
							break;

						case 5:
							System.exit(0);
							break;


						default:
							System.out.println("Invalid action. Choose between 1-5.");
							break;
					}
				} catch (Exception e) {
					sc.nextLine();
					System.out.println("Invalid action. Choose between 1-5.");
				}
			}
		}
	}



