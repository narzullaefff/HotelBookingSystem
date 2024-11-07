package Entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_size")
    private Double roomSize;

    @Column(name = "floor")
    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "hotel_id") // Внешний ключ для связи с Hotel
    private HotelEntity hotel;

    public RoomEntity() {
    }

    public RoomEntity(String roomNumber, String roomType, Double roomSize, Integer floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floor = floor;
        this.roomSize = roomSize;
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public Double getRoomSize() {
        return roomSize;
    }

    public Integer getFloor() {
        return floor;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomSize(Double roomSize) {
        this.roomSize = roomSize;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }
}
