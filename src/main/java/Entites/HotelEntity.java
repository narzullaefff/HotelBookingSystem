package Entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "rating")
    private String rating;

    @Column(name = "rooms_description")
    private String roomsDescription;

    @OneToMany(mappedBy = "hotel")
    private List<RoomEntity> rooms;

    public HotelEntity() {
    }

    public HotelEntity(String name, String address, String rating, String roomsDescription) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.roomsDescription = roomsDescription;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public String getRoomsDescription() {
        return roomsDescription;
    }

    public List<RoomEntity> getRooms() {
        return  rooms;
    }

    public void setLong(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRoomsDescription(String roomsDescription) {
        this.roomsDescription = roomsDescription;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
}
