package util.stopwatch;

public class SecondStopwatch implements StopwatchI {
    private final String unit = "s";
    private final long start;

    public SecondStopwatch() {
        this.start = System.currentTimeMillis();
    }

    @Override
    public Double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start)/1000.0;
    }

    @Override
    public String getUnit() {
        return unit;
    }


}
