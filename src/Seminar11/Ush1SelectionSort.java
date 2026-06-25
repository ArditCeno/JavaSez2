package Seminar11;

public class Ush1SelectionSort {

    static class Lumi {
        String emri;
        int gjatesia;

        Lumi(String emri, int gjatesia) {
            this.emri = emri;
            this.gjatesia = gjatesia;
        }
        void printo(){
            System.out.println(emri+","+gjatesia);
        }
    }
    //1a per selection sort ne rend zbrites
    public static void selectionSortZbritje(int[] arr){
        int n=arr.length;

        for(int i=0;i<n-1;i++){
            int maxId = i;
            for (int j=i+1;j<n;j++){
                if(arr[j]>arr[maxId]){ //rend zbrites
                    maxId=j;
                }
            }
            int tmp = arr [i];
            arr [i] = arr [maxId];
            arr [maxId] = tmp;
        }
    }
    //1b Selection sort per arr lumenjsh
    public static void selectionSortGjatesia(Lumi [] lumenjt){
        int n = lumenjt.length;
        for(int i=0;i<n-1;i++){
            int maxId = i;
            for (int j=i+1;j<n;j++){
                if (lumenjt[j].gjatesia > lumenjt[maxId].gjatesia){
                    maxId=j;
                }
            }
            Lumi tmp = lumenjt[i];
            lumenjt[i] = lumenjt[maxId];
            lumenjt[maxId] = tmp;
        }
    }
    //metode printimi array
    static void printArr(int[] arr){
        for(int num : arr){
            System.out.print(num+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        //Testi 1a per renditje
        int [] numra = {5,3,6,7,2,87};
        System.out.print("Para sortimit: ");
        printArr(numra);
        selectionSortZbritje(numra);
        System.out.print("Pas sortimit: ");
        printArr(numra);

        //test 2b
        Lumi [] lumenjt = {
                new Lumi("Osumi", 23412),
                new Lumi("Danubi", 1234)
        };
        System.out.print("Para sortimit: ");
        for(Lumi l : lumenjt)l.printo();
        selectionSortGjatesia(lumenjt);
        System.out.print("Pas sortimit: ");
        for (Lumi l : lumenjt)l.printo();
    }

}
