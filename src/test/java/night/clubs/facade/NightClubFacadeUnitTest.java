package night.clubs.facade;

import night.clubs.converter.NightClubConverter;
import night.clubs.data.NightClubData;
import night.clubs.model.NightClub;
import night.clubs.service.NightClubService;
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
public class NightClubFacadeUnitTest {

    private static final String CLUB_NAME = "club";
    
    @InjectMocks
    private NightClubFacade nightClubFacade = new NightClubFacade();
    
    @Mock
    private NightClubService nightClubService;
    @Mock
    private NightClubConverter nightCLubConverter;
    
    private NightClub nightClub = new NightClub();
    private NightClubData nightClubData = new NightClubData();

    @Before
    public void setUp() {
        when(nightCLubConverter.convert(nightClub)).thenReturn(nightClubData);
    }

    @Test
    public void shouldGetNightClubByName() {
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(nightClub);

        NightClubData actual = nightClubFacade.getNightClubByName(CLUB_NAME);

        verify(nightClubService).getNightClubByName(CLUB_NAME);
        verify(nightCLubConverter).convert(nightClub);
        assertThat(actual).isEqualTo(nightClubData);
    }

    @Test
    public void shouldReturnNullWhenNightClubIsNull() {
        when(nightClubService.getNightClubByName(CLUB_NAME)).thenReturn(null);

        NightClubData actual = nightClubFacade.getNightClubByName(CLUB_NAME);

        verify(nightCLubConverter, never()).convert(any());
        assertThat(actual).isNull();
    }

    @Test
    public void shouldCreateNightClub() {
        when(nightClubService.createNightClub(CLUB_NAME)).thenReturn(nightClub);

        NightClubData actual = nightClubFacade.createNightClub(CLUB_NAME);
        
        assertThat(actual).isEqualTo(nightClubData);
    }
}