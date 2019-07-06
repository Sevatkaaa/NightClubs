package night.clubs.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class NightClub {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "club_visit",
            joinColumns = { @JoinColumn(name = "club_id") },
            inverseJoinColumns = { @JoinColumn(name = "visitor_id") }
    )
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
