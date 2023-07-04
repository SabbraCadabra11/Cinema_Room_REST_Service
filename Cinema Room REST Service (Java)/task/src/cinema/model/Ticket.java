package cinema.model;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private UUID token;
    private Seat seat;

    public Ticket(Seat seat) {
        this.token = UUID.randomUUID();
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        return Objects.equals(token, ticket.token) && Objects.equals(seat, ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, seat);
    }
}
