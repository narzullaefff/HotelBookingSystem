package Services;

import DAO.HotelDAO;
import Entites.HotelEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class HotelImpl implements HotelDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(HotelEntity hotelEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(hotelEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<HotelEntity> findById(Long id) {
        HotelEntity hotelEntity = entityManager.find(HotelEntity.class, id);
        return Optional.ofNullable(hotelEntity);
    }

    @Override
    public List<HotelEntity> findAll() {
        TypedQuery<HotelEntity> query = entityManager.createQuery(
                "SELECT h FROM HotelEntity h", HotelEntity.class);
        return query.getResultList();
    }

    @Override
    public void update(HotelEntity hotelEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(hotelEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        HotelEntity hotelEntity = findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        entityManager.remove(hotelEntity);
        entityManager.getTransaction().commit();
    }
}
