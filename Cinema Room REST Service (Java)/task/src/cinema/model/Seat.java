package cinema.model;

import cinema.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean available;

    public Seat(){}

    public Seat(int row, int column, boolean available) {
        this.row = row;
        this.column = column;
        this.available = available;
        this.price = row < 5 ? AppConstants.SEAT_PREMIUM_PRICE : AppConstants.SEAT_REGULAR_PRICE;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "\"row\":" + row +
                "\n\"column\":" + column +
                "\n\"price\":" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column && available == seat.available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, available);
    }
}
