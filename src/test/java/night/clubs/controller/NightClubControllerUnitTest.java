package night.clubs.controller;

import night.clubs.data.NightClubData;
import night.clubs.facade.NightClubFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NightClubControllerUnitTest {

    private static final String CLUB_NAME = "club";
    
    @InjectMocks
    private NightClubController nightClubController = new NightClubController();
    
    @Mock
    private NightClubFacade nightClubFacade;
    
    private NightClubData nightClubData = new NightClubData();

    @Test
    public void shouldGetNightClubByName() {
        when(nightClubFacade.getNightClubByName(CLUB_NAME)).thenReturn(nightClubData);

        NightClubData actual = nightClubController.getNightClubByName(CLUB_NAME);
        
        assertThat(actual).isEqualTo(nightClubData);
    }

    @Test
    public void shouldCreateNightClub() {
        when(nightClubFacade.createNightClub(CLUB_NAME)).thenReturn(nightClubData);

        NightClubData actual = nightClubController.createNightClub(CLUB_NAME);

        assertThat(actual).isEqualTo(nightClubData);
    }
}
