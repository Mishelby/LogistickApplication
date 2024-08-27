package by.mishelby.logistickapplication.model.city;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import by.mishelby.logistickapplication.model.distance.Distance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "city_name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_map_id", referencedColumnName = "country_map_id")
    private CountryMap countryMap;

    @OneToMany(mappedBy = "cityFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Distance> distancesFrom;

    @OneToMany(mappedBy = "cityTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Distance> distancesTo;
}



