package me.namila.reservbox.ReservBox.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {

    private String hotelName;
    private String roomType;
    private double roomRate;


}
