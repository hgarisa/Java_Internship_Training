package Deep_Threading.Java_Memory_Model.Q8;

import java.util.concurrent.*;

/*

Step-by-step flow

Queue creation:

1.You now have an empty mailbox that can hold exactly one Result.

2.Worker thread (producer):

Thread worker = new Thread(() -> {
  int s = 0; for (int i = 1; i <= 1_000; i++) s += i;
  q.offer(new Result(s)); // put HB take
});
worker.start();

It computes 1 + 2 + … + 1000 into s, builds a Result(s), then offers it to the queue.

Because the queue is empty (capacity 1), offer succeeds immediately and puts the object into the mailbox.
*
take() blocks if the queue is empty,
waiting until a producer supplies an element.
 Here, it will unblock when the worker offers the Result.

After take(), you print the sum.




*
*
* */
public class Piggybacking
{
    static class Result
    {
        int sum;
        Result(int s)
        {
            sum=s;
        }
        public static void main(String[] args) throws InterruptedException {

            BlockingQueue<Result> q = new ArrayBlockingQueue<>(1);

            Thread worker = new Thread(() ->
            {
                int s = 0;
                for (int i=1;i<=1_000;i++) s+=i;


                try {
                    q.put(new Result(s));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
            worker.start();
            Result r = q.take();      // sees fields as worker left them
            System.out.println(r.sum);

        }

    }
}
