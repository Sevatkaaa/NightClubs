package night.clubs.converter;

import night.clubs.data.VisitorData;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import org.junit.Test;

import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;

public class VisitorConverterUnitTest {

    private static final String VISITOR_NAME = "visitor";
    private static final String CLUB_NAME = "club";

    private VisitorConverter visitorConverter = new VisitorConverter();
    
    private Visitor visitor = new Visitor(VISITOR_NAME);

    @Test
    public void shouldConvert() {
        visitor.setClubsVisited(Collections.singletonList(new NightClub(CLUB_NAME)));

        VisitorData actual = visitorConverter.convert(visitor);
        
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo(VISITOR_NAME);
        assertThat(actual.getClubsVisited()).hasSize(1).contains(CLUB_NAME);
    }
}
