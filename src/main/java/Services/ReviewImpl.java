package Services;

import DAO.ReviewDAO;
import Entites.ReviewEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ReviewImpl implements ReviewDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(ReviewEntity reviewEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(reviewEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<ReviewEntity> findById(Long id) {
        ReviewEntity reviewEntity = entityManager.find(ReviewEntity.class, id);
        return Optional.ofNullable(reviewEntity);
    }

    @Override
    public List<ReviewEntity> findAll() {
        TypedQuery<ReviewEntity> query = entityManager.createQuery(
                "SELECT r FROM ReviewEntity r", ReviewEntity.class);
        return query.getResultList();
    }

    @Override
    public void update(ReviewEntity reviewEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(reviewEntity);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        ReviewEntity reviewEntity = findById(id).orElseThrow(() -> new EntityNotFoundException("Review not found"));
        entityManager.remove(reviewEntity);
        entityManager.getTransaction().commit();
    }
}
