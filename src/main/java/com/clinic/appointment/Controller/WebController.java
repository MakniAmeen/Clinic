package com.clinic.appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.clinic.appointment.Model.*;
import com.clinic.appointment.Dao.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @GetMapping
    public String index(Model model) {
        long doctorCount = doctorRepository.count();
        long clientCount = clientRepository.count();
        long appointmentCount = appointmentRepository.count();

        model.addAttribute("doctorCount", doctorCount);
        model.addAttribute("clientCount", clientCount);
        model.addAttribute("appointmentCount", appointmentCount);

        return "index";
    }


    @GetMapping("/doctors")
    public String doctorsList(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "doctors/list";
    }

    @GetMapping("/doctors/create")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/create";
    }

    @PostMapping("/doctors")
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{id}")
    public String editDoctorForm(@PathVariable Long id, Model model) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            return "doctors/edit";
        }
        return "redirect:/doctors";
    }

    @PostMapping("/doctors/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }


    @GetMapping("/clients")
    public String clientsList(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients/list";
    }

    @GetMapping("/clients/create")
    public String createClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/create";
    }

    @PostMapping("/clients")
    public String createClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "clients/edit";
        }
        return "redirect:/clients";
    }

    @PostMapping("/clients/update")
    public String updateClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }


    @GetMapping("/appointments")
    public String appointmentsList(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<Doctor> doctors = doctorRepository.findAll();
        List<Client> clients = clientRepository.findAll();

        model.addAttribute("appointments", appointments);
        model.addAttribute("doctors", doctors);
        model.addAttribute("clients", clients);

        return "appointments/list";
    }

    @GetMapping("/appointments/create")
    public String createAppointmentForm(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        List<Client> clients = clientRepository.findAll();

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctors);
        model.addAttribute("clients", clients);

        return "appointments/create";
    }

    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointmentForm(@PathVariable Long id, Model model) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        List<Doctor> doctors = doctorRepository.findAll();
        List<Client> clients = clientRepository.findAll();

        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            model.addAttribute("doctors", doctors);
            model.addAttribute("clients", clients);
            return "appointments/edit";
        }
        return "redirect:/appointments";
    }

    @PostMapping("/appointments/update")
    public String updateAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }


    @GetMapping("/test")
    public String test() {
        return "simple-test";
    }

    @GetMapping("/admin/apis")
    public String apiManagement() {
        return "admin/apis";
    }
}