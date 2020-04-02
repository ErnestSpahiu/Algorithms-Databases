import java.util.Arrays;
import java.util.Stack;

//MSAF LAB 3
public class BSTSet{
    private TNode root;
    public TNode left;
    public TNode right;

    public BSTSet(){
        this.root = null;
    }

    public BSTSet(int[] input){
        if (input.length == 0){
            this.root = null;
        }
        else {
            this.root = new  TNode(input[0], null, null);

            for (int i=1;i<input.length;i++){ 
                this.add(input[i]);
            }
        }
    }
    public boolean isIn(int v){
        //initializes node scroll to pass through each node
        TNode nodeScroll = this.root;
        if (nodeScroll == null){
            return false;
        }

        while (true) {
            if (nodeScroll == null){
                return false;
            }
            
            if (nodeScroll.element == v){
                return true;
            }

            if (v < nodeScroll.element){
                nodeScroll = nodeScroll.left;
            }
            else if ( v > nodeScroll.element){
                nodeScroll = nodeScroll.right;
            }

        }
    }

    public void add(int v){
        if (this.root == null){
            this.root = new TNode(v, null, null);
        }
        TNode nodeScroll = this.root;
        if (this.isIn(v) == false){    
            while (true) {
                if (v < nodeScroll.element && nodeScroll.left != null){
                    nodeScroll = nodeScroll.left;
                }
                else if (v > nodeScroll.element && nodeScroll.right != null){
                    nodeScroll = nodeScroll.right;
                }
                if (v < nodeScroll.element && nodeScroll.left == null){
                    TNode newNode = new TNode(v,null,null);
                    nodeScroll.left = newNode;
                    return;
                }
                if (v > nodeScroll.element && nodeScroll.right == null){
                    TNode newNode = new TNode(v,null,null);
                    nodeScroll.right = newNode;
                    return;
                }
            }
        }
    }

    public boolean remove(int v){
        if (this.root == null){
            return false;
        }
        else if (this.isIn(v) == false){
            return false;
        }
        else {
            this.remove (v,this.root);
            return true;
        }
    }


    public TNode remove(int v, TNode t){
        //check if the value is on the left or right side, then start over at the new node
        if (v < t.element){
            t.left = remove(v,t.left);
        }
        else if (v > t.element) {
            t.right = remove(v,t.right);
        }
        //once the value is found, check to see if it is a leaf or not (children vs no children)
        else if (t.left != null && t.right != null){
            
            TNode scrollNode = t.right;
            if (t.right.left != null){
                scrollNode = scrollNode.left;
            }
            //find the minimum value on the right side of the node
            //will be a leaf with no children
            while (scrollNode.left != null){
                scrollNode = scrollNode.left;
            }
            //the minimum value will replace the value of the current node
            t.element = scrollNode.element;
            //remove the leaf now
            t.right = removeMin(t.right);

        }
        else {
            t = (t.left != null) ? t.left  : t.right;
        }
        return t;
    }

    //removing the leaf
    public TNode removeMin(TNode t){
        if (t.left != null){
            t.left = removeMin(t.left);
            return t;
        }
        else {
            return t.right;
        }
    }

    public int size(){
        int size = this.size(this.root);
        return size;
    }

    public int size(TNode t){
        int count = 0;
        if (t != null){
            count ++;
            count += (size(t.left));
            count += (size(t.right));
        }
        return count;
    }

    public int height(TNode t){
        int leftCount = 0;
        int rightCount = 0;

        if (t == null){
            return 0;
        }
        else {
            leftCount += (height(t.left) + 1);
            rightCount += (height(t.right) + 1);
        }
        return (leftCount>rightCount ? leftCount : rightCount);
    }

    public int height(){
        return (height(this.root));
    }

    public BSTSet union (BSTSet s){
        BSTSet set = new BSTSet();
        //if the the S set is empty then the new set is just This set
        if (s.root == null){
            return this;
        }
        //if This set is empty, then the new set is just the s set
        else if (this.root == null){
            return s;
        }
        //if both are empty, return an empty set
        else if (s.root == null && this.root == null){
            return set;
        }
        //otherwise start adding both elements into one
        else {
            set = union(s.root);
            return set;
        }
    }
    public BSTSet union(TNode t){
        BSTSet unionSet = this;
        //use the This set as the host, add each element in the s set which is not already in This set
        if (t != null){
            unionSet.add(t.element);
            unionSet.union(t.left);
            unionSet.union(t.right);
        }
        return unionSet;
    }

    public BSTSet intersection(BSTSet s){
        BSTSet set1 = new BSTSet();
        BSTSet set2 = s;
        if (this.root == null || s.root == null){
            return set1;
        }
        intersection(set1, set2, this.root);
        return set1;
    }

    public void intersection(BSTSet interSet, BSTSet set2, TNode t) {
        if (t != null){
            if (set2.isIn(t.element) == true){
                interSet.add(t.element);
            }
            intersection(interSet, set2, t.left);
            intersection(interSet, set2, t.right);
        }

    }

        

    public BSTSet difference(BSTSet s){
        BSTSet set1 = new BSTSet();
        if (this.root == null){
            return set1;
        }
        else if (s.root == null){
            return this;
        }
        else if (this.root == null & s.root == null){
            return set1;
        }
        else{
            difference(set1, s, this.root);
        }
        return set1;
    }

    public void difference(BSTSet diffSet, BSTSet set2, TNode t){
        //if the node is not null
        if (t != null){
            //if the element present in set2 is not present in this set, then add it to the diffSet
            if (set2.isIn(t.element) != true) {
                diffSet.add(t.element);
            }
            //repeat for the left and right nodes
            difference(diffSet, set2, t.left);
            difference(diffSet, set2, t.right);
        }
    }



    public void printBSTSet() {
        if (root==null) {
            System.out.println("The set is empty");
        }
        else {
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }

    public void printBSTSet(TNode t){
        if(t != null){
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec(){
        Stack <TNode> nodeStack = new Stack();
        TNode node = this.root;

        while (node != null || nodeStack.size() > 0){
            while (node != null){
                //push the node and then go to the left child
                nodeStack.push(node);
                node = node.left;
            }
            //node is now null, go back 
            node = nodeStack.pop();
            System.out.print(node.element + " ");
            //now move to the right child
            node = node.right;
        }

    }
}