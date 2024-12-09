import java.util.*;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LostAndFoundSystem {
    private ItemManager itemManager;
    private ClaimantManager claimantManager;
    private Scanner scanner;
    private String adminPassword;

    public LostAndFoundSystem() {
        itemManager = new ItemManager();
        claimantManager = new ClaimantManager();
        scanner = new Scanner(System.in);
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest("admin123".getBytes());
            adminPassword = bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            adminPassword = "";
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void reportLostItem() {
        System.out.println("\n========== Report Lost Item ==========");

        String description;
        do {
            System.out.print("Enter item description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Error: Description cannot be empty.");
            } else if (description.split("\\s+").length < 3) {
                System.out.println("Error: Description must be at least 3 words.");
            }
        } while (description.isEmpty() || description.split("\\s+").length < 3);

        String location;
        do {
            System.out.print("Enter location found: ");
            location = scanner.nextLine().trim();
            if (location.isEmpty()) {
                System.out.println("Error: Location cannot be empty.");
            }
        } while (location.isEmpty());

        LocalDate foundDate;
        while (true) {
            try {
                System.out.print("Enter found date (YYYY-MM-DD): ");
                String dateString = scanner.nextLine().trim();
                foundDate = LocalDate.parse(dateString);
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid date format. Use YYYY-MM-DD");
            }
        }

        String[] categories = {"Electronics", "Clothing", "Accessories", "Others"};
        String category;
        while (true) {
            System.out.println("Categories: " + String.join(", ", categories));
            System.out.print("Enter category: ");
            category = scanner.nextLine().trim();
            
            if (category.isEmpty()) {
                System.out.println("Error: Category cannot be empty.");
                continue;
            }
            
            boolean validCategory = false;
            for (String cat : categories) {
                if (cat.equalsIgnoreCase(category)) {
                    category = cat;
                    validCategory = true;
                    break;
                }
            }
            
            if (!validCategory) {
                System.out.println("Error: Invalid category. Choose from: " + String.join(", ", categories));
                continue;
            }
            
            break;
        }

        String itemId = itemManager.generateItemId();
        Item item = new Item(itemId, description, location, foundDate, category);
        itemManager.addItem(item);
        System.out.println("Item reported successfully. Item ID: " + item.getItemId());
    }

    public void verifyOwnership() {
        System.out.println("\n========== Verify Item Ownership ==========");
        System.out.print("What item are you looking for? ");
        String searchTerm = scanner.nextLine().toLowerCase();
    
        List<Item> matchingItems = itemManager.findUnclaimedItemsByDescription(searchTerm);
    
        if (matchingItems.isEmpty()) {
            System.out.println("No matching items found.");
            return;
        }
    
        // Display table header
        System.out.println("\n=============================== Matching Items ===============================");
        System.out.printf("| %-8s | %-15s | %-10s | %-10s | %-10s |%n", 
                          "ID", "Location", "Found Date", "Category", "Status");
        System.out.println("================================================================================");
    
        // Display matching items
        for (Item item : matchingItems) {
            System.out.printf("| %-8s | %-15s | %-10s | %-10s | %-10s |%n",
                item.getItemId(), item.getLocation(), item.getFoundDate(), 
                item.getCategory(), item.isClaimed() ? "Claimed" : "Unclaimed");
        }
        System.out.println("================================================================================");
    
        // Claim process
        System.out.print("\nEnter the ID of the item you want to claim: ");
        String itemId = scanner.nextLine();
    
        Item item = itemManager.findUnclaimedItemById(itemId);
    
        if (item == null) {
            System.out.println("Invalid item ID or item already claimed.");
            return;
        }
    
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
    
        System.out.print("Enter your contact number: ");
        String contact = scanner.nextLine();
    
        for (int attempt = 0; attempt < 3; attempt++) {
            System.out.print("Enter a detailed description of the item to confirm ownership: ");
            String description = scanner.nextLine().toLowerCase();
    
            double similarity = itemManager.calculateSimilarity(description, item.getDescription().toLowerCase());
    
            if (similarity >= 0.6) {
                String claimCode = claimantManager.generateClaimCode();
                claimantManager.addClaimant(itemId, name, contact, claimCode);
                System.out.println("\nOwnership verified! Your claim code is: " + claimCode);
                return;
            }
            System.out.println("Description doesn't match. Attempts left: " + (2 - attempt));
        }
        System.out.println("Maximum attempts reached. Please try again later.");
    }

    public void claimItem() {
        System.out.println("\n========== Claim Item ==========");
        System.out.print("Enter your claim code: ");
        String claimCode = scanner.nextLine();
        
        Claimant claimant = claimantManager.findClaimantByCode(claimCode);
        
        if (claimant == null) {
            System.out.println("Invalid claim code.");
            return;
        }

        Item item = itemManager.findUnclaimedItemById(claimant.getItemId());
        
        if (item != null) {
            item.setClaimed(true);
            System.out.println("Item claimed successfully!");
        } else {
            System.out.println("Error: Item already claimed or not found.");
        }
    }

    public void viewItems() {
        List<Item> unclaimedItems = itemManager.getUnclaimedItems();

        if (unclaimedItems.isEmpty()) {
            System.out.println("No unclaimed items found.");
            return;
        }

        System.out.println("\n==========================================================");
        System.out.printf("| %-8s | %-15s | %-10s | %-10s |%n", 
                          "ID", "Location", "Found Date", "Category");
        System.out.println("==========================================================");

        for (Item item : unclaimedItems) {
            System.out.printf("| %-8s | %-15s | %-10s | %-10s |%n",
                    item.getItemId(), item.getLocation(), item.getFoundDate(), item.getCategory());
        }
        System.out.println("==========================================================");
    }

    public void adminMenu() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            String hashedPassword = bytesToHex(hash);
            
            if (!hashedPassword.equals(adminPassword)) {
                System.out.println("Invalid password.");
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Authentication error.");
            return;
        }

        while (true) {
            System.out.println("\n==================================");
            System.out.println("            Admin Menu            ");
            System.out.println("==================================");
            System.out.println("1. Search claimed items");
            System.out.println("2. View all claimed items");
            System.out.println("3. Archive old claims");
            System.out.println("4. Go back");
            System.out.println("==================================");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter item ID: ");
                    String itemId = scanner.nextLine();
                    Claimant claimant = claimantManager.findClaimantByItemId(itemId);
                    
                    if (claimant != null) {
                        System.out.println("\nClaim Details:");
                        System.out.println("Item ID: " + claimant.getItemId());
                        System.out.println("Claim Code: " + claimant.getClaimCode());
                        System.out.println("Claimant Name: " + claimant.getName());
                        System.out.println("Claimant Contact: " + claimant.getContact());
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                
                case "2":
                    List<Claimant> allClaimants = claimantManager.getAllClaimants();
                    if (!allClaimants.isEmpty()) {
                        System.out.println("\nClaimed Items:");
                        for (Claimant c : allClaimants) {
                            System.out.printf("Item ID: %s | Claim Code: %s | Name: %s | Contact: %s%n", 
                                c.getItemId(), c.getClaimCode(), c.getName(), c.getContact());
                        }
                    } else {
                        System.out.println("No claimed items.");
                    }
                    break;
                
                case "3":
                    itemManager.archiveOldClaims();
                    break;
                
                case "4":
                    return;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("        Lost and Found System     ");
            System.out.println("==================================");
            System.out.println("1. Report a lost item");
            System.out.println("2. Verify item ownership");
            System.out.println("3. Claim item");
            System.out.println("4. View all items");
            System.out.println("5. Admin settings");
            System.out.println("6. Exit");
            System.out.println("==================================");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    reportLostItem();
                    break;
                case "2":
                    verifyOwnership();
                    break;
                case "3":
                    claimItem();
                    break;
                case "4":
                    viewItems();
                    break;
                case "5":
                    adminMenu();
                    break;
               case "6":
                    System.out.println("Thank you for using the Lost and Found System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        LostAndFoundSystem system = new LostAndFoundSystem();
        system.run();
    }
}