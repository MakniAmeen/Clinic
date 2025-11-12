package com.clinic.appointment.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String status;

    public Appointment() {}

    public Appointment(LocalDateTime dateTime, Doctor doctor, Client client, String status) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.client = client;
        this.status = status;
    }


    public Long getId() { return id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public Doctor getDoctor() { return doctor; }
    public Client getClient() { return client; }
    public String getStatus() { return status; }


    public void setId(Long id) { this.id = id; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setClient(Client client) { this.client = client; }
    public void setStatus(String status) { this.status = status; }
}