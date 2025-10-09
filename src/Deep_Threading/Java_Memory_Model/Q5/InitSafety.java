package Deep_Threading.Java_Memory_Model.Q5;
import java.util.*;

/*

states is a final field, fully built in the constructor.

The JMM’s initialization safety says: for a properly constructed object,
 all threads see correct values of its final fields,
  and also the data reachable only through those finals (the map contents).
   Those writes are “frozen” when the constructor finishes;
    later readers see at least that frozen state.

    Publish is volatile so a write by one thread is visible to a later read by another,
     and a volatile write happens-before a subsequent volatile read of the same variable.

     Thread b = new Thread(() -> {
  while (!publish) {}            // volatile read; waits for A’s signal
  System.out.println(ref.get("alaska"));
});
It spins until it observes publish == true. That volatile read is the “receiver” of A’s volatile write,
 so there is a happens-before from A’s work into B after the loop.

Therefore, B is guaranteed to see:

ref as non-null (the assignment is visible), and

the fully constructed contents of SafeStates (constructor writes are frozen and visible by init safety).

Why it always prints AK

The volatile flag gives you ordering/visibility from A → B for the reference itself.

Initialization safety gives you the correct contents of the object once B has the reference,
 even if the publication was racy. Put together: B will print AK every time.

 Important caveats (what would break it)

If states were not final, or if you mutated the map after construction,
 you would need normal synchronization to read/write it safely.
  Initialization safety only covers the as-constructed state.





* */
public class InitSafety
{
    static class SafeStates
    {

        private final Map<String,String> states;

        SafeStates(){
            states = new HashMap<>();
            states.put("alaska","AK") ;  states.put("wyoming","WY");
        }

        String get(String s)
        {
            return states.get(s);
        }
    }

    static volatile boolean publish ;
    static SafeStates ref;

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(() -> {
            ref = new SafeStates();   // 1) construct & assign
            publish = true;           // 2) volatile write (signal)
        });

        Thread b = new Thread(() -> {
            while (!publish) {}            // volatile read; waits for A’s signal
            System.out.println(ref.get("alaska"));
        });


        a.start(); b.start(); a.join(); b.join(); // Guaranteed to print "AK"

    }

}
