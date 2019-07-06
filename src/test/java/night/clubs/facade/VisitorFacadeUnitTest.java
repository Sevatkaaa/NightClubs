package night.clubs.facade;

import night.clubs.converter.VisitorConverter;
import night.clubs.data.VisitorData;
import night.clubs.model.Visitor;
import night.clubs.service.VisitorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VisitorFacadeUnitTest {

    private static final String VISITOR_NAME = "visitor";
    
    @InjectMocks
    private VisitorFacade visitorFacade = new VisitorFacade();
    
    @Mock
    private VisitorService visitorService;
    @Mock
    private VisitorConverter visitorConverter;
    
    private Visitor visitor = new Visitor();
    private VisitorData visitorData = new VisitorData();

    @Before
    public void setUp() {
        when(visitorConverter.convert(visitor)).thenReturn(visitorData);
    }

    @Test
    public void shouldGetVisitorByName() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(visitor);

        VisitorData actual = visitorFacade.getVisitorByName(VISITOR_NAME);

        verify(visitorService).getVisitorByName(VISITOR_NAME);
        verify(visitorConverter).convert(visitor);
        assertThat(actual).isEqualTo(visitorData);
    }

    @Test
    public void shouldReturnNullWhenVisitorIsNull() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(null);

        VisitorData actual = visitorFacade.getVisitorByName(VISITOR_NAME);

        verify(visitorConverter, never()).convert(any());
        assertThat(actual).isNull();
    }

    @Test
    public void shouldCreateVisitor() {
        when(visitorService.createVisitor(VISITOR_NAME)).thenReturn(visitor);

        VisitorData actual = visitorFacade.createVisitor(VISITOR_NAME);
        
        verify(visitorService).createVisitor(VISITOR_NAME);
        verify(visitorConverter).convert(visitor);
        assertThat(actual).isEqualTo(visitorData);
    }
}