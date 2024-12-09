import java.time.LocalDate;

abstract class BaseItem {
    protected String itemId;
    protected String description;
    protected String location;
    protected LocalDate foundDate;
    
    public BaseItem(String itemId, String description, String location, LocalDate foundDate) {
        this.itemId = itemId;
        this.description = description;
        this.location = location;
        this.foundDate = foundDate;
    }
    
    public abstract String getItemDetails();
    
    public String getItemId() { return itemId; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public LocalDate getFoundDate() { return foundDate; }
}