package night.clubs.service;

import night.clubs.data.NightClub;
import night.clubs.exception.NightClubExistsException;
import night.clubs.repository.NightClubRepository;

import javax.annotation.Resource;
import java.util.List;

public class NightClubService {
    @Resource
    private NightClubRepository nightClubRepository;
    
    public void createNightClub(String name) {
        List<NightClub> clubs = nightClubRepository.findByName(name);
        if (clubs.isEmpty()) {
            nightClubRepository.save(new NightClub(name));
        } else {
            throw new NightClubExistsException("Visitor with such name already exists");
        }
    }
}
