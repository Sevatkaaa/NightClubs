package night.clubs.controller;

import night.clubs.data.NightClubData;
import night.clubs.facade.ClubVisitFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClubVisitControllerUnitTest {

    private static final String VISITOR_NAME = "visitor";
    private static final String CLUB_NAME = "club";
    
    @InjectMocks
    private ClubVisitController clubVisitController = new ClubVisitController();

    @Mock
    private ClubVisitFacade clubVisitFacade;

    @Test
    public void shouldCreateVisit() {
        
        clubVisitController.createVisit(VISITOR_NAME, CLUB_NAME);
        
        verify(clubVisitFacade).createVisit(VISITOR_NAME, CLUB_NAME);
    }

    @Test
    public void shouldGetClubsNotVisitedByVisitorWithName() {
        List<NightClubData> clubs = new ArrayList<>();
        when(clubVisitFacade.getClubsNotVisitedByVisitorWithName(VISITOR_NAME)).thenReturn(clubs);
        
        List<NightClubData> actual = clubVisitController.getClubsNotVisitedByVisitorWithName(VISITOR_NAME);
        
        assertThat(actual).isEqualTo(clubs);
    }
}
