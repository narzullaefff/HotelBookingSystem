package Services;

import DAO.UserDAO;
import Entites.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserImpl implements UserDAO {

    // Внедрение EntityManager для работы с базой данных
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(UserEntity user) {
        // Начинаем транзакцию для сохранение нового пользователя
        entityManager.getTransaction().begin();
        // Сохранаем пользователя в базу данных
        entityManager.persist(user);
        // Завершаем транзакцию, чтобы изменения были применены
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        // Находим пользователя по его уникальному идентификатору
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        // Возвращаем пользователя, обернутого в Optional что бы не получит NullPointerException
        return Optional.ofNullable(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        // Создаем JPQL запрос для выборки всех пользователей
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class);
        // Возвращаем список всех найденных пользователей
        return query.getResultList();
    }

    @Override
    public void update(UserEntity user) {
        // Начинаем транзакцию для обновления информацию о пользователе
        entityManager.getTransaction().begin();
        // Обновляем пользователя в базе данных
        entityManager.merge(user);
        // Завершаем транзакцию, чтобы изменения были применены
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        // Начинаем транзакцию для удаление пользователя
        entityManager.getTransaction().begin();
        // Находим пользователя по идинтификатору выбрасываем исключение если пользователь не найден с таким ID
        UserEntity userEntity = findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        // Удаляем пользователя в базе данных
        entityManager.remove(userEntity);
        // Заверщаем транзакцию, чтобы изменения были применены
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        // Создаем JPQL запрос для поиска пользователя по имени пользователя
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.name = :username", UserEntity.class);
        // Устанавливаем параметр запроса
        query.setParameter("username", username);
        // Возвращаем первого найденного пользователя если он существует
        return query.getResultStream().findFirst();
    }
}
