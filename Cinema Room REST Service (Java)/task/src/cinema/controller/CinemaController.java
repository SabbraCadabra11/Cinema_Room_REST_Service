package cinema.controller;

import cinema.AppConstants;
import cinema.exceptions.UnauthorizedAccessException;
import cinema.model.*;
import cinema.model.dto.ReturnedSeatDTO;
import cinema.model.dto.TicketDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {
    private final SeatManager seatManager;
    private final TicketManager ticketManager;
    private final ObjectMapper objectMapper;

    @Autowired
    public CinemaController(SeatManager seatManager, TicketManager ticketManager){
        this.seatManager = seatManager;
        this.ticketManager = ticketManager;
        objectMapper = new ObjectMapper();
    }

    @GetMapping("/seats")
    public ResponseEntity<Object> getAvailableSeats() throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        response.put("total_rows", AppConstants.TOTAL_ROWS);
        response.put("total_columns", AppConstants.TOTAL_COLUMNS);
        response.put("available_seats", seatManager.getAvailableSeats());
        //String responseJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody Seat seat) throws JsonProcessingException {
        if (seat.getRow() < 1 || seat.getRow() > AppConstants.TOTAL_ROWS ||
                seat.getColumn() < 1 || seat.getColumn() > AppConstants.TOTAL_COLUMNS){
            Map<String, String> response = new HashMap<>();
            response.put("error", "The number of a row or a column is out of bounds!");
            //String responseJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return ResponseEntity.badRequest().body(response);
        }

        if (!seatManager.getSeat(seat.getRow(), seat.getColumn()).isAvailable()){
            Map<String, String> response = new HashMap<>();
            response.put("error", "The ticket has been already purchased!");
            //String responseJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return ResponseEntity.badRequest().body(response);
        }

        Ticket ticket = ticketManager.purchaseTicket(seatManager.getSeat(seat.getRow(), seat.getColumn()));
        TicketDTO ticketDTO = new TicketDTO(ticket);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(ticketDTO);
        return ResponseEntity.ok().body(json);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundTicket(@RequestBody RequestToken requestToken) throws JsonProcessingException {
        UUID token = requestToken.getToken();
        if (!ticketManager.containsTicket(token)){
            Map<String, String> response = new HashMap<>();
            response.put("error", "Wrong token!");
            return ResponseEntity.badRequest().body(response);
        }
        ReturnedSeatDTO seatDTO = new ReturnedSeatDTO(ticketManager.refundTicket(token));
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(seatDTO);
        return ResponseEntity.ok().body(json);
    }

    @GetMapping("/stats")
    public CinemaStats cinemaStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new UnauthorizedAccessException("The password is wrong!");
        }

        return new CinemaStats(ticketManager.currentIncome(),
                                seatManager.availableSeatsCount(),
                                ticketManager.purchasedTickets());
    }
}












