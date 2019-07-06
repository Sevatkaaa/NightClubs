package night.clubs.service;

import night.clubs.model.Visitor;
import night.clubs.exception.VisitorExistsException;
import night.clubs.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitorService {
    @Resource
    private VisitorRepository visitorRepository;
    
    public Visitor createVisitor(String name) {
        List<Visitor> visitors = visitorRepository.findByName(name);
        if (visitors.isEmpty()) {
            return visitorRepository.save(new Visitor(name));
        } else {
            throw new VisitorExistsException("Visitor with such name already exists");
        }
    }
    
    public Visitor getVisitorByName(String name) {
        return visitorRepository.findByName(name).stream()
                .findFirst()
                .orElse(null);
    }
}
