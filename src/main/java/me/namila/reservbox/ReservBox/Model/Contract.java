package me.namila.reservbox.ReservBox.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "contract")  //naming a table
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) //addig auto updating time stamps
public class Contract {
    //Auto generated primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "start_date")
    @DateTimeFormat
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat
    private Date endDate;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    //not null foreign key with 1to1, join column mean this is the owner of the relationship
//    @MapsId
//    @Column(name = "hotel_id")
//    @OneToOne
//    @JoinColumn
//    private Hotel hotel;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    //for JPA
    protected Contract() {
    }

    //for java functions
    public Contract( Date startDate, Date endDate, List<Room> room )
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rooms = room;
        room.forEach( x -> x.setContract( this ) );
    }

    public void setRooms(Room room) {
        this.rooms.add(room);
        room.setContract(this);
    }
}
