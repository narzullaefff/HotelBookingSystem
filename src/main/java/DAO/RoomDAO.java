package DAO;

import Entites.RoomEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomDAO {

    // Базовые CRUD операции
    void save(RoomEntity roomEntity);
    Optional<RoomEntity> findById(Long id);
    List<RoomEntity> findAll();
    void update(RoomEntity roomEntity);
    void deleteById(Long id);

    // Методы поиска по параметрам
    List<RoomEntity> findByType(String type);
    List<RoomEntity> findByFloor(Integer floor);
    List<RoomEntity> findByHotelId(Long hotelId);
    List<RoomEntity> findBySize(Double minSize, Double maxSize);


}
