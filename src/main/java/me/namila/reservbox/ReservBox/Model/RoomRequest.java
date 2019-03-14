package me.namila.reservbox.ReservBox.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest
{
	private int rooms;
	private int maxAdults;
}
