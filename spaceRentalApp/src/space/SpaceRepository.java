package space;

import java.util.List;
import java.util.ArrayList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 공간 정보를 메모리에 저장하고 관리하는 저장소
 */
public class SpaceRepository {
    private static final Map<Long, Space> store = new ConcurrentHashMap<>();
    // private static final AtomicLong seq = new AtomicLong(1L);

    public Long save(Space space) {
        Long id = space.getId();
        // if (id == null) {
        //     id = seq.getAndIncrement();
        //     space.setId(id);              // ← 세터 사용, 리플렉션 불필요
        // }
        store.put(id, space);
        return id;
    }
    public Optional<Space> findById(Long id) { return Optional.of(store.get(id)); }
    public List<Space> findAll() { return new ArrayList<>(store.values()); }
    public void deleteById(Long id) { /* ID로 공간 삭제 로직 */ }
    public boolean updateById(Long id, Space updateSpace) { /* 공간 정보 업데이트 로직 */ return false; }
}
