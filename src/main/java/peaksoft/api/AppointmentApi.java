package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.PatientService;


@Controller
@RequestMapping("/appointments")
public class AppointmentApi {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DepartmentService departmentService;

    public AppointmentApi(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, DepartmentService departmentService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }
    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable Long hospitalId, Model model){
        model.addAttribute("appointments", appointmentService.getAll(hospitalId));
        return "appointment/mainPage";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model){
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAll(hospitalId));
        model.addAttribute("patients", patientService.getAll(hospitalId));
        model.addAttribute("departments", departmentService.getAll(hospitalId));
        model.addAttribute(hospitalId);
        return "appointment/savePage";
    }

    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable Long hospitalId, @ModelAttribute("newAppointment") Appointment appointment){
        appointmentService.save(hospitalId, appointment);
        return "redirect:/appointments/"+hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String delete(@PathVariable Long hospitalId,@PathVariable Long appointmentId,
                         @ModelAttribute("newAppointment") Appointment appointment){
        appointmentService.deleteById(appointmentId,hospitalId);
        return "redirect:/appointments/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{appointmentId}/edit")
    public String edit(@PathVariable("appointmentId")Long id, @PathVariable("hospitalId")Long hospitalId, Model model){
        model.addAttribute("appointment",appointmentService.findById(id));
        model.addAttribute("departments",departmentService.getAll(hospitalId));
        model.addAttribute("doctors",doctorService.getAll(hospitalId));
        model.addAttribute("patients",patientService.getAll(hospitalId));
        model.addAttribute("hospitalId", hospitalId);
        return "appointment/edit";
    }
    @PutMapping("/{hospitalId}/{appointmentId}/update")
    public String update(@PathVariable("hospitalId")Long hospitalId,@PathVariable("appointmentId")Long appointmentId,@ModelAttribute("appointment") Appointment appointment){
        appointmentService.update(appointmentId,appointment);
        return "redirect:/appointments/"+hospitalId;
    }


}