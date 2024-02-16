package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Timer {
    private final LocalDateTime start;
    private LocalDateTime end;

    public Timer(LocalDateTime start) {
        this.start = start;
    }

    public static Timer start() {
        return new Timer(LocalDateTime.now());
    }

    public Duration stop() {
        end = LocalDateTime.now();
        return Duration.between(start, end);
    }
}
