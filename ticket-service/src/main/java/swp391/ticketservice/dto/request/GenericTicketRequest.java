package swp391.ticketservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericTicketRequest {

    @JsonProperty("genericTicketName")
    @NotEmpty(message = "Tên vé không được phép rỗng")
    private String genericTicketName;

    @JsonProperty("price")
    @NotNull(message = "Giá vé không được phép rỗng")
    @Min(value = 0, message = "Giá vé phải lớn hơn hoặc bằng 0")
    private Long price;

    @JsonProperty("salePercent")
    private Double salePercent;

    @JsonProperty("area")
    private String area;

    @JsonProperty("expiredDateTime")
    @NotNull(message = "Ngày vé hết hạn không được phép rỗng")
    private Date expiredDateTime;

    @JsonProperty("description")
    private String description;

    @JsonProperty("linkEvent")
    private String linkEvent;

    @JsonProperty("isPaper")
    private Boolean isPaper;

    @JsonProperty("policyId")
    @NotNull(message = "Chính sách không được phép để trống")
    private Integer policyId;

    @JsonProperty("categoryId")
    @NotNull(message = "Danh mục không được phép để trống")
    private Integer categoryId;

    @JsonProperty("eventId")
    @NotNull(message = "Sự kiện không được phép để trống")
    private Integer eventId;

    @JsonProperty("sellerId")
    @NotNull(message = "Người bán không được phép để trống")
    private Long sellerId;
}
