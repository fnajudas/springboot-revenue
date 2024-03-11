package Yipykso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueRequest {
    // ID is auto increment on table revenue

    @NotNull(message = "Building ID is required")
    @JsonProperty("building_id")
    private Integer buildingId;

    @NotNull(message = "Transaction Type ID is required")
    @JsonProperty("transaction_type_id")
    private Integer transactionTypeId;

    @PastOrPresent(message = "Effective date must be in the past or present")
    @JsonProperty("effective_date")
    private Date effectiveDate;
}
