package ComplaintApp.main.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public List<Complaint> getComplaint() {
        return complaintService.getComplaint();
    }

    @GetMapping("/{id}")
    public Optional<Complaint> getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @GetMapping("/status/{status}")
    public List<Complaint> getComplaintByStatus(@PathVariable String status) {
        return complaintService.getComplaintsByStatus(status);
    }

    @PostMapping
    public void addComplaint(@RequestBody Complaint complaint) {
        complaintService.saveComplaint(complaint);
    }

    @PutMapping("/{id}")
    public void updateComplaint(@PathVariable Long id, @RequestBody Complaint updatedComplaint) {
        complaintService.updateComplaint(id, updatedComplaint);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
