package night.clubs.repository;

import night.clubs.data.Visitor;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    
}
