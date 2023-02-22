package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/doctors")
public class DoctorApi {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorApi(DoctorService doctorService, HospitalService hospitalService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable Long hospitalId,Model model){
        model.addAttribute("doctors",doctorService.getAll(hospitalId));
        return "doctor/doctor";
    }
    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("newDoctor",new Doctor());
        model.addAttribute(hospitalId);
        model.addAttribute("departments",hospitalService.findById(hospitalId));
        return "doctor/newDoctor";
    }
    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDoctor") Doctor doctor ){
        doctorService.save(hospitalId,doctor);
        return"redirect:/doctors/"+hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{doctorId}/delete")
    public String delete(@PathVariable("hospitalId")Long hospitalId,@PathVariable("doctorId")Long doctorId){
        doctorService.delete(doctorId);
        return "redirect:/doctors/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{doctorId}/edit")
    public String edit(@PathVariable("doctorId")Long id,@PathVariable("hospitalId")Long hospitalId, Model model){
        model.addAttribute("doctor",doctorService.findById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "doctor/edit";
    }
    @PutMapping("/{hospitalId}/{doctorId}/update")
    public String update(@PathVariable("hospitalId")Long hospitalId,@PathVariable("doctorId")Long doctorId,@ModelAttribute("doctor")Doctor doctor){
        doctorService.update(doctorId,doctor);
        return "redirect:/doctors/"+hospitalId;
    }


    }