package night.clubs.controller;

import night.clubs.data.Visitor;
import night.clubs.service.VisitorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "visitor")
public class VisitorController {
    @Resource
    private VisitorService visitorService;
    
    @RequestMapping(method = RequestMethod.POST)
    public Visitor createVisitor(String name) {
        return visitorService.createVisitor(name);
    }
}
