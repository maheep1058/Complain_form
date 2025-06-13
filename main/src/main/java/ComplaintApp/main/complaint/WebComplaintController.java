package ComplaintApp.main.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/complaints")
public class WebComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public WebComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Display all complaints
    @GetMapping
    public String getAllComplaints(Model model) {
        model.addAttribute("complaints", complaintService.getComplaint());
        return "complaints/list"; // Refers to src/main/resources/templates/complaints/list.html
    }

    // Show form to add a new complaint
    @GetMapping("/new")
    public String showComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaints/form"; // Refers to src/main/resources/templates/complaints/form.html
    }

    // Save a new complaint
    @PostMapping
    public String saveComplaint(@ModelAttribute Complaint complaint) {
        complaintService.saveComplaint(complaint);
        return "redirect:/complaints";
    }

    // Show form to edit a complaint
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Complaint complaint = complaintService.getComplaintById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
        model.addAttribute("complaint", complaint);
        return "complaints/form";
    }

    // Delete a complaint
    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return "redirect:/complaints";
    }
}