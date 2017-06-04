package hospital;

public class Patient extends Person {
	
	private String chart;
	private String bloodPressure;
	
	public String getChart() {
		return chart;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}
	
	public void setBloodPressure(String bp) {
		this.bloodPressure = bp;
	}
}
