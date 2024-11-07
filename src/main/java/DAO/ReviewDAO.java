package DAO;

import Entites.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO {

    void save(ReviewEntity reviewEntity);
    Optional<ReviewEntity> findById(Long id);
    List<ReviewEntity> findAll();
    void update(ReviewEntity reviewEntity);
    void deleteById(Long id);


}
