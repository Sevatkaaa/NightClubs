package night.clubs.controller;

import night.clubs.data.VisitorData;
import night.clubs.facade.VisitorFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "visitor")
public class VisitorController {
    @Resource
    private VisitorFacade visitorFacade;
    
    @RequestMapping(method = RequestMethod.GET)
    public VisitorData getVisitorByName(@RequestParam String name) {
        return visitorFacade.getVisitorByName(name);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public VisitorData createVisitor(String name) {
        return visitorFacade.createVisitor(name);
    }
}
