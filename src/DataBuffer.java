import java.util.Iterator;

public class DataBuffer<T> implements  Iterable<T> {
    int bufferSize;
    T[] a;
    int back = -1;
    int front = 0;
    int length = 0;

    public static void main(String[] args){
        DataBuffer buffe = new DataBuffer(15);
        buffe.enqueue("a");
        buffe.enqueue("b");
        buffe.enqueue("c");
        buffe.enqueue("g");
        buffe.enqueue("d");
        buffe.enqueue("e");
        buffe.enqueue("f");
        buffe.enqueue("g");
        buffe.enqueue("f");
        buffe.changeBufferSize(10);


        //buffe.enqueue("a");
        //buffe.enqueue("b");
        //buffe.enqueue("c");
        //buffe.enqueue("d");
        //buffe.enqueue("e");
        //buffe.enqueue("f");
        //buffe.enqueue("g");
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.enqueue("h");
        //buffe.enqueue("i");
        //buffe.changeBufferSize(15);
        //buffe.enqueue("j");
        //buffe.enqueue("k");
        //buffe.enqueue("l");
        //buffe.enqueue("m");
        //buffe.enqueue("n");
        //buffe.enqueue("o");
        //buffe.enqueue("p");
        //buffe.enqueue("q");
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.enqueue("s");
        //buffe.enqueue("s");
        //buffe.enqueue("s");
        //buffe.enqueue("s");
        //buffe.enqueue("s");
        //buffe.changeBufferSize(10);
        //buffe.enqueue("test"); // should break
    }

    public DataBuffer(int bufferSize){
        this.bufferSize = bufferSize;
        a = (T[]) new Object[bufferSize];
    }

    public void enqueue(T t){
        if(isFull()){
            throw new IndexOutOfBoundsException("Buffer is full");
        }
        if(isEmpty()){
            back = 0;
            a[0] = t;
        } else if(back == bufferSize-1){
            back = 0;
            a[0] = t;
        } else{
            back++;
            a[back] = t;
        }

        length++;
    }

    public void dequeue(){
        if(isEmpty()){
            System.out.println("Det finns ju fan inga element h??r jue");
        }else {
            if(front == bufferSize-1){
                front = 0;
            }else{
                front++;
            }
            length--;
        }
    }

    public void changeBufferSize(int newBufferSize){
        if(newBufferSize == bufferSize){
            return;
        }

        if(newBufferSize < length){ //make buffer smaller
            while(!(length == newBufferSize)){
                if(back == 0){
                    back = bufferSize-1;
                }else{
                    back--;
                }
                length--;
            }
        }

        T[] b = (T[]) new Object[newBufferSize];
        boolean done = false;
        while(!done){
            int counter = 0;
            if(back > front){
                for (int i = front; i <= back; i++) {
                    b[counter] = a[front+counter];
                    counter++;
                }
                done = true;
            }else{
                for (int i = front; i < bufferSize; i++) {
                    b[counter] = a[i];
                    counter++;
                }
                for (int i = 0; i <= back; i++) {
                    b[counter] = a[i];
                    counter++;
                }
                done = true;
            }
        }
        front = 0;
        back = length-1;
        a = b;
        bufferSize = newBufferSize;
    }

    public boolean isFull(){
        return length == bufferSize;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int size(){
        return length;
    }

    public int bufferSize(){
        return bufferSize;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
