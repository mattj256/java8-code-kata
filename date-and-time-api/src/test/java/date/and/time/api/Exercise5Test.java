package date.and.time.api;

import common.test.tool.annotation.Easy;
import common.test.tool.dataset.DateAndTimes;

import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Though old Date APIs are not recommended to use with Java8 Date and Time APIs, there could be some cases that you
 * need to do so. Thus you should know how to convert bidirectionally.
 */
public class Exercise5Test {

    @Easy @Test
    public void localDateTime2Timestamp() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;

        /**
         * Create a {@link java.sql.Timestamp} from {@link ldt}
         */
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        assertThat(timestamp.toString(), is("2015-06-18 23:07:30.5"));
    }

    @Easy @Test
    public void localDate2date() {
        LocalDate ld = DateAndTimes.LD_20150618;

        /**
         * Create a {@link java.sql.Date} from {@link ld}
         */
        ZonedDateTime zdt = ld.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        Date date = new Date(instant.toEpochMilli());

        assertThat(date.toString(), is("2015-06-18"));
    }

    @Easy @Test
    public void timestamp2LocalDateTime() {
        Timestamp timestamp = DateAndTimes.OLD_TIMESTAMP_20150618_23073050;

        /**
         * Create a {@link LocalDateTime} from {@link timestamp}
         */
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        assertThat(localDateTime.toString(), is("2015-06-18T23:07:30.500"));
    }

    @Easy @Test
    public void date2LocalDate() {
        Date date = DateAndTimes.OLD_DATE_20150618;

        /**
         * Create a {@link LocalDate} from {@link date}
         */
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        assertThat(localDate.toString(), is("2015-06-18"));
    }
}
