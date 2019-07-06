package night.clubs.service;

import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import night.clubs.repository.NightClubRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ClubVisitService {
    @Resource
    private NightClubRepository nightClubRepository;


    public void addVisitor(NightClub nightClub, Visitor newVisitor) {
        List<Visitor> visitors = nightClub.getVisitors();
        visitors.add(newVisitor);
        nightClub.setVisitors(visitors);
        List<NightClub> clubsVisited = newVisitor.getClubsVisited();
        clubsVisited.add(nightClub);
        newVisitor.setClubsVisited(clubsVisited);
        nightClubRepository.save(nightClub);
    }

    public void addNightClub(Visitor visitor, NightClub newClub) {
        List<NightClub> clubsVisited = visitor.getClubsVisited();
        clubsVisited.add(newClub);
        visitor.setClubsVisited(clubsVisited);
        List<Visitor> visitors = newClub.getVisitors();
        visitors.add(visitor);
        newClub.setVisitors(visitors);
        nightClubRepository.save(newClub);
    }

    public void createVisit(Visitor visitor, NightClub club) {
        List<NightClub> clubsVisited = visitor.getClubsVisited();
        clubsVisited.add(club);
        visitor.setClubsVisited(clubsVisited);
        List<Visitor> visitors = club.getVisitors();
        visitors.add(visitor);
        club.setVisitors(visitors);
        nightClubRepository.save(club);
    }

    public List<NightClub> getClubsNotVisitedByVisitor(Visitor visitor) {
        List<NightClub> clubs = getAllClubs();
        clubs.removeAll(visitor.getClubsVisited());
        return clubs;
    }

    private List<NightClub> getAllClubs() {
        Iterable<NightClub> allClubs = nightClubRepository.findAll();
        List<NightClub> clubs = new ArrayList<>();
        Iterator<NightClub> iterator = allClubs.iterator();
        while (iterator.hasNext()) {
            clubs.add(iterator.next());
        }
        return clubs;
    }
}
