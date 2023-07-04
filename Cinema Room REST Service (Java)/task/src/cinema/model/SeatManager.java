package cinema.model;

import cinema.AppConstants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SeatManager {

    private final Map<String, Seat> seats;

    private SeatManager() {
        this.seats = new HashMap<>();
        for (int i = 1; i <= AppConstants.TOTAL_ROWS; i++){
            for (int j = 1; j <= AppConstants.TOTAL_COLUMNS; j++) {
                String seatKey = i + "-" + j;
                seats.put(seatKey, new Seat(i, j, true));
            }
        }
    }

    public List<Seat> getAllSeats(){
        return seats.values().stream().toList();
    }

    public List<Seat> getAvailableSeats() {
        return getAllSeats().stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Seat> getTakenSeats() {
        return getAllSeats().stream()
                .filter(seat -> !seat.isAvailable())
                .collect(Collectors.toList());
    }

    public Seat getSeat(int row, int column){
        String seatKey = row + "-" + column;
        return seats.get(seatKey);
    }

    public int availableSeatsCount() {
        return getAvailableSeats().size();
    }

}














