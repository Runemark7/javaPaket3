import java.util.Iterator;
import java.util.Stack;

public class TreeSetCounter<T extends Comparable<T>> implements Iterable<T> {

    Node root;
    int length = 0;

    private class Node{
        private T val;
        private Node left, right;
        private int n;

        Node(T val, int n)
        {
         this.val = val;
         this.n = n;
        }
    }

    public static void main(String[] args) {

    }

    public TreeSetCounter(){
        this.root = new Node(null, 1);
    }

    public void add(T t){
        add(root, t);
    }

    private void addCount(Node node){


    }

    public void add(Node node, T val){
            int branchSearchVal = node.val.compareTo(val);
            if(branchSearchVal > 0){
                add(node.left, val);
            }else if(branchSearchVal < 0){
                if(node.right == null){
                    node.right = new Node(val, 0);
                }
                add(node.right, val);

            }else{

            }
    }

    public int size(){
        return length;
    }

    private class BSTIterator implements Iterator<T>
    {
        Stack<Node> stack = new Stack<Node>();

        BSTIterator()
        {
            pushLeft(root);
        }

        public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        public T next()
        {
            Node node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }

        private void pushLeft(Node node)
        {
            while(node != null)
            {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public BSTIterator iterator()
    {
        return new BSTIterator();
    }
}
