package swp391.staffservice.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Date fixDateTime(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(7);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
