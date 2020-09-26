package stopwatch;

public enum StopwatchUnit {
    NANO("ns"),
    MILLI("ms"),
    SECOND("s");
    private String unit;
    StopwatchUnit(String s) {
        this.unit = s;
    }

    public String getUnit() {
        return unit;
    }
}
