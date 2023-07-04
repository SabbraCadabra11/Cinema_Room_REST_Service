package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class CinemaStats {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public CinemaStats(int currentIncome, int availableSeats, int purchasedTickets) {
        this.currentIncome = currentIncome;
        this.availableSeats = availableSeats;
        this.purchasedTickets = purchasedTickets;
    }

}
