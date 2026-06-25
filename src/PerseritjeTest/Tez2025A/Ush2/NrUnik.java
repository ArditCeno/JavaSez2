package PerseritjeTest.Tez2025A.Ush2;

public class NrUnik {

    static boolean unik(int n){
        boolean[] shifra = new boolean[10];

        while(n > 0){
            int d = n % 10;
            if(shifra[d]) return false;

            shifra[d] = true;
            n /= 10;
        }
        return true;
    }

    static int numeroUnike(int n, int m){
        int count = 0;

        for(int i=n; i<=m; i++){
            if(unik(i))
                count++;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(numeroUnike(10,22));
    }
}
