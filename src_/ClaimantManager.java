import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClaimantManager {
    private List<Claimant> claimants;
    private Random random;

    public ClaimantManager() {
        claimants = new ArrayList<>();
        random = new Random();
    }

    public String generateClaimCode() {
        return "CLAIM-" + (1000 + random.nextInt(9000));
    }

    public void addClaimant(String itemId, String name, String contact, String claimCode) {
        claimants.add(new Claimant(itemId, claimCode, name, contact));
    }

    public Claimant findClaimantByCode(String claimCode) {
        return claimants.stream()
            .filter(c -> c.getClaimCode().equals(claimCode))
            .findFirst()
            .orElse(null);
    }

    public Claimant findClaimantByItemId(String itemId) {
        return claimants.stream()
            .filter(c -> c.getItemId().equals(itemId))
            .findFirst()
            .orElse(null);
    }

    public List<Claimant> getAllClaimants() {
        return new ArrayList<>(claimants);
    }
}