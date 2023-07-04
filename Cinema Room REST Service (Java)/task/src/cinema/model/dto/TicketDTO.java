package cinema.model.dto;

import cinema.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TicketDTO {
    private UUID token;
    @JsonProperty("ticket")
    private SeatDTO seat;

    public TicketDTO(Ticket ticket) {
        this.token = ticket.getToken();
        this.seat = new SeatDTO(ticket.getSeat());
    }

    public TicketDTO() {}

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
}
