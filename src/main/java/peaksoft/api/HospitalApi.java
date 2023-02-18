package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalApi {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalApi(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospital());
        return "hospital/mainPage";
    }
    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospital/newHospital";
    }
    @PostMapping("/savePage")
    public String save(@ModelAttribute("newHospital")Hospital hospital){
        hospitalService.save(hospital);
        return"redirect:/hospitals";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
    @GetMapping("/{id}/edit")
    public String updateHospital(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hospital", hospitalService.findById(id));
        return "hospital/edit";
    }

    @PutMapping("/{id}/update")
    public String updateCompany(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital newHospital) {
        hospitalService.updateHospital(id, newHospital);
        return "redirect:/hospitals";
    }
}
