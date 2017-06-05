package hospital;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Hospital {
	
	private static CareGiver[] careGivers;
	
	// commented out after persistence working
	private static void populatePatients(Patient[] patients) {
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
	
	private static void readCharts(Patient[] patients) {
		for (int i = 0; i < patients.length; i++) {
			//System.out.println(patients[i].toString());
			System.out.println("Treating patient: " + patients[i].getFormattedName());
			Random rand = new Random();
			int randomNum = rand.nextInt(10);
			System.out.println(careGivers[randomNum].readChart(patients[i])); 
			System.out.println("test"); 
		}
	}
	
	private static void persistPatients(Patient[] patients) {
		try {
			FileOutputStream fileOut =
					new FileOutputStream("objects.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i = 0; i < patients.length; i++ ){
				//Patient p = new Patient();
				//p = patients[i];
				//out.writeObject(p);
				out.writeObject(patients[i]);
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
				fis.close();
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}


	
	public static void main (String args[]) {
		//initialize the Arrays of Patients and Staff
		Patient[] patients = new Patient[10];
		careGivers = new CareGiver[10];
		
		//populate patients, doctors, nurses
		//populatePatients(patients); //- no longer needed now that persisting through serialization
		loadPatients(patients);
		
		populateCareGivers();

		// for each patient, have a random CareGiver read a patient's chart
		readCharts(patients);
		
		//save patients out to file
		persistPatients(patients);
	}

}
