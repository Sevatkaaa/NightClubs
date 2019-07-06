package night.clubs.controller;

import night.clubs.data.NightClubData;
import night.clubs.facade.ClubVisitFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "clubvisit")
public class ClubVisitController {
    @Resource
    private ClubVisitFacade clubVisitFacade;
    
    @RequestMapping(method = RequestMethod.POST)
    public void createVisit(@RequestParam String visitorName, @RequestParam String clubName) {
        clubVisitFacade.createVisit(visitorName, clubName);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<NightClubData> getClubsNotVisitedByVisitorWithName(@RequestParam String name) {
        return clubVisitFacade.getClubsNotVisitedByVisitorWithName(name);
    }
}
