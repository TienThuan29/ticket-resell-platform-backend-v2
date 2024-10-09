package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventFilter {

    @JsonProperty("name")
    private String name;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("hashtagIds")
    private List<Integer> hashtagIds;
}
