package by.mishelby.logistickapplication.controller.MVC;

import by.mishelby.logistickapplication.service.TruckService.TruckDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/truckMVCController")
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckController {
    private final TruckDAO truckDAO;

    @GetMapping
    public String getTrucks(Model model) {
        model.addAttribute("trucks", truckDAO.findAllTrucks());
        return "trucks";
    }
}
