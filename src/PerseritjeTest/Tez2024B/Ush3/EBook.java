package PerseritjeTest.Tez2024B.Ush3;
/*Ndërtoni klasën EBook e cila trashëgon nga klasa Book dhe ka atributet fileSize dhe format.
Ndërtoni konstruktorin e kësaj klase si dhe metodat aksesuese për variablat e instancës fileSize dhe format.
Ndërtoni metodën toString() e cila do të afishojë të dhënat e EBook-ut.*/
public class EBook extends Book {
    private double fileSize;
    private String format;

    EBook(String title, String author, String ISBN, double fileSize, String format) {
        super(title, author, ISBN);
        this.fileSize = fileSize;
        this.format = format;
    }
    public double getFileSize() {
        return fileSize;
    }
    public String getFormat() {
        return format;
    }
    public String toString() {
        return getTitle() + getAuthor() + getISBN() + fileSize + format;
    }
}
