package night.clubs.data;

import java.util.List;

public class VisitorData {
    private Integer id;
    private String name;
    private List<String> clubsVisited;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getClubsVisited() {
        return clubsVisited;
    }

    public void setClubsVisited(List<String> clubsVisited) {
        this.clubsVisited = clubsVisited;
    }
}
