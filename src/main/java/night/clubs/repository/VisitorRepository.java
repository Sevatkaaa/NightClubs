package night.clubs.repository;

import night.clubs.data.Visitor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    List<Visitor> findByName(String name);
}
