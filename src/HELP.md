# Краткое описание как работаю сущности в Hibernate точнее как они мапаются с помощью ORM

# One-to-Many (Hotel — Room): Один отель может содержать несколько комнат

# Many-to-One (Booking — Room): Одно бронирование связано с одной комнатой

# Many-to-One (Booking — User): Одно бронирование связано с одним пользователем

# Many-to-One (Review — User): Один пользователь может оставить много отзывов

# Many-to-One (Review — Hotel): Один отель может иметь множество отзывов



# @OneToMany - указывает что один объект связан с несколькими другими
# @ManyToOne - указывает что много объектов связан с одним объектом
# @JoinColumn - указывает какой столбец использовать для связи в базе данных
# mappedBy - "hotel" в Hotel говорит Hibernate "Не добавлчй сюда лишний столбец Свзяь уже указана в поле hotel у Room"
# Такким образом свзяь "один отель - много комнат" хранится в Room и Hotel просто знает комнаты к нему относятся

