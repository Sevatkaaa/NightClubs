package night.clubs.facade;

import night.clubs.converter.NightClubConverter;
import night.clubs.data.NightClubData;
import night.clubs.exception.VisitorDoesNotExistException;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import night.clubs.exception.ClubVisitExistsException;
import night.clubs.service.ClubVisitService;
import night.clubs.service.NightClubService;
import night.clubs.service.VisitorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ClubVisitFacade {
    @Resource
    private ClubVisitService clubVisitService;
    
    @Resource
    private VisitorService visitorService;
    
    @Resource
    private NightClubService nightClubService;
    
    @Resource
    private NightClubConverter nightCLubConverter;

    public void createVisit(String visitorName, String clubName) {
        Visitor visitor = visitorService.getVisitorByName(visitorName);
        NightClub nightClub = nightClubService.getNightClubByName(clubName);
        if (visitor != null && nightClub != null) {
            if (visitor.getClubsVisited().contains(nightClub)) {
                throw new ClubVisitExistsException("User already visited night club");
            } else {
                clubVisitService.createVisit(visitor, nightClub);
            }
        } else if (visitor == null && nightClub != null) {
            createVisitWithNewVisitor(visitorName, nightClub);
        } else if (visitor != null) {
            createVisitWithNewClub(visitor, clubName);
        } else {
            createVisitWithNewClubAndNewVisitor(visitorName, clubName);
        }
    }

    private void createVisitWithNewVisitor(String visitorName, NightClub nightClub) {
        Visitor newVisitor = visitorService.createVisitor(visitorName);
        clubVisitService.addVisitor(nightClub, newVisitor);
    }

    private void createVisitWithNewClub(Visitor visitor, String clubName) {
        NightClub newClub = nightClubService.createNightClub(clubName);
        clubVisitService.addNightClub(visitor, newClub);
    }

    private void createVisitWithNewClubAndNewVisitor(String visitorName, String clubName) {
        Visitor newVisitor = visitorService.createVisitor(visitorName);
        NightClub newClub = nightClubService.createNightClub(clubName);
        clubVisitService.createVisit(newVisitor, newClub);
    }

    public List<NightClubData> getClubsNotVisitedByVisitorWithName(String name) {
        Visitor visitor = visitorService.getVisitorByName(name);
        if (visitor == null) {
            throw new VisitorDoesNotExistException("Visitor with such name does not exist");
        }
        return nightCLubConverter.convertAll(clubVisitService.getClubsNotVisitedByVisitor(visitor));
    }
}
