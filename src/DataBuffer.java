import java.util.Iterator;

public class DataBuffer<T> implements  Iterable<T> {
    int bufferSize;
    T[] a;
    int back = -1;
    int front = 0;
    int length = 0;

    public static void main(String[] args){
        DataBuffer buffe = new DataBuffer(5);
        buffe.enqueue(1);
        buffe.enqueue(2);
        buffe.enqueue(3);
        buffe.enqueue(4);
        buffe.enqueue(5);
        buffe.changeBufferSize(3);
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.dequeue();
        //buffe.enqueue(3);
        //buffe.enqueue(4);
        //buffe.enqueue(5);
        //buffe.changeBufferSize(15);
        //buffe.enqueue(30);
        //buffe.enqueue(40);
        //buffe.enqueue(50);
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

    public T[] trimArray(int size){

        T[] b = (T[]) new Object[size];

        while(length != size){
            if(back == 0){
                back = bufferSize-1;
            }else{
                back--;
            }
            length--;
        }

        boolean done = false;
        while(!done){
            int counter = 0;
            if(back > front){
                for (int i = front; i < back; i++) {
                    b[counter] = a[front+i];
                    counter++;
                }
                done = true;
            }else{
                for (int i = 0; i < back; i++) {
                    b[counter] = a[i];
                    counter++;
                }
                for (int i = front; i < length; i++) {
                    b[counter] = a[i];
                    counter++;
                }
                done = true;
            }
        }
        return b;
    }

    public void dequeue(){
        if(isEmpty()){
            System.out.println("Det finns ju fan inga element här jue");
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
        a = trimArray(newBufferSize);
        bufferSize = newBufferSize;

        //a = b;
        /*if(back > front){
            if(newBufferSize > bufferSize){
                for (int i = 0; i < bufferSize; i++) {
                    b[i] = a[i];
                }
            }else{
                while(length < newBufferSize){
                    dequeue();
                }
                for (int i = 0; i < bufferSize; i++) {
                    b[i] = a[i];
                }
            }
        }else{
            if(newBufferSize > bufferSize){



            }else{
                while(length < newBufferSize){
                    dequeue();
                }
            }

            // now it's a fit :D

        }*/
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
