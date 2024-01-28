package models;

public class RareEditionBook extends Book {
    private int rarityLevel; // The higher the level, the more valuable the book

    public RareEditionBook(String title, String author, String isbn, double baseValue, Integer year, int rarityLevel) {
        super(title, author, isbn, baseValue + (rarityLevel * 10), year); // Increase value based on rarity level
        this.rarityLevel = rarityLevel;
    }

    public int getRarityLevel() {
        return rarityLevel;
    }

    // Additional methods or overrides specific to RareEditionBook can be added here
}
