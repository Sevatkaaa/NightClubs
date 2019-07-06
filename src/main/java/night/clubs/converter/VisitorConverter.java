package night.clubs.converter;

import night.clubs.data.VisitorData;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitorConverter {
    public VisitorData convert(Visitor source) {
        VisitorData target = new VisitorData();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setClubsVisited(source.getClubsVisited().stream()
                .map(NightClub::getName)
                .collect(Collectors.toList()));
        return target;
    }

    public List<VisitorData> convertAll(List<Visitor> visitors) {
        return visitors.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
