package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.enams.Gender;
import peaksoft.model.Doctor;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientApi {
    private final PatientService patientService;

    @Autowired
    public PatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable("hospitalId")Long hospitalId ,Model model) {
        model.addAttribute("patients", patientService.getAll(hospitalId));
        return "patient/mainPage";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute(hospitalId);
        model.addAttribute("genders", Gender.values());
        return "patient/savePage";
    }

    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable("hospitalId") Long hospitalId,@ModelAttribute("newPatient") Patient patient) {
        patientService.save(hospitalId,patient);
        return "redirect:/patients/"+hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{patientId}/delete")
    public String delete( @PathVariable("hospitalId") Long id,@PathVariable("patientId")Long patientId) {
      patientService.deleteById(patientId);
        return "redirect:/patients/"+id;
    }
    @GetMapping("/{hospitalId}/{patientId}/edit")
    public String edit(Model model, @PathVariable("patientId") Long id,@PathVariable("hospitalId")Long hospitalId) {
        model.addAttribute("patients",patientService.getById(id));
        model.addAttribute("hospitalId", hospitalId);
        return "patient/update";
    }
    @PutMapping("/{hospitalId}/{patientId}/update")
    public String update(@PathVariable("hospitalId") Long hospitalId,@PathVariable("patientId")Long id,@ModelAttribute("patients") Patient patient) {
        patientService.update(id, patient);
        return "redirect:/patients/"+hospitalId;
    }

}
