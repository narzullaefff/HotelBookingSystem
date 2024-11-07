package Services;

import DAO.RoomDAO;
import Entites.RoomEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RoomImpl implements RoomDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(RoomEntity roomEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(roomEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<RoomEntity> findById(Long id) {
        RoomEntity roomEntity = entityManager.find(RoomEntity.class, id);
        return Optional.ofNullable(roomEntity);
    }

    @Override
    public List<RoomEntity> findAll() {
        TypedQuery<RoomEntity> query = entityManager.createQuery(
                "SELECT r FROM RoomEntity r", RoomEntity.class);
        return query.getResultList();
    }

    @Override
    public void update(RoomEntity roomEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(roomEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        RoomEntity roomEntity = findById(id).orElseThrow((
        ) -> new EntityNotFoundException("Room с таким ID не найден"));
        entityManager.remove(roomEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<RoomEntity> findByType(String type) {
        TypedQuery<RoomEntity> query = entityManager.createQuery(
                "SELECT r FROM RoomEntity r WHERE r.roomType = :type", RoomEntity.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<RoomEntity> findByFloor(Integer floor) {
        TypedQuery<RoomEntity> query = entityManager.createQuery(
                "SELECT r FROM RoomEntity  r WHERE r.floor = :floor", RoomEntity.class);
        query.setParameter("floor", floor);
        return query.getResultList();
    }

    @Override
    public List<RoomEntity> findByHotelId(Long hotelId) {
        TypedQuery<RoomEntity> query = entityManager.createQuery(
                "SELECT r FROM RoomEntity  r WHERE r.hotel.id = :hotelId", RoomEntity.class);
        query.setParameter("hotelId", hotelId);
        return query.getResultList();
    }

    @Override
    public List<RoomEntity> findBySize(Double minSize, Double maxSize) {
        TypedQuery<RoomEntity> query = entityManager.createQuery(
                "SELECT r FROM RoomEntity r WHERE r.roomSize BETWEEN :minSize AND :maxSize", RoomEntity.class);
        query.setParameter("minSize", minSize);
        query.setParameter("maxSize", maxSize);
        return query.getResultList();
    }
}
