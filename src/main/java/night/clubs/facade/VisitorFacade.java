package night.clubs.facade;

import night.clubs.converter.VisitorConverter;
import night.clubs.data.VisitorData;
import night.clubs.model.Visitor;
import night.clubs.service.VisitorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class VisitorFacade {
    @Resource
    private VisitorService visitorService;
    
    @Resource
    private VisitorConverter visitorConverter;
    
    public VisitorData getVisitorByName(String name) {
        Visitor visitor = visitorService.getVisitorByName(name);
        return visitor == null ? null : visitorConverter.convert(visitor);
    }

    public VisitorData createVisitor(String name) {
        return visitorConverter.convert(visitorService.createVisitor(name));
    }
}
