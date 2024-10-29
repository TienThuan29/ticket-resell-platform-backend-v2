package swp391.adminservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import jakarta.persistence.*;


@SqlResultSetMapping(
        name = "EventRevenueResponseMapping",
        classes = @ConstructorResult(
                targetClass = EventRevenueResponse.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "boughtQuantity", type = Integer.class),
                        @ColumnResult(name = "startDate", type = Date.class),
                        @ColumnResult(name = "revenueEvent", type = Double.class)
                }
        )
)
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
    private Double revenueEvent;

}

