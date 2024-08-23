package by.mishelby.logistickapplication.service.RoutePointsService;

import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.repository.RoutePointsRepository.RoutePointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.RouteMatcher;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoutePointsDAO implements RoutePointsService {
    private final RoutePointsRepository routePointsRepository;

    @Override
    public List<RoutePoint> getRoutePoints() {
        List<RoutePoint> pointsRepositoryAll = routePointsRepository.findAll();
        if (pointsRepositoryAll.isEmpty()) {
            log.info("No route points found");
            return Collections.emptyList();
        }
        return routePointsRepository.findAll();
    }

    @Override
    public RoutePoint findById(int id) {
        return routePointsRepository.findById(BigInteger.valueOf(id)).orElseThrow(()
                -> new RuntimeException("No route points found"));
    }

    @Override
    public RoutePoint createRoutePoint(RoutePoint routePoint) {
        return routePointsRepository.save(routePoint);
    }

    @Override
    public RoutePoint updateRoutePoint(RoutePoint routePoint) {
        return null;
    }

    @Override
    public void deleteRoutePoint(int id) {
        RoutePoint routePoint = this.findById(id);
        routePointsRepository.delete(routePoint);
    }
}
