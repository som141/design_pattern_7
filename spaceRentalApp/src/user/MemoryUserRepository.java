package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 사용자 정보를 메모리에 저장하고 관리하는 저장소
 */
public class MemoryUserRepository {
    private static final Map<Long, User> store = new ConcurrentHashMap<>();

    public void save(User user) {       // 사용자 저장 로직
        store.put(user.getUserId(), user);
    }

    public User findById(Long id) {     // ID로 사용자 조회 로직
        return store.get(id);
    }

    public List<User> findAll() {       // 모든 사용자 조회 로직
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {   // ID로 사용자 삭제 로직
        store.remove(id);
    }

    public boolean updateById(Long id, User updateUser) {       // 사용자 정보 업데이트 로직
        if(store.containsKey(id)){      // 해당 사용자 존재하는 경우
            store.put(id, updateUser);
            return true;
        }
        return false;
    }
}
