package Services;

import DAO.BookingDAO;
import Entites.BookingEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookingImpl implements BookingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(BookingEntity bookingEntity) {
     entityManager.getTransaction().begin();
     entityManager.persist(bookingEntity);
     entityManager.getTransaction().commit();
    }

    @Override
    public Optional<BookingEntity> findById(Long id) {
        BookingEntity bookingEntity = entityManager.find(BookingEntity.class, id);
        return Optional.ofNullable(bookingEntity);
    }

    @Override
    public List<BookingEntity> findAll() {
        TypedQuery<BookingEntity> query = entityManager.createQuery(
                "SELECT b FROM BookingEntity b", BookingEntity.class);
        return query.getResultList();
    }

    @Override
    public void update(BookingEntity bookingEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(bookingEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        BookingEntity bookingEntity = findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        entityManager.remove(bookingEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<BookingEntity> findByType(String type) {
        TypedQuery<BookingEntity> query = entityManager.createQuery(
                "SELECT b FROM BookingEntity b WHERE b.type = :type", BookingEntity.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<BookingEntity> findByAvailability(Boolean isAvailable) {
        TypedQuery<BookingEntity> query = entityManager.createQuery(
                "SELECT b FROM BookingEntity b WHERE b.isAvailable = :isAvailable", BookingEntity.class);
        query.setParameter("isAvailable", isAvailable);
        return query.getResultList();
    }

    @Override
    public List<BookingEntity> findByPriceRange(Double minPrice, Double maxPrice) {
        TypedQuery<BookingEntity> query = entityManager.createQuery(
                "SELECT b FROM BookingEntity b WHERE b.price BETWEEN :minPrice AND :maxPrice",
                BookingEntity.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

    @Override
    public List<BookingEntity> findRoomsForGuestCount(Integer guestCount) {
        TypedQuery<BookingEntity> query = entityManager.createQuery(
                "SELECT b FROM BookingEntity b WHERE b.guestCount = :guestCount", BookingEntity.class);
        query.setParameter("guestCount", guestCount);
        return query.getResultList();
    }

    @Override
    public Boolean checkAvailability(Long roomId, LocalDate startDate, LocalDate endDate) {
        TypedQuery<Long> query = entityManager.createQuery(
                """
                        SELECT COUNT(b) FROM BookingEntity b WHERE b.room.id = :roomId AND 
                        ((b.checkInDate BETWEEN :startDate AND :endDate) OR (b.checkOutDate BETWEEN :startDate 
                        AND :endDate))""", Long.class);
        query.setParameter("roomId", roomId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Long count = query.getSingleResult();
        return count == 0;
    }
}
