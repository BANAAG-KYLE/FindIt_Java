class Claimant {
    private String itemId;
    private String claimCode;
    private String name;
    private String contact;

    public Claimant(String itemId, String claimCode, String name, String contact) {
        this.itemId = itemId;
        this.claimCode = claimCode;
        this.name = name;
        this.contact = contact;
    }

    public String getItemId() { return itemId; }
    public String getClaimCode() { return claimCode; }
    public String getName() { return name; }
    public String getContact() { return contact; }
}