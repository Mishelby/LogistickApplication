package by.mishelby.logistickapplication.model.distance;
import by.mishelby.logistickapplication.model.city.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distance_id", unique = true, nullable = false)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "city_from_id", referencedColumnName = "city_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to_id", referencedColumnName = "city_id")
    private City cityTo;

    @Column(name = "distance", nullable = false)
    private Double distance;
}

