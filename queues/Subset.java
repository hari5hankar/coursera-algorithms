public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (count >= k) {
                int j = StdRandom.uniform(0, count + 1);
                if (j < k) {
                    rq.dequeue();
                    rq.enqueue(input);
                }
            }else{
                rq.enqueue(input);
            }
            count++;
        }
        for(String s:rq){
            StdOut.println(s);
        }
    }

}