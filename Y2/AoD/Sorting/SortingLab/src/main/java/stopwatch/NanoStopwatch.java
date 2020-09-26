package stopwatch;

public class NanoStopwatch implements StopwatchI {
    private final String unit = "ns";
    private final long start;

    public NanoStopwatch() {
        this.start = System.nanoTime();
    }

    @Override
    public Double elapsedTime() {
        long now = System.nanoTime();
        return (double) (now - start);
    }

    @Override
    public String getUnit() {
        return unit;
    }
}
