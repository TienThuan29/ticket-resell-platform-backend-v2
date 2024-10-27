package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class EventRevenueResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("boughtQuantity")
    private Integer boughtQuantity;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("revenueEvent")
    private Long revenueEvent;
}
