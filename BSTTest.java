public class BSTTest {
    public static void main(String[] args) {
        int a1[] = {50,17,72,12,23,54,67,19,9,14};
        BSTSet aa1 = new BSTSet(a1);
        aa1.printBSTSet();
        System.out.println("Size test: " + aa1.size());
        System.out.println("Height test: "+ aa1.height());
        System.out.println("-------FIND TEST-----------");
        System.out.println(aa1.isIn(67));

        System.out.println("-------REMOVE TEST-----------");
        aa1.remove(72);
        aa1.printBSTSet();

        System.out.println("-------UNION TEST-----------");
        int a2 [] = {123,412,21,50,50};
        BSTSet aa2 = new BSTSet(a2);
        System.out.print("A1 ---");
        aa1.printBSTSet();
        System.out.print("A2 ---");
        aa2.printBSTSet();

        
        BSTSet aa3 = aa1.union(aa2);
        System.out.print("Union Result----");
        aa3.printBSTSet();

        System.out.println("-------INTER TEST-----------");
        int b1[] = {23,51,4124,512,1,3,2,31,414,2121};
        BSTSet bb1 = new BSTSet(b1);
        System.out.print("B1 ---");
        bb1.printBSTSet();
        int b2[] = {1222,1,20,71,72,73,4,33,414,21,2,31,42,513,23};
        BSTSet bb2 = new BSTSet(b2);
        System.out.print("B2 ---");
        bb2.printBSTSet();

        BSTSet bb3 = bb1.intersection(bb2);
        System.out.print("inter result ---");
        bb3.printBSTSet();
        
        System.out.println("-------DIFF TEST-----------");
        int d1[] = {10,231,444,112,240};
        BSTSet dd1 = new BSTSet(d1);
        System.out.print("D1---");
        dd1.printBSTSet();
        int d2[] = {10,222,444,112,2401};
        BSTSet dd2 = new BSTSet(d2);
        System.out.print("D2---");
        dd2.printBSTSet();
        BSTSet dd3 = dd1.difference(dd2);
        dd3.printBSTSet();

        System.out.println("-------STACK PRINT TEST-----------");
        int s1[] = {1222,3,20,71,7173,4,33,414,21,2,31,42,531,23};
        BSTSet ss1 = new BSTSet(s1);
        ss1.printNonRec();
    }
}