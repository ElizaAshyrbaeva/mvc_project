package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentApi(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/department";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("departments", new Department());
        model.addAttribute("hospitals", hospitalService.getAllHospital());
        return "department/newDepartment";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("departments") Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        departmentService.deleteById(id);
            return "redirect:/departments";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")Long id,Model model){
        model.addAttribute("department",departmentService.findById(id));
            return "department/edit";
    }
    @PutMapping("/{id}/update")
    public String update(@PathVariable("id")Long id,@ModelAttribute("department") Department department){
        departmentService.update(id,department);
        return "redirect:/departments";
    }

}
