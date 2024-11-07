package DAO;

import Entites.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void save(UserEntity user);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findAll();
    void update(UserEntity user);
    void delete(Long id);
    Optional<UserEntity> findByUsername(String username);

}
