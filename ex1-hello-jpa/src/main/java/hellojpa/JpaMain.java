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
            // 객체를 생성한 상태 (비영속)
            Member member = new Member(1L, "helloJPA");

            System.out.println("===BEFORE===");

            // 로그를 확인해보면 비포와 애프터 사이에 insert 쿼리가 날라가지 않는다.
            // 아직 DB에 적용되지 않은 상태.
            em.persist(member);

            System.out.println("===AFTER===");

            // 트랜잭션을 커밋하는 순간 디비에 적용되는 쿼리가 날라간다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
