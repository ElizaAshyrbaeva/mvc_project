
package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalApi {
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    public HospitalApi(HospitalService hospitalService, DepartmentService departmentService) {
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/mainPage")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hospital", hospitalService.findById(id));
        model.addAttribute("departments", departmentService.getAll(id));
        model.addAttribute("hospitalId", id);
        return "hospital/mainPage";
    }

    @GetMapping("/show")
    public String findAll(Model model , @RequestParam (required = false)String word) {
        model.addAttribute("word", word);
        model.addAttribute("hospitals", hospitalService.findAll(word));
        return "hospital/find";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/newHospital";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("hospital") Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals/show";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hospitals", hospitalService.findById(id));
        return "hospital/edit";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newHospital") Hospital newHospital) {
        hospitalService.updateHospital(id, newHospital);
        return "redirect:/hospitals/show";
    }


}
