package night.clubs.facade;

import night.clubs.converter.NightClubConverter;
import night.clubs.converter.VisitorConverter;
import night.clubs.data.NightClubData;
import night.clubs.data.VisitorData;
import night.clubs.exception.VisitorDoesNotExistException;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import night.clubs.service.NightClubService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class NightClubFacade {
    @Resource
    private NightClubService nightClubService;
    
    @Resource
    private NightClubConverter nightCLubConverter;
    
    @Resource
    private VisitorConverter visitorConverter;
    
    public NightClubData getNightClubByName(String name) {
        NightClub club = nightClubService.getNightClubByName(name);
        return club == null ? null : nightCLubConverter.convert(club);
    }

    public NightClubData createNightClub(String name) {
        return nightCLubConverter.convert(nightClubService.createNightClub(name));
    }

    public List<VisitorData> getVisitorsForClubWithName(String name) {
        NightClub club = Optional
                .ofNullable(nightClubService.getNightClubByName(name))
                .orElseThrow(() -> new VisitorDoesNotExistException("Visitor with such name does not exist"));
        return visitorConverter.convertAll(club.getVisitors());
    }
}
