package util.stopwatch;

public class NanoStopwatch implements Stopwatch{
    private final long start;

    public NanoStopwatch() {
        this.start = System.nanoTime();
    }

    @Override
    public Double elapsedTime() {
        long now = System.nanoTime();
        return (double) (now - start);
    }
}
