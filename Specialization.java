package hospital;

public enum Specialization {
	CARDIO("Cardiologist"), INTERNAL("Internal Medicine"), PED("Pediatrics")
;
	private final String value;
	private Specialization(final String value) {
		this.value = value;
	}
    public String getValue() {
        return value;
    }
    public String toString() {
        return getValue();
    }
}