package testim.httpupload.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import testim.httpupload.domain.User;

import java.util.*;

@Slf4j
@Repository
public class UserRepository {

    private static Map<Long, User> store = new HashMap<>(); //static 사ㅛㅇ
    private static long sequence = 0L;

    public User save(User member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public Optional<User> findByLoginId(String loginId) {
        /*
        List<Member> all = findAll();
        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
        */
        return findAll().stream()
                .filter(u -> u.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
