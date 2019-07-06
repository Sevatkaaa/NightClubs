package night.clubs.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class NightClub {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private List<Visitor> visitors;

    public NightClub(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
