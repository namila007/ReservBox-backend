package me.namila.reservbox.ReservBox.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Room")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) //adding auto updating time stamps
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "max_adults")
    private int maxAdults;

    @Column(name = "room_rate")
    private double roomRate;

    @Column(name = "no_of_rooms")
    private int noOfRooms;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    protected Room() {
    }


    public Room(String roomType, double roomRate, int noOfRooms, int maxAdults, Contract contract) {
        this.roomRate = roomRate;
        this.roomType = roomType;
        this.noOfRooms = noOfRooms;
        this.maxAdults = maxAdults;
        this.contract = contract;

    }

    public void setContract( Contract contract )
    {
        this.contract = contract;
    }

}
