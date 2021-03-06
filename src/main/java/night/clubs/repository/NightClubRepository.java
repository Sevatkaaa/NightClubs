package night.clubs.repository;

import night.clubs.model.NightClub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NightClubRepository extends CrudRepository<NightClub, Integer> {
    List<NightClub> findByName(String name);
}
