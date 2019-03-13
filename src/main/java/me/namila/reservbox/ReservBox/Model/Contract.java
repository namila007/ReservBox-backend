package me.namila.reservbox.ReservBox.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "contract")  //naming a table
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) //addig auto updating time stamps
public class Contract implements Serializable {
    //Auto generated primary key
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("startDate")
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @JsonProperty("endDate")
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @JsonProperty("rooms")
    @JsonManagedReference
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();

    //not null foreign key with 1to1, join column mean this is the owner of the relationship
//    @MapsId

    @JsonProperty("hotel")
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

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
    public Contract(Date startDate, Date endDate, List<Room> room, Hotel hotel)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rooms = room;
        room.forEach(x -> x.setContract(this));
        this.hotel = hotel;
        this.hotel.addContract( this );

    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        for (Room room : rooms) {
            room.setContract(this);
        }
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public void setHotel( Hotel hotel )
    {
        this.hotel = hotel;
        hotel.addContract( this );
    }
}
