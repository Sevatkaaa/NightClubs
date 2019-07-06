package night.clubs.converter;

import night.clubs.data.NightClubData;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class NightClubConverterUnitTest {

    private static final String VISITOR_NAME = "visitor";
    private static final String CLUB_NAME_1 = "club1";
    private static final String CLUB_NAME_2 = "club2";

    private NightClubConverter nightClubConverter = new NightClubConverter();

    private NightClub nightClub1;
    private NightClub nightClub2;
    
    @Before
    public void setUp() {
        nightClub1 = new NightClub(CLUB_NAME_1);
        nightClub2 = new NightClub(CLUB_NAME_2);
        nightClub2.setVisitors(Collections.singletonList(new Visitor(VISITOR_NAME)));
    }

    @Test
    public void shouldConvertWithEmptyVisitors() {

        NightClubData actual = nightClubConverter.convert(nightClub1);

        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo(CLUB_NAME_1);
        assertThat(actual.getVisitors()).isEmpty();
    }

    @Test
    public void shouldConvertWithVisitors() {

        NightClubData actual = nightClubConverter.convert(nightClub2);

        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo(CLUB_NAME_2);
        assertThat(actual.getVisitors()).hasSize(1).contains(VISITOR_NAME);
    }

    @Test
    public void shouldConvertAll() {

        List<NightClubData> actual = nightClubConverter.convertAll(Arrays.asList(nightClub1, nightClub2));

        NightClubData data1 = actual.get(0);
        NightClubData data2 = actual.get(1);
        assertThat(data1.getName()).isEqualTo(CLUB_NAME_1);
        assertThat(data1.getVisitors()).isEmpty();
        assertThat(data2.getName()).isEqualTo(CLUB_NAME_2);
        assertThat(data2.getVisitors()).hasSize(1).contains(VISITOR_NAME);
    }
}