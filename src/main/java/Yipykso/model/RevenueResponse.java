package Yipykso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueResponse {
    private Integer id;

    @JsonProperty("building_id")
    private Integer buildingId;

    @JsonProperty("transaction_type_id")
    private Integer transactionTypeId;

    @JsonProperty("effective_date")
    private Date effectiveDate;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private Boolean deleted;
}
