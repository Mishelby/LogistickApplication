package by.mishelby.logistickapplication.repository.CargoRepository;

import by.mishelby.logistickapplication.model.cargo.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, BigInteger> {
}
