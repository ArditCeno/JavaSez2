package PerseritjeTest.Tez2024B.Ush3;
/*Ndërtoni një klasë Book që ka atributet e mëposhtme: title, author, ISBN dhe ratings.
Ndërtoni:
konstruktorin me parametra dhe metodat aksesuese dhe ndryshuese;
metodën addRating që merr si parametër një rating dhe e shton atë në një listë të vlerësimeve
për librin nëse vlerësimi është më i madh se 1 dhe më i vogël se 5;
metodën removeRating që merr si parametër një rating dhe e heq nga lista e vlerësimeve.
metodën getAverageRating që kthen vlerësimin mesatar të librit*/
import java.util.*;

public class Book {
    private String title;
    private String author;
    private String ISBN;

    private ArrayList<Integer> ratings = new ArrayList<>();

    Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    private String  getISBN() {
        return ISBN;
    }
    private void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    void addRating(int r){
        if(r>=1 && 5<=r){
            ratings.add(r);
        }
    }
    void removeRating(int r){
        ratings.remove(Integer.valueOf(r));
    }
    double getAverageRating(){
        if(ratings.size()==0){
            return 0;
        }
        int shuma=0;
        for(int x: ratings){
            shuma +=x;
        }
        return (double) shuma/ratings.size();
    }
}
