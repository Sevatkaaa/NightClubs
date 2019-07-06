package night.clubs.facade;

import night.clubs.converter.NightClubConverter;
import night.clubs.data.NightClubData;
import night.clubs.model.NightClub;
import night.clubs.service.NightClubService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NightClubFacade {
    @Resource
    private NightClubService nightClubService;
    
    @Resource
    private NightClubConverter nightCLubConverter;
    
    public NightClubData getNightClubByName(String name) {
        NightClub club = nightClubService.getNightClubByName(name);
        return club == null ? null : nightCLubConverter.convert(club);
    }

    public NightClubData createNightClub(String name) {
        return nightCLubConverter.convert(nightClubService.createNightClub(name));
    }
}
