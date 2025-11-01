package pattern;

/**
 * [Decorator Pattern]
 * 공간 정보에 부가 서비스를 추가하기 위한 기본 컴포넌트 인터페이스
 */
public interface SpaceComponent {
    String getId();
    String getDescription();
    int getCost();
}
