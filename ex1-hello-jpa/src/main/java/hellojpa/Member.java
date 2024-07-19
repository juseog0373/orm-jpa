package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name="username")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false) //읽기 전용 필드
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
}
