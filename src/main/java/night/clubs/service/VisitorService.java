package night.clubs.service;

import night.clubs.data.Visitor;
import night.clubs.exception.VisitorExistsException;
import night.clubs.repository.VisitorRepository;

import javax.annotation.Resource;
import java.util.List;

public class VisitorService {
    @Resource
    private VisitorRepository visitorRepository;
    
    public void createVisitor(String name) {
        List<Visitor> visitors = visitorRepository.findByName(name);
        if (visitors.isEmpty()) {
            visitorRepository.save(new Visitor(name));
        } else {
            throw new VisitorExistsException("Visitor with such name already exists");
        }
    }
}
