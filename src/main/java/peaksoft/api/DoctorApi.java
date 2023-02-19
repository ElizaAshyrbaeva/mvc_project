package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public DoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("doctors",doctorService.getAll());
        return "";
    }
}