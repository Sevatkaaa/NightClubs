package night.clubs.service;

import night.clubs.model.NightClub;
import night.clubs.exception.NightClubExistsException;
import night.clubs.repository.NightClubRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NightClubService {
    @Resource
    private NightClubRepository nightClubRepository;
    
    public NightClub createNightClub(String name) {
        List<NightClub> clubs = nightClubRepository.findByName(name);
        if (clubs.isEmpty()) {
            return nightClubRepository.save(new NightClub(name));
        } else {
            throw new NightClubExistsException("Visitor with such name already exists");
        }
    }
    
    public NightClub getNightClubByName(String name) {
        return nightClubRepository.findByName(name).stream()
                .findFirst()
                .orElse(null);
    }
}
