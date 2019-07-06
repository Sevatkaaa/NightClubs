package night.clubs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    private String name;

    @ManyToMany(mappedBy = "visitors")
    private List<NightClub> clubsVisited;

    public Visitor() {
    }

    public Visitor(String name) {
        this.name = name;
        clubsVisited = new ArrayList<>();
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
