package util.stopwatch;

public class MilliStopwatch implements Stopwatch {
    private final Long start;

    public MilliStopwatch(){
        start = System.currentTimeMillis();
    }


    @Override
    public Double elapsedTime() {
        long now = System.currentTimeMillis();
        return (double) (now - start);
    }
}
