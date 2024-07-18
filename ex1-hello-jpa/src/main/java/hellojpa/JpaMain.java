package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            team.addMember(member);

            em.flush();
            em.clear(); // flush()를 하고 clear()를 하면 1차 캐시를 다 날려서 다음부터는 디비에서 조회를 해온다.

            Team findTeam = em.find(Team.class, team.getTeamId()); // 1차캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("==============================");
            System.out.println("members"+ findTeam);
            System.out.println("==============================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
