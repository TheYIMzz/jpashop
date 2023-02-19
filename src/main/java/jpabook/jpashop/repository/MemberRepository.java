package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//  @PersistenceContext => 엔티티 매니저는 @PersistenceContext가 있어야 인젝션이 되지만 스프링 부트 dataJPA 라이브러리는 @autowired 지원 해줌
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    // 글 한 건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 글 목록 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
