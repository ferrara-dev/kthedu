package stopwatch;

public class StopwatchFactory {
    private StopwatchFactory(){

    }

    public static StopwatchI getStopwatch(StopwatchUnit stopwatchUnit){
        switch (stopwatchUnit){
            case MILLI:
                return new MilliStopwatch();
            case NANO:
                return new NanoStopwatch();
            case SECOND:
                return new SecondStopwatch();
            default:
                throw new IllegalArgumentException();
        }
    }
}
