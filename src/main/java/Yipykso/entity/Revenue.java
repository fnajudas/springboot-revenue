package Yipykso.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "transaction_type_id")
    private Integer transactionTypeId;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    private Boolean deleted;
}