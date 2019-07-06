package night.clubs.controller;

import night.clubs.data.NightClubData;
import night.clubs.facade.NightClubFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/nightclub")
public class NightClubController {
    @Resource
    private NightClubFacade nightClubFacade;
    
    @RequestMapping(method = RequestMethod.GET)
    public NightClubData getNightClubByName(@RequestParam String name) {
        return nightClubFacade.getNightClubByName(name);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public NightClubData createNightClub(@RequestParam String name) {
        return nightClubFacade.createNightClub(name);
    }
}
