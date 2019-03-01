package me.namila.reservbox.ReservBox.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Hotel")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) //adding auto updating time stamps
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;


    //ToDo make rooms bidirectional
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Room")
    private List<Room> rooms = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    protected Hotel() {
    }

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }


    public void setRooms(Room room) {
        this.rooms.add(room);
        room.setHotel(this);
    }

}
