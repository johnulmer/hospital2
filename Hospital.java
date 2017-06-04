package hospital;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Hospital {
	
	// declare two arrays, one for patients, one that will hold doctors & nurses
	private static Patient[] patients;
	private static CareGiver[] careGivers;
	
	private static void populatePatients() {
		String[] firsts = new String[]{"Erasmo", "Ellsworth", "Kecia", "Carletta",
			"Stephanie", "Horacio", "Gaston", "Janene", "Miesha", "Trudy"};
		String[] lasts = new String[]{"Ebron", "Ericson", "Kerman", "Conkling",
				"Sullens", "Homer", "Goffney", "Joerling", "Mccombs", "Tinney"};
		for (int i = 0; i < 10; i++) {
			Patient p = new Patient();
			//p.SetName("First" + i, i + "Last");
			p.SetName(firsts[i], lasts[i]);
			p.setChart("Patient has " + i + " days left!");
			patients[i] = p;
		}
	}
	private static void populateCareGivers() {
		for (int i = 0; i < 5; i++) {
			Doctor d = new Doctor();
			d.SetName("dr" + i, i + "Last");
			d.setSpecialization(Specialization.CARDIO);
			careGivers[i] = d;
		}
		for (int i = 0; i < 5; i++) {
			Nurse n = new Nurse();
			n.SetName("Nurse" + i, i + "Last");
			careGivers[i+5] = n;
		}
	}
	
	private static void readCharts() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Treating patient: " + patients[i].getFormattedName());
			Random rand = new Random();
			int randomNum = rand.nextInt(10);
			System.out.println(careGivers[randomNum].readChart(patients[i])); 
			System.out.println(); 
		}
	}
	
	private static void persistPatients(Patient[] patients) {
		try {
			FileOutputStream fileOut =
					new FileOutputStream("patients.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i = 0; i < patients.length; i++ ){
				Patient p = new Patient();
				p = patients[i];
				out.writeObject(p);
			}
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in patients.ser");
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	private static void loadPatients(Patient[] patients) {
		Patient p=null ;
		int i = 0;
		try {
			FileInputStream fis = new FileInputStream("patients.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				try {
					p = (Patient)ois.readObject();
					patients[i] = p;
					i++;
					System.out.println("found: " + p.getFormattedName());
				} catch (IOException e) {
					//e.printStackTrace();
					break;
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();

		}
	}


	
	public static void main (String args[]) {
		//initialize the Arrays of Patients and Staff
		patients = new Patient[10];
		careGivers = new CareGiver[10];
		
		//populate patients, doctors, nurses
		//populatePatients();
		loadPatients(patients);
		
		populateCareGivers();

		// for each patient, have a random CareGiver read a patient's chart
		readCharts();
		
		//save patients out to file
		persistPatients(patients);
	}

}
