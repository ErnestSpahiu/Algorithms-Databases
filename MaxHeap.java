public class MaxHeap {
    private Integer[] heap;
    private int heapSize;
    private int numOfElements;


    public MaxHeap(int size) {
        this.heap = new Integer[size];
        this.heapSize = size;
        this.numOfElements = 0;
    }

    public MaxHeap(Integer[] someArray){
        this.heapSize = someArray.length;
        this.heap = someArray;

        for (int i=0;i<someArray.length;i++){
            insert(someArray[i]);
        }

    }

    public void insert(int n){
        
        //if the number of elements exceeds the array size then create a new array
        if (this.numOfElements == this.heapSize -1){
            Integer[] oldHeap = this.heap;
            this.heap = new Integer[this.heapSize*2];
           
            //reinsert all the values from the old heap array into the new one
            for (int i=0;i<this.heapSize;i++){
                this.heap[i] = oldHeap[i];
            }
            this.heapSize = this.heapSize*2;
        }
        this.heap[this.numOfElements] = n;
        int newIndex = this.numOfElements;
        int oldParent;

        while (true){
            int index = (int)(newIndex-1)/2;
            //if the child is greater, perform a swap
            if (n > this.heap[index]) {
                oldParent = this.heap[index];
                this.heap[index] = n;
                this.heap[newIndex] = oldParent;
                newIndex = index;
            }
            else {
                break;
            }
        }
        this.numOfElements++;
    }

    public int deleteMax(){
        //remove the max value and replace it with the last value in the heap

        int max = this.heap[0];
        int last = this.heap[this.numOfElements-1];
        int index = this.numOfElements;
        MaxHeap newHeap = new MaxHeap(this.heapSize);
        newHeap.insert(last);
        this.numOfElements = 1;

        //reinsert all other nodes into new heap
        for (int i = 1;i<index-1;i++){
            newHeap.insert(this.heap[i]);
            this.numOfElements++;
        }
        this.heap = newHeap.heap;

        return max;
    }
    
    public int getSize(){
        return this.numOfElements;
    }

    public int getCapacity(){
        return this.heapSize;
    }

    public Integer[] getHeap(){
        return this.heap;
    }

    public static void heapsort(Integer[] arrayToSort){
        MaxHeap sortedArray = new MaxHeap(arrayToSort);
        for (int i=0;i<arrayToSort.length;i++){
            arrayToSort[i] = sortedArray.deleteMax();
        }
    }

    public void printHeap() {
        String array = "";
        for (int i = 0;i<this.heapSize;i++){
            array += this.heap[i] + " ";
        }
        System.out.println(array);
    }

    public String toString() {
        String array = "";
        int i = 0;
        while (this.heap[i]!= null){
            array += this.heap[i] + ",";
            i++;
        }
        return array;
    }
}