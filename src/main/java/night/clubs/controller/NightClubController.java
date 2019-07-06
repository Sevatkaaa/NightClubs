package night.clubs.controller;

import night.clubs.data.NightClub;
import night.clubs.service.NightClubService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/nightclub")
public class NightClubController {
    @Resource
    private NightClubService nightClubService;
    
    @RequestMapping(method = RequestMethod.POST)
    public NightClub createNightClub(@RequestParam String name) {
        return nightClubService.createNightClub(name);
    }
}
