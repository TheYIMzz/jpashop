package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // readOnly = true : 읽기 전용 트랜잭션 최적화
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; // 필드에 final 해놓으면 컴파일 시점에 null인지 확인 가능

    // 회원 가입
    @Transactional // 클래스에 어노테이션보다 메서드에 따로 설정한 어노테이션이 우선권있음, 쓰기 기능이라 따로 추가
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 1건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
