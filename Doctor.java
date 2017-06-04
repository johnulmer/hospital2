package hospital;

public class Doctor extends Person implements CareGiver {
	
	private String specialty;
	private Specialization specialization;
	
	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getFormattedName () {
		return "DOCTOR " + this.getFirstName() + " " + this.getLastName();
	}

	@Override
	public String takeBloodPressure(Patient p) {
		return "I am above this!";
	}

	@Override
	public String readChart(Patient p) {
		return "haha - it's the best medicine";
	}

}
