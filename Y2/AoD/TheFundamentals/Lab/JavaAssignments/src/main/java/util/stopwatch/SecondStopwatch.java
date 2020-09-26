package util.stopwatch;

public class SecondStopwatch implements Stopwatch {
    private final long start;

    public SecondStopwatch() {
        this.start = System.currentTimeMillis();
    }

    @Override
    public Double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start)/1000.0;
    }


}
