package space;

public class SpaceService {
    private SpaceRepository spaceRepository;
    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }
    public Long createSpace(Space space) {
        return spaceRepository.save(space);
    }
    public Space getSpace(Long id) {
        return spaceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 장소가 없습니다.!"));
    }


}
