
package com.example;
import lombok.Data;

@Data
public class FormReservation {
    private String id;
    private String clientFullName;
    private String roomNumber;
    private String startDate;
    private String endDate;
}
