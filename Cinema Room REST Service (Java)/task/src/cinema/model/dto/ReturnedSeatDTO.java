package cinema.model.dto;

import cinema.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedSeatDTO {
    @JsonProperty("returned_ticket")
    private SeatDTO seat;

    public ReturnedSeatDTO(Seat seat) {
        this.seat = new SeatDTO(seat);
    }

    public ReturnedSeatDTO() {}

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
}
