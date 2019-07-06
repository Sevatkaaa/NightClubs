package night.clubs.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Visitor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private List<NightClub> clubsVisited;

    public Visitor(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NightClub> getClubsVisited() {
        return clubsVisited;
    }

    public void setClubsVisited(List<NightClub> clubsVisited) {
        this.clubsVisited = clubsVisited;
    }
}
