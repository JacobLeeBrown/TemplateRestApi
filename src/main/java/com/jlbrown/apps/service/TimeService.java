package com.jlbrown.apps.service;

import com.jlbrown.apps.util.LoggingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class TimeService {

    private static final Logger log = LoggerFactory.getLogger(TimeService.class);

    public static final ZoneId SYSTEM_ZONE = ZoneId.of("CST", ZoneId.SHORT_IDS);
    public static final Clock SYSTEM_CLOCK = Clock.system(SYSTEM_ZONE);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getCurrentTime(String zone) {
        ZoneId zoneId = SYSTEM_ZONE;
        try {
            zoneId = ZoneId.of(zone, ZoneId.SHORT_IDS);
        } catch (DateTimeException e) {
            log.error("Unable to convert zone ({}), defaulting to system zone. stackTrace={}", zone, LoggingUtil.flattenStackTrace(e));
        }

        LocalDateTime currentTime = LocalDateTime.now(SYSTEM_CLOCK.withZone(zoneId));
        return DATE_TIME_FORMATTER.format(currentTime);
    }

}
