import java.util.*;

public class JavaCollections {
    public static void main(String[] args) {
        // // 1.ArrayList class
        // List<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // // On increasing the size of arraylist it changes to:
        // // if original size: n
        // // then new size: n + n/2 + 1
        // System.out.println(list);

        // // add function
        // list.add(20); // adds 20 to the last of list
        // System.out.println(list);

        // list.add(2, 300); // adds 300 to the 2nd position
        // System.out.println(list);

        // List<Integer>newList=new ArrayList<>();
        // newList.add(12);
        // newList.add(121);
        // newList.add(134);

        // list.addAll(newList); //appends all the elements of the newlist to this list
        // System.out.println(list);

        // //get function
        // System.out.println(list.get(1));

        // //remove function
        // list.remove(2); //This will remove element present at 2nd index
        // System.out.println(list);

        // list.remove(Integer.valueOf(121)); //This will remove the element 121
        // //Integer.valueOf() is used to convert 121(int) to 121(Integer)
        // System.out.println(list);

        // //Set function
        // list.set(2, 1000); //updates the value present at 2nd index to 1000
        // System.out.println(list);

        // //Contains function
        // System.out.println(list.contains(1000)); //returns true or false depending
        // upon the value, whether it's present or not
        // list.clear();//Empty's the list
        // System.out.println(list);




        // //2. Queue Interface
        // //classes:arraylist,linkedlist and priorityqueue
        // Queue<Integer>queue=new LinkedList<>();

        // //Offer function:adds element to the queue
        // //It is same as add but with slight change,add returns exception in case of
        // errors whereas offers returns false(doesn't crashes the application)
        // queue.offer(12);
        // queue.offer(223);
        // queue.offer(36);
        // System.out.println(queue);

        // //poll function:pop element
        // //It is same as remove but with slight change,remove returns exception in
        // case of errors whereas poll returns false(doesn't crashes the application)
        // System.out.println(queue.poll());
        // System.out.println(queue);



        // 3.Priority Queue
        // PriorityQueue<Integer>pq=new PriorityQueue<>(); //Min-Heap

        // //offer
        // pq.offer(12);
        // pq.offer(2);
        // pq.offer(322);

        // System.out.println(pq);

        // //poll
        // System.out.println(pq.poll());//Smallest element will be removed as it is a
        // min-heap

        // //Max-Heap
        // PriorityQueue<Integer>pq=new PriorityQueue<>(Comparator.reverseOrder());

        // //offer
        // pq.offer(12);
        // pq.offer(2);
        // pq.offer(322);

        // System.out.println(pq);

        // //poll
        // System.out.println(pq.poll());//Greatest element will be removed as it is a
        // max-heap



        // 4.Doubly ended queue
        // ArrayDeque<Integer>adq=new ArrayDeque<>();

        // //offer
        // adq.offer(25); //last
        // adq.offerFirst(45);//first
        // adq.offerLast(65);//last
        // adq.offer(12);//first

        // System.out.println(adq);

        // //peek
        // System.out.println(adq.peek());//first
        // System.out.println(adq.peekFirst());//first
        // System.out.println(adq.peekLast());//last
        // System.out.println(adq.peek());//first

        // System.out.println(adq);

        // // //poll
        // System.out.println(adq.poll());//first
        // System.out.println(adq.pollFirst());//first
        // System.out.println(adq.pollLast());//last
        // System.out.println(adq.poll());//first

        // System.out.println(adq);




        // 5.Hash set O(1)
        // Set<Integer>set=new HashSet<>(); //Un-ordered set but unique
        // set.add(12);
        // set.add(23);
        // set.add(142);
        // set.add(2);
        // set.add(62);
        // set.add(122);
        // set.add(12); //repeated
        // set.add(12); //repeated

        // System.out.println(set);//Only unique values will be printed(i.e,extra 12
        // will be removed)

        // //remove
        // set.remove(12); //takes element as an input
        // System.out.println(set);

        // //contains
        // System.out.println(set.contains(12));

        // //isEmpty():returns true or false depending upon whether set is empty or not
        // System.out.println(set.isEmpty());

        // //size():returns size
        // System.out.println(set.size());

        // //clear():clears set
        // set.clear();
        // System.out.println(set);

        //<<---------New Class but same Interface---------->

        // Ordered Set and also unique (element remains in their order in which they are
        // added)
        // Set<Integer>set=new LinkedHashSet<>();
        // set.add(12);
        // set.add(23);
        // set.add(142);
        // set.add(2);
        // set.add(62);
        // set.add(122);
        // set.add(12); //repeated
        // set.add(12); //repeated

        // System.out.println(set);//Only unique values will be printed(i.e,extra 12
        // will be removed)

        // //remove
        // set.remove(12); //takes element as an input
        // System.out.println(set);

        // //contains
        // System.out.println(set.contains(12));

        // //isEmpty():returns true or false depending upon whether set is empty or not
        // System.out.println(set.isEmpty());

        // //size():returns size
        // System.out.println(set.size());
        // //clear():clears set
        // set.clear();
        // System.out.println(set);


        //<<---------New Class but same Interface---------->


        // Sorted Set and also unique (element will be stored in sorted order)
        // O(logN)
        // Set<Integer>set=new TreeSet<>();
        // set.add(12);
        // set.add(23);
        // set.add(142);
        // set.add(2);
        // set.add(62);
        // set.add(122);
        // set.add(12); //repeated
        // set.add(12); //repeated

        // System.out.println(set);//Only unique values will be printed(i.e,extra 12
        // will be removed)

        // //remove
        // set.remove(12); //takes element as an input
        // System.out.println(set);

        // //contains
        // System.out.println(set.contains(12));

        // //isEmpty():returns true or false depending upon whether set is empty or not
        // System.out.println(set.isEmpty());

        // //size():returns size
        // System.out.println(set.size());
        // //clear():clears set
        // set.clear();
        // System.out.println(set);






        // 6.Map
        // unordered map O(1)
        // Map<String,Integer>numbers=new HashMap<>();
        // numbers.put("One", 1);
        // numbers.put("two", 2);
        // numbers.put("three", 3);

        // System.out.println(numbers);

        // //ContainsKey:returns true or false
        // System.out.println(numbers.containsKey("four"));
        // System.out.println(numbers.containsValue(3));

        // //putIfAbsent():if key is not present then assign the given value to the
        // given key
        // numbers.putIfAbsent("four", 4);

        // System.out.println(numbers);

        // //traversal in map
        // for(Map.Entry<String,Integer>e:numbers.entrySet())
        // {
        // // System.out.println(e);//both(key and value)
        // System.out.println(e.getKey());//key
        // System.out.println(e.getValue());//value
        // }
        // //If we only want to traverse key
        // for(String key:numbers.keySet())
        // {
        // System.out.println(key);
        // }
        // //If we only want to traverse value
        // for(Integer val:numbers.values())
        // {
        // System.out.println(val);
        // }
        // //Remove:returns value or null
        // numbers.remove("one");
        // System.out.println(numbers);


        //<<---------New Class but same Interface---------->


        // Ordered map O(logN)(sorted on the basis of keys)
        // Map<String, Integer> numbers = new TreeMap<>();
        // numbers.put("one", 1);
        // numbers.put("two", 2);
        // numbers.put("three", 3);

        // System.out.println(numbers);

        // // Contains:returns true or false
        // System.out.println(numbers.containsKey("four"));
        // System.out.println(numbers.containsValue(3));

        // // putIfAbsent():if key is not present then assign the given value to the
        // given
        // // key
        // numbers.putIfAbsent("four", 4);

        // System.out.println(numbers);

        // // traversal in map
        // for (Map.Entry<String, Integer> e : numbers.entrySet()) {
        // // System.out.println(e);//both(key and value)
        // System.out.println(e.getKey());// key
        // System.out.println(e.getValue());// value
        // }
        // // If we only want to traverse key
        // for (String key : numbers.keySet()) {
        // System.out.println(key);
        // }
        // // If we only want to traverse value
        // for (Integer val : numbers.values()) {
        // System.out.println(val);
        // }
        // // Remove:returns value or null
        // numbers.remove("one");
        // System.out.println(numbers);





        // 7.Arrays Class(provides extra functionality to the primitive arr[])

        // int[] num = { 1, 4, 6, 7, 11, 34, 56, 67, 78, 112 };

        //binary search (must be sorted array)
        //returns index if key is found
        //else returns index where the key should be present in -ve + -1;

        // int index=Arrays.binarySearch(num, 12);
        // System.out.println("Index of 12:"+index);
        
        //sort
        // int[] number={1,34,12,456,76,88};
        // // Arrays.sort(number);
        // // for(Integer n:number)
        // // {
        // //     System.out.println(n);
        // // }

        // //fill:to fill values in arr
        // Arrays.fill(number, 100);
        // for(Integer n:number)
        // {
        //     System.out.println(n);
        // }



        //8. Collections class
        List<Integer>list=new ArrayList<>();
        list.add(4); 
        list.add(12); 
        list.add(12); 
        list.add(12); 
        list.add(12); 
        list.add(41); 
        list.add(44); 
        list.add(124); 
        list.add(431); 
        list.add(-4); 

        //Collections.min():returns minimum
        System.out.println("Minimum element:"+Collections.min(list));
        //Collections.max():returns maximum
        System.out.println("Maximum element:"+Collections.max(list));
        //frequency:returns frequency of element
        System.out.println(Collections.frequency(list, 12));
        //reverse list
        Collections.sort(list,Comparator.reverseOrder());
        System.out.println(list);
    }
}
