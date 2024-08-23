package by.mishelby.logistickapplication.service.RoutePointsService;

import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;

import java.util.List;

public interface RoutePointsService {
    List<RoutePoint> getRoutePoints();

    RoutePoint findById(int id);

    RoutePoint createRoutePoint(RoutePoint routePoint);

    RoutePoint updateRoutePoint(RoutePoint routePoint);

    void deleteRoutePoint(int id);
}
