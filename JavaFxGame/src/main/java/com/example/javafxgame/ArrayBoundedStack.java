package com.example.javafxgame;


public class ArrayBoundedStack<T> implements StackInterface<T>
{
    protected final int DEFCAP = 100; // default capacity
    protected T[] elements;           // holds stack elements
    protected int topIndex = -1;      // index of top element in stack

    public ArrayBoundedStack()
    {
        elements = (T[]) new Object[DEFCAP];
    }

    public ArrayBoundedStack(int maxSize)
    {
        elements = (T[]) new Object[maxSize];
    }
    //ADDED THIS -------------------------------
    @Override
    public String toString(){
        String r = "";
        int counter = topIndex;
        int c = 1; // for list formating
        //if stack is empty
        if(topIndex == -1) {
            //return stack is empty
            r += "Stack is Empty";
        }
        else {
            //otherwise, move through elements
            while(counter >= 0) {
                //add elements toString to stack's toString
                r +=  c + " : " +elements[counter].toString() + "\n";
                counter -= 1;
                c++;
                //the idea is that itll print in list format, with the top stack element at the top of list
            }
        }
        return r; // return toString
    }
    // ------------------------------------------

    //ADDED THIS-------------
    public int size() {
        return topIndex + 1;
    }
    //-----------------------

    //ADDED THIS----------------------
    public void popSome(int count) {
        if(topIndex + 1 < count) { // if count is greater than size
            throw new StackUnderflowException("Count is greater than number of elements in the stack");
        }
        else {
            for(int i = 0; i < count; i++) { // move through stack until count is satisfied
                elements[topIndex] = null; // set topindex to null
                topIndex--; // move the index
            }
        }
    }
    // -------------------------------

    //ADDED THIS----------------------
    public boolean swapStart() {
        boolean done;
        if(topIndex + 1 < 2) { // if the stack is less than 2
            done = false;
        }
        else {
            T temp = elements[topIndex - 1];//create temp T of second element
            elements[topIndex - 1] = elements[topIndex]; // set second element to the top
            elements[topIndex] = temp; //assign top element to temp of second

            done = true;
        }
        return done;
    }
    // -------------------------------

    //ADDED THIS----------------------
    public T poptop() {
        T returnVal;
        if(topIndex == -1) { // stack is empty
            throw new StackUnderflowException("poptop attempted on an empty stack");
        }
        else {
            returnVal = elements[topIndex]; // value we are returning
            elements[topIndex] = null; // top of stack is null
            topIndex--; //move top index
        }
        return returnVal;
    }
    // -------------------------------

    @Override
    public void push(T element)
    // Throws StackOverflowException if this stack is full,
    // otherwise places element at the top of this stack.
    {
        if (isFull())
            throw new StackOverflowException("Push attempted on a full stack.");
        else
        {
            topIndex++;
            elements[topIndex] = element;
        }
    }
    @Override
    public void pop()
    // Throws StackUnderflowException if this stack is empty,
    // otherwise removes top element from this stack.
    {
        if (isEmpty())
            throw new StackUnderflowException("Pop attempted on an empty stack.");
        else
        {
            elements[topIndex] = null;
            topIndex--;
        }
    }
    @Override
    public T top()
    // Throws StackUnderflowException if this stack is empty,
    // otherwise returns top element of this stack.
    {
        T topOfStack = null;
        if (isEmpty())
            throw new StackUnderflowException("Top attempted on an empty stack.");
        else
            topOfStack = elements[topIndex];
        return topOfStack;
    }
    @Override
    public boolean isEmpty()
    // Returns true if this stack is empty, otherwise returns false.
    {
        return (topIndex == -1);
    }
    @Override
    public boolean isFull()
    // Returns true if this stack is full, otherwise returns false.
    {
        return (topIndex == (elements.length - 1));
    }
}