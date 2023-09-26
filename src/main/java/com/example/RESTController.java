package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.Reservation;
import com.example.FormReservation;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Controller
public class RESTController {
    private List<Reservation> reservations = new ArrayList<>();
    
    @GetMapping("/")
    public String startup(Model model){
        model.addAttribute("reservations", reservations);
        FormReservation formReservation = new FormReservation();
        model.addAttribute("formReservation", formReservation);
        return "index";
    }
    
    @PostMapping("/makeReservation")
    public String makeReservation(FormReservation form){
        String clientName = form.getClientFullName();
        String roomNumber = form.getRoomNumber();
        String[] d1 = form.getStartDate().split("-");
        String[] d2 = form.getEndDate().split("-");
        LocalDate startDate = LocalDate.of(Integer.parseInt(d1[0]),Integer.parseInt(d1[1]),Integer.parseInt(d1[2]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(d2[0]),Integer.parseInt(d2[1]),Integer.parseInt(d2[2]));
        List<LocalDate> dates = new ArrayList();
        dates.add(startDate);
        dates.add(endDate);
        Reservation r = new Reservation();
        r.setId(reservations.size()+1);
        r.setClientFullName(clientName);
        r.setRoomNumber(Integer.parseInt(roomNumber));
        r.setReservationDates(dates);
        reservations.add(r);
        return "redirect:/";
    }
    
    @PostMapping("/updateReservation")
    public String updateReservation(FormReservation form){
        int id = Integer.parseInt(form.getId());
        int i;
        Reservation a;
        boolean found = false;
        for(i=0;i<reservations.size();i++){
            a = (Reservation)reservations.get(i);
            if(a.getId()==id){
                found = true;
                reservations.remove(i);
                break;
            }
        }
        if(found){
            String clientName = form.getClientFullName();
            String roomNumber = form.getRoomNumber();
            String[] d1 = form.getStartDate().split("-");
            String[] d2 = form.getEndDate().split("-");
            LocalDate startDate = LocalDate.of(Integer.parseInt(d1[0]),Integer.parseInt(d1[1]),Integer.parseInt(d1[2]));
            LocalDate endDate = LocalDate.of(Integer.parseInt(d2[0]),Integer.parseInt(d2[1]),Integer.parseInt(d2[2]));
            List<LocalDate> dates = new ArrayList();
            dates.add(startDate);
            dates.add(endDate);
            Reservation r = new Reservation();
            r.setId(id);
            r.setClientFullName(clientName);
            r.setRoomNumber(Integer.parseInt(roomNumber));
            r.setReservationDates(dates);
            reservations.add(r);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}
