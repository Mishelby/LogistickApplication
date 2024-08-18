package by.mishelby.logistickapplication.model.city;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import by.mishelby.logistickapplication.model.distance.Distance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_map_id", referencedColumnName = "id")
    private CountryMap countryMap;

    @OneToMany(mappedBy = "cityFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Distance> distancesFrom;

    @OneToMany(mappedBy = "cityTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Distance> distancesTo;
}



