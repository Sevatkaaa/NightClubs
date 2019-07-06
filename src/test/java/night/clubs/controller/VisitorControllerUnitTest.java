package night.clubs.controller;

import night.clubs.data.VisitorData;
import night.clubs.facade.VisitorFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VisitorControllerUnitTest {

    private static final String VISITOR_NAME = "visitor";
    
    @InjectMocks
    private VisitorController visitorController = new VisitorController();
    
    @Mock
    private VisitorFacade visitorFacade;
    
    private VisitorData visitorData = new VisitorData();

    @Test
    public void shouldGetVisitorByName() {
        when(visitorFacade.getVisitorByName(VISITOR_NAME)).thenReturn(visitorData);
        
        VisitorData actual = visitorController.getVisitorByName(VISITOR_NAME);
        
        assertThat(actual).isEqualTo(visitorData);
    }

    @Test
    public void shouldCreateVisitor() {
        when(visitorFacade.createVisitor(VISITOR_NAME)).thenReturn(visitorData);

        VisitorData actual = visitorController.createVisitor(VISITOR_NAME);

        assertThat(actual).isEqualTo(visitorData);
    }
}