package night.clubs.converter;

import night.clubs.data.NightClubData;
import night.clubs.model.NightClub;
import night.clubs.model.Visitor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NightClubConverter {
    public NightClubData convert(NightClub source) {
        NightClubData target = new NightClubData();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setVisitors(source.getVisitors().stream()
                .map(Visitor::getName)
                .collect(Collectors.toList()));
        return target;
    }

    public List<NightClubData> convertAll(List<NightClub> sources) {
        return sources.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
