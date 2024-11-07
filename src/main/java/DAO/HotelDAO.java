package DAO;

import Entites.HotelEntity;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {

    void save(HotelEntity hotelEntity);
    Optional<HotelEntity> findById(Long id);
    List<HotelEntity> findAll();
    void update(HotelEntity hotelEntity);
    void deleteById(Long id);


}
