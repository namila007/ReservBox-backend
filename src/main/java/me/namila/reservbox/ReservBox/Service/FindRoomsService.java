package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.SearchRequest;
import me.namila.reservbox.ReservBox.Model.SearchResult;

import java.util.Date;
import java.util.List;

public interface FindRoomsService
{
	List<SearchResult> findRooms( int noOfRooms, int noOfAdults, Date StartDate, Date EndDate );

	List<SearchResult> findRoomsbyList( SearchRequest searchRequest );
}
