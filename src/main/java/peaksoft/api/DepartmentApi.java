package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.DepartmentService;

@Controller
@RequestMapping("departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentApi(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("departments",departmentService.getAll());
    return "department/department";
    }

}
