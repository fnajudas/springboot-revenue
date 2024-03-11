package Yipykso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueDeleted {
    @NotNull(message = "Deleted cant not null")
    @JsonProperty("deleted")
    private Boolean deleted;
}
