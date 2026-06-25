package Seminar9.Ush2;

public class Person {
    private String emri;
    private String mbiemri;
    private String ID;

    public Person(String emri, String mbiemri, String ID) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.ID = ID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "emri='" + emri + '\'' +
                ", mbiemri='" + mbiemri + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return ID.equals(person.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
