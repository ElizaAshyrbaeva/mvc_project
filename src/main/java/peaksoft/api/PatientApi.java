package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/patients")
public class PatientApi {
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @Autowired
    public PatientApi(PatientService patientService, HospitalService hospitalService) {
        this.patientService = patientService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("patients", patientService.getAll());
        return "patient/mainPage";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("hospitals", hospitalService.getAllHospital());
        return "patient/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newPatient") Patient patient) {
        patientService.save(patient);
        return "redirect:/patients";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        patientService.deleteById(id);
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/update";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("patient") Patient patient) {
        patientService.update(id, patient);
        return "redirect:/patients";
    }
}
