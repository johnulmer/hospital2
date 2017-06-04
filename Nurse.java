package hospital;

public class Nurse extends Person implements CareGiver {

	@Override
	public String takeBloodPressure(Patient p) {
		return p.getBloodPressure();
	}

	@Override
	public String readChart(Patient p) {
		return p.getChart();
	}

}
