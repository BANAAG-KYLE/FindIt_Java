interface ItemStatus {
    boolean isClaimed();
    void setClaimed(boolean claimed);
    String getStatus();
}

interface Categorizable {
    String getCategory();
    void setCategory(String category);
}