package util.stopwatch;

public class MilliStopwatch implements Stopwatch {
    private final String unit = "ms";
    private final Long start;

    public MilliStopwatch(){
        start = System.currentTimeMillis();
    }


    @Override
    public Double elapsedTime() {
        long now = System.currentTimeMillis();
        return (double) (now - start);
    }

    @Override
    public String getUnit() {
        return unit;
    }
}
