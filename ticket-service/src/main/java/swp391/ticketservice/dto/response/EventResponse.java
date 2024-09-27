package swp391.ticketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("image")
    private byte[] image;

}
