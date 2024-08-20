package by.mishelby.logistickapplication.controller.MVC;

import by.mishelby.logistickapplication.service.DriverService.DriverDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("driverMVCController")
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverDAO driverDAO;

    @GetMapping
    public String getDrivers(Model model) {
        model.addAttribute("drivers", driverDAO.findAllDrivers());
        return "drivers";
    }
}
