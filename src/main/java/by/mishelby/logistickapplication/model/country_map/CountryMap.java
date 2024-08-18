package by.mishelby.logistickapplication.model.country_map;
import by.mishelby.logistickapplication.model.city.City;
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
public class CountryMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "countryMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<City> cities;
}

