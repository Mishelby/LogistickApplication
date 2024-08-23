package by.mishelby.logistickapplication.repository.RoutePointsRepository;

import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoutePointsRepository extends JpaRepository<RoutePoint, BigInteger> {
}
