package me.namila.reservbox.ReservBox.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "SearchResultMapping",
        entities = @EntityResult(
                entityClass = SearchResult.class,
                fields = {
                        @FieldResult(name = "hotelName", column = "name"),
                        @FieldResult(name = "roomType", column = "room_type"),
                        @FieldResult(name = "roomRate", column = "room_rate")}))

public class SearchResult {

    private String hotelName;
    private String roomType;
    private double roomRate;


}
