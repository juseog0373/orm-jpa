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
            Member member = new Member(200L, "member200");
            em.persist(member);

            // flush 메서드를 사용하면 영속성 컨텍스트에 저장되어있는 쿼리를 바로 디비에 반영하게 된다.
            // (원래는 쓰기 지연 때문에 트랜잭션을 호출할 때 한번에 쓰기 지연 저장소에 있는 sql들이 반영이 이루어지지만 flush로 강제성을 부여할 수 있다.)
            // 영속성 컨텍스트를 비우지 않는다. 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화하는 역할.
            // 트랜잭션이라는 작업 단위가 중요하다. 커밋 직전에만 동기화 하면 된다.
            em.flush();

            System.out.println("================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
