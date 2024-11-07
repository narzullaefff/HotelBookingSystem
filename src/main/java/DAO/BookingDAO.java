package DAO;

import Entites.BookingEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingDAO {

    void save(BookingEntity bookingEntity);
    Optional<BookingEntity> findById(Long id);
    List<BookingEntity> findAll();
    void update(BookingEntity bookingEntity);
    void deleteById(Long id);

    // Поиск по определенным параметрам
    List<BookingEntity> findByType(String type);
    List<BookingEntity> findByAvailability(Boolean isAvailable);
    List<BookingEntity> findByPriceRange(Double minPrice, Double maxPrice);
    List<BookingEntity> findRoomsForGuestCount(Integer guestCount);

    // Проверка доступности комнаты на указаный период
    Boolean checkAvailability(Long roomId, LocalDate startDate, LocalDate endDate);
}
