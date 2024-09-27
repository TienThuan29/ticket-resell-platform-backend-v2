package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @JsonProperty("name")
    @NotBlank(message = "Tên sự kiện không để trống")
    private String name;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonProperty("detail")
    @NotBlank(message = "Mô tả sự kiện không được để trống")
    private String detail;

    @JsonProperty("image")
    private byte[] image;

}
