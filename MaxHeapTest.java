public class MaxHeapTest {
    public static void main(String[] args){
        MaxHeap h1 = new MaxHeap(5);
        h1.printHeap();

        System.out.println("----INSERT TEST----");
        h1.insert(241);
        h1.insert(300);
        h1.insert(3);
        h1.insert(23);
        h1.insert(412);
        h1.insert(1000);
        h1.printHeap();

        System.out.println("----DELETE MAX TEST----");
        h1.printHeap();
        h1.deleteMax();
        h1.printHeap();
        h1.deleteMax();
        h1.printHeap();
        h1.deleteMax();
        System.out.println(h1.toString());
  

        Integer[] a1 = new Integer[]{43,2,3,100,3,42,5311,42,12};
        Integer[] a2 = new Integer[]{43,2,3,100,3,42,5311,42,12,-123,-231,42141,412,41241,-2111111,421412};
        MaxHeap h2 = new MaxHeap(a1);
        System.out.println("----HEAP SORT TEST----");
        MaxHeap.heapsort(a1);
        MaxHeap.heapsort(a2);
        for (int i=0;i<a1.length;i++){
            System.out.print(a1[i] + " ");
        }
        System.out.println();
        for (int i=0;i<a2.length;i++){
            System.out.print(a2[i] + " ");
        }

    }
}