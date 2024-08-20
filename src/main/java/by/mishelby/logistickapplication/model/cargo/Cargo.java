package by.mishelby.logistickapplication.model.cargo;

import by.mishelby.logistickapplication.model.CargoStatus.CargoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "cargo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "number")
    @NotBlank(message = "Cargo number should not be null or blank")
    private BigDecimal number;

    @Column(name = "description")
    @NotBlank(message = "Description should not be null or blank")
    private String description;

    @Column(name = "weight")
    @NotBlank(message = "Weight should not be an empty or null")
    private Integer weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotBlank(message = "Cargo status should not be an empty or null")
    private CargoStatus cargoStatus;
}
