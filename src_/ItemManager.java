import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class ItemManager {
    private List<Item> items;
    private Random random;

    public ItemManager() {
        items = new ArrayList<>();
        random = new Random();
    }

    public String generateItemId() {
        while (true) {
            String itemId = String.format("%03d", random.nextInt(900) + 100);
            if (items.stream().noneMatch(item -> item.getItemId().equals(itemId))) {
                return itemId;
            }
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item findUnclaimedItemById(String itemId) {
        return items.stream()
            .filter(i -> i.getItemId().equals(itemId) && !i.isClaimed())
            .findFirst()
            .orElse(null);
    }

    public List<Item> findUnclaimedItemsByDescription(String searchTerm) {
        return items.stream()
            .filter(item -> item.getDescription().toLowerCase().contains(searchTerm.toLowerCase()) && !item.isClaimed())
            .collect(Collectors.toList());
    }

    public List<Item> getUnclaimedItems() {
        return items.stream()
            .filter(item -> !item.isClaimed())
            .collect(Collectors.toList());
    }

    public void archiveOldClaims() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        List<Item> itemsToArchive = items.stream()
            .filter(item -> item.isClaimed() && item.getFoundDate().isBefore(thirtyDaysAgo))
            .collect(Collectors.toList());
    
        int archived = itemsToArchive.size();
        items.removeAll(itemsToArchive);
        System.out.println("Archived " + archived + " items.");
    }

    public double calculateSimilarity(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        int commonChars = 0;
        
        for (char c : s1.toCharArray()) {
            int index = s2.indexOf(c);
            if (index != -1) {
                commonChars++;
                s2 = s2.replaceFirst(String.valueOf(c), "");
            }
        }
        
        return (double) commonChars / maxLength;
    }
}