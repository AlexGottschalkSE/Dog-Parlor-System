package Models;

public class Dog {

    private String name;
    private String size;
    private int breed;
    private int visits;
    private Customer owner;

    public Dog() {
    }

    public Dog(String name, String size, int breed) {
        this.name = name;
        this.size = size;
        this.breed = breed;
    }

    public Dog(String name, String size, int breed, int visits) {
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
