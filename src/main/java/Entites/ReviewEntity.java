package Entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private String rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id") // Внешний ключ для связи с User
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "hotel_id") // Внещний ключ для связи с Hotel
    private HotelEntity hotel;

    public ReviewEntity() {
    }

    public ReviewEntity(String rating, String comment, LocalDate date) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public UserEntity getUser() {
        return user;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }
}
