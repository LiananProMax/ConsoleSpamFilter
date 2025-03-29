package dev.lianan.consoleSpamFilter.share;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

import java.util.List;

public class LogFilter extends AbstractFilter {

    private final List<String> filterPatterns;

    public LogFilter(List<String> filterPatterns) {
        this.filterPatterns = filterPatterns;
    }

    @Override
    public Result filter(LogEvent event) {
        String message = event.getMessage().getFormattedMessage();
        return filterPatterns.stream().anyMatch(message::contains) ?
                Result.DENY : Result.NEUTRAL;
    }
}
