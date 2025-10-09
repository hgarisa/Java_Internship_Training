package Deep_Threading.Java_Memory_Model.Q3;
/*

No sync at all (plain race)

Both threads run at the same time;
the consumer might read before the producer’s write becomes visible.

 Why 0? With no ordering, the consumer can execute (and even finish)
before the producer’s write hits main memory.

* */
public class NoSync {
    static int shared = 0;

    public static void main(String[] args) throws Exception {
        Thread producer = new Thread(() -> {

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        shared = 42 ;
        });
        Thread consumer = new Thread(() -> System.out.println(shared)); // could print 0


        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    /*

  Why you saw 42 (most of the time)

Scheduling luck: the producer often runs first (or quickly enough) so the consumer reads after the write.

Strong-ish hardware behavior:
 on common CPUs (like x86),
 the default memory ordering is fairly strong,
 so the “read-before-write-becomes-visible” outcome (printing 0)
  is possible per the JMM but often rare in practice.

Small program effect: very little work happens between start and read,
 so timing tends to line up producer → consumer.

Key idea: with no happens-before, both outcomes are legal.
Seeing 42 doesn’t prove correctness;
 it just means the race didn’t bite you this time.



    * */
}
