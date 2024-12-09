import java.time.LocalDate;

class Item extends BaseItem implements ItemStatus, Categorizable {
    private String category;
    private boolean claimed;
    private String status;

    public Item(String itemId, String description, String location, LocalDate foundDate, String category) {
        super(itemId, description, location, foundDate);
        this.category = category;
        this.claimed = false;
        this.status = "Unclaimed";
    }

    @Override
    public String getItemDetails() {
        return String.format("ID: %s | Description: %s | Location: %s | Found: %s | Category: %s | Status: %s",
            itemId, description, location, foundDate, category, status);
    }

    @Override
    public boolean isClaimed() { return claimed; }

    @Override
    public void setClaimed(boolean claimed) { 
        this.claimed = claimed; 
        this.status = claimed ? "Claimed" : "Unclaimed";
    }

    @Override
    public String getStatus() { return status; }

    @Override
    public String getCategory() { return category; }

    @Override
    public void setCategory(String category) { this.category = category; }
}