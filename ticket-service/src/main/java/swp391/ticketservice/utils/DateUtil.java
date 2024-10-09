package swp391.ticketservice.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Date fixDateTimeResponse(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(7);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static Date fixDateTimeRequest(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.minusHours(7);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
