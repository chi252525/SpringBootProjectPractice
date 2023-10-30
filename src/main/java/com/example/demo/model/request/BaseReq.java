package com.example.demo.model.request;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.ws.rs.BadRequestException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class BaseReq {
    private List<String> supportedMalls = Arrays.asList("tw", "us", "es", "gb");

    protected int toInt(String name, String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid format of " + name + ": " + value);
        }
    }

    protected DateTime toDateTime(String name, String value, String pattern) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZoneUTC();

        try {
            return DateTime.parse(value, formatter);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid date format of " + name + ": " + value);
        }
    }

    protected ZonedDateTime toZonedDateTime(String name, String value) {
        return toZonedDateTime(name, value, ZoneOffset.UTC);
    }

    protected ZonedDateTime toTwZonedDateTime(String name, String value) {
        return toZonedDateTime(name, value, ZoneId.of("Asia/Taipei"));
    }

    private ZonedDateTime toZonedDateTime(String name, String value, ZoneId zone) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Invalid date format of " + name + ": " + value);
        }
        return ZonedDateTime.of(localDateTime, zone);
    }

    protected void require(String name, String value) {
        if (StringUtils.isBlank(value)) {
            throw new BadRequestException(name + " is required.");
        }
    }

    protected void requireObject(String name, Object o) {
        if (o == null) {
            throw new BadRequestException(name + " is required.");
        }
    }

    protected void requireList(String name, List<?> list) {
        if (list == null || list.isEmpty()) {
            throw new BadRequestException(name + " is required.");
        }
    }

    protected void uuid(String name, String value) {
        fixLength(36, name, value);
    }

    protected void min(int limit, String name, int value) {
        if (value < limit) {
            throw new BadRequestException("min of " + name + " is " + limit);
        }
    }

    protected void max(int limit, String name, int value) {
        if (value > limit) {
            throw new BadRequestException("max of " + name + " is " + limit);
        }
    }

    protected void fixLength(int length, String name, String value) {
        if (value == null) {
            return;
        }

        if (value.length() != length) {
            throw new BadRequestException("Length of " + name + " must be " + length + ".");
        }
    }

    protected void minLength(int length, String name, String value) {
        if (value == null) {
            return;
        }

        if (value.length() < length) {
            throw new BadRequestException("Min length of " + name + " is " + length + ".");
        }
    }

    protected void maxLength(int length, String name, String value) {
        if (value == null) {
            return;
        }

        if (value.length() > length) {
            throw new BadRequestException("Max length of " + name + " is " + length + ".");
        }
    }

    protected void maxSize(int size, String name, List<?> list) {
        if (list == null) {
            return;
        }

        if (list.size() > size) {
            throw new BadRequestException("Max size of " + name + " is " + size + ".");
        }
    }

    protected void pattern(String name, String value, String pattern) {
        pattern(name, value, pattern, "Unsupported " + name + ": " + value);
    }

    protected void pattern(String name, String value, String pattern, String message) {
        if (value == null) {
            return;
        }

        if (!value.matches(pattern)) {
            throw new BadRequestException(message);
        }
    }
}
