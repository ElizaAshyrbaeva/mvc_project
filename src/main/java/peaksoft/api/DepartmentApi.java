package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
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

    @GetMapping("/{hospitalId}")
    public String findAll(@PathVariable Long hospitalId,Model model) {
        model.addAttribute("departments", departmentService.getAll(hospitalId));
        model.addAttribute(hospitalId);
        return "department/department";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute(hospitalId);
        return "department/newDepartment";
    }

    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDepartment") Department department) {
        departmentService.save(hospitalId,department);
        return "redirect:/departments/"+hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{departmentId}/delete")
    public String deleteById(@PathVariable("hospitalId")Long hospitalId,@PathVariable("departmentId") Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping("/{hospitalId}/{departmentId}/edit")
    public String edit(Model model, @PathVariable("departmentId") Long id,@PathVariable("hospitalId")Long hospitalId) {
        model.addAttribute(departmentService.findById(id));
        model.addAttribute("hospitalId", hospitalId);
        return "department/edit";
    }

    @PutMapping("/{hospitalId}/{departmentId}/update")
    public String update(@PathVariable("hospitalId") Long hospitalId,@PathVariable("departmentId")Long id,@ModelAttribute("department") Department department) {
        departmentService.update(id, department);
        return "redirect:/departments/"+hospitalId;
    }

}