package night.clubs.facade;

import night.clubs.converter.NightClubConverter;
import night.clubs.data.NightClubData;
import night.clubs.exception.ClubVisitExistsException;
import night.clubs.exception.VisitorDoesNotExistException;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import night.clubs.service.ClubVisitService;
import night.clubs.service.NightClubService;
import night.clubs.service.VisitorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClubVisitFacadeUnitTest {

    private static final String VISITOR_NAME = "visitor name";
    private static final String CLUB_NAME = "club name";

    @InjectMocks
    private ClubVisitFacade clubVisitFacade = new ClubVisitFacade();
    
    @Mock
    private ClubVisitService clubVisitService;
    @Mock
    private VisitorService visitorService;
    @Mock
    private NightClubService nightClubService;
    @Mock
    private NightClubConverter nightClubConverter;
    
    private Visitor visitor = new Visitor();
    private NightClub nightClub = new NightClub();
    private NightClubData nightClubData = new NightClubData();
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
    }

    @Test
    public void shouldCreateVisitWhenClubAndVisitorExist() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(visitor);
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(nightClub);
        visitor.setClubsVisited(Collections.emptyList());
        
        clubVisitFacade.createVisit(VISITOR_NAME, CLUB_NAME);

        InOrder inOrder = inOrder(visitorService, nightClubService, clubVisitService);
        inOrder.verify(visitorService).getVisitorByName(VISITOR_NAME);
        inOrder.verify(nightClubService).getNightClubByName(CLUB_NAME);
        inOrder.verify(clubVisitService).createVisit(visitor, nightClub);
    }

    @Test
    public void shouldThrowExceptionWhenClubAndVisitorExistAndVisitExists() {
        expectedException.expect(ClubVisitExistsException.class);
        expectedException.expectMessage("User already visited night club");
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(visitor);
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(nightClub);
        visitor.setClubsVisited(Collections.singletonList(nightClub));

        clubVisitFacade.createVisit(VISITOR_NAME, CLUB_NAME);
    }
    
    @Test
    public void shouldCreateVisitWithNewVisitorWhenClubExists() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(null);
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(nightClub);
        when(visitorService.createVisitor(VISITOR_NAME)).thenReturn(visitor);

        clubVisitFacade.createVisit(VISITOR_NAME, CLUB_NAME);

        InOrder inOrder = inOrder(visitorService, nightClubService, clubVisitService);
        inOrder.verify(visitorService).getVisitorByName(VISITOR_NAME);
        inOrder.verify(nightClubService).getNightClubByName(CLUB_NAME);
        inOrder.verify(visitorService).createVisitor(VISITOR_NAME);
        inOrder.verify(clubVisitService).createVisit(visitor, nightClub);
    }

    @Test
    public void shouldCreateVisitWithNewClubWhenVisitorExists() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(visitor);
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(null);
        when(nightClubService.createNightClub(CLUB_NAME)).thenReturn(nightClub);

        clubVisitFacade.createVisit(VISITOR_NAME, CLUB_NAME);

        InOrder inOrder = inOrder(visitorService, nightClubService, clubVisitService);
        inOrder.verify(visitorService).getVisitorByName(VISITOR_NAME);
        inOrder.verify(nightClubService).getNightClubByName(CLUB_NAME);
        inOrder.verify(nightClubService).createNightClub(CLUB_NAME);
        inOrder.verify(clubVisitService).createVisit(visitor, nightClub);
    }

    @Test
    public void shouldCreateVisitWithNewClubAndNewVisitor() {
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(null);
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(null);
        when(visitorService.createVisitor(VISITOR_NAME)).thenReturn(visitor);
        when(nightClubService.createNightClub(CLUB_NAME)).thenReturn(nightClub);

        clubVisitFacade.createVisit(VISITOR_NAME, CLUB_NAME);

        InOrder inOrder = inOrder(visitorService, nightClubService, clubVisitService);
        inOrder.verify(visitorService).getVisitorByName(VISITOR_NAME);
        inOrder.verify(nightClubService).getNightClubByName(CLUB_NAME);
        inOrder.verify(visitorService).createVisitor(VISITOR_NAME);
        inOrder.verify(nightClubService).createNightClub(CLUB_NAME);
        inOrder.verify(clubVisitService).createVisit(visitor, nightClub);
    }

    @Test
    public void shouldGetClubsNotVisitedByVisitorWithName() {
        List<NightClub> expected = Collections.singletonList(nightClub);
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(visitor);
        when(clubVisitService.getClubsNotVisitedByVisitor(visitor)).thenReturn(expected);
        when(nightClubConverter.convertAll(expected)).thenReturn(Collections.singletonList(nightClubData));

        List<NightClubData> actual = clubVisitFacade.getClubsNotVisitedByVisitorWithName(VISITOR_NAME);
        
        assertThat(actual).hasSize(1).contains(nightClubData);
    }

    @Test
    public void shouldThrowExceptionWhenVisitorIsNull() {
        expectedException.expect(VisitorDoesNotExistException.class);
        expectedException.expectMessage("Visitor with such name does not exist");
        when(visitorService.getVisitorByName(VISITOR_NAME)).thenReturn(null);
        
        clubVisitFacade.getClubsNotVisitedByVisitorWithName(VISITOR_NAME);
    }
}
