package Seminar1;

public class Ush3Student extends Ush3Person {
    private String id;

    public Ush3Student(String emri, String id) {
        super(emri);
        this.id = id;
    }

 public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void printo() {
        System.out.println("Emri: " + emri + ", ID: " + id);
    }

    public boolean teNjejte(Ush3Student s) {
        return emerNjejte(s) && this.id.equals(s.id);
    }
}