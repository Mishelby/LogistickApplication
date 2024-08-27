package by.mishelby.logistickapplication.model.country_map;
import by.mishelby.logistickapplication.model.city.City;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "country_map")
public class CountryMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_map_id", unique = true, nullable = false)
    private BigInteger id;

    @OneToMany(mappedBy = "countryMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<City> cities;
}

