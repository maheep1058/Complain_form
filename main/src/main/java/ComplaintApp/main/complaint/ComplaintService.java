package ComplaintApp.main.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getComplaint() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatusIgnoreCase(status);
    }
    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public void updateComplaint(Long id, Complaint updatedComplaint) {
        Complaint existingComplaint = complaintRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Complaint with ID " + id + " not found"));

        existingComplaint.setDescription(updatedComplaint.getDescription());
        existingComplaint.setStatus(updatedComplaint.getStatus());

        complaintRepository.save(existingComplaint);
    }

    public void deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id)) {
            throw new IllegalStateException("Complaint with ID " + id + " does not exist");
        }
        complaintRepository.deleteById(id);
    }

    public void addDummyComplaint() {
        if (complaintRepository.count() == 0) { // only add if DB is empty
            complaintRepository.saveAll(List.of(
                    new Complaint("Leaking tap in kitchen", "open"),
                    new Complaint("Broken window in room", "resolved"),
                    new Complaint("Power cut in block A", "open")
            ));
        }
    }
}
