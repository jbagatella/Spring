package com.example;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import lombok.Data;

@Data
public class Reservation {
    private int id;
    private String clientFullName;
    private int roomNumber;
    private List<LocalDate> reservationDates;
}
