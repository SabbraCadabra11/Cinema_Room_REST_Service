package cinema.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class TicketManager {
    private Map<UUID, Ticket> tickets;

    public TicketManager() {
        this.tickets = new HashMap<>();
    }

    public Ticket purchaseTicket(Seat seat) {
        Ticket ticket = new Ticket(seat);
        ticket.getSeat().setAvailable(false);
        tickets.put(ticket.getToken(), ticket);
        return ticket;
    }

    public Seat refundTicket(UUID token){
        Seat seat = tickets.get(token).getSeat();
        seat.setAvailable(true);
        tickets.remove(token);
        return seat;
    }

    public boolean containsTicket(UUID token) {
        return tickets.containsKey(token);
    }

    public int currentIncome() {
        if (tickets.isEmpty()) {
            return 0;
        }
        int income = 0;
        for (Ticket ticket : tickets.values()){
            income += ticket.getSeat().getPrice();
        }
        return income;
    }

    public int purchasedTickets() {
        return tickets.size();
    }
}
