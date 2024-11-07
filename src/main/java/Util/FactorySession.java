package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactorySession {

    // Поле для хранения и конфигурцации SessionFactory
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Создание конфигурации Hibernate
            Configuration configuration = new Configuration();
            configuration.configure(); // Читает hibernate.cfg.xml
            return configuration.buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // Метод для получения SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Метод для завершения работы с SessionFactory
    public static void shutdown() {
        // Закрытие кэша и соединения
        getSessionFactory();
    }
}
