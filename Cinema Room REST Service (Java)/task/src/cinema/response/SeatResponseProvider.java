package cinema.response;

import cinema.model.SeatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatResponseProvider {

    @Autowired
    private SeatManager seatManager;


}
