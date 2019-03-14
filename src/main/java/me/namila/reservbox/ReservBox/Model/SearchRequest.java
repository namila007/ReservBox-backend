package me.namila.reservbox.ReservBox.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest
{
	private Date startDate;
	private Date endDate;
	private List<RoomRequest> roomRequestList;
}

