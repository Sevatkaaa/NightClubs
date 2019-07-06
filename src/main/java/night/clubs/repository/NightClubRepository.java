package night.clubs.repository;

import night.clubs.data.NightClub;
import org.springframework.data.repository.CrudRepository;

public interface NightClubRepository extends CrudRepository<NightClub, Integer> {
    
}
