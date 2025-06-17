package Java_Concurrency.Q2;


import java.util.*;
import java.util.concurrent.*;

//  Online Exam System with CountDownLatch
//        Implement an online exam system where all students must wait for the exam to start.
//        The exam starts when the instructor gives the signal.

// Main class to simulate exam system


public class OnlineExamSystem {

    // CountDownLatch allows students to wait until exam starts
    private static final CountDownLatch latch = new CountDownLatch(1);

    static class Student implements Runnable {
        private final String studentName;

        public Student(String studentName) {
            this.studentName = studentName;
        }

        @Override
        public void run() {
            try {
                System.out.println(studentName + " waiting for exam to start...");
                latch.await();
                System.out.println(studentName + " started the exam.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Registering students
        for (int i = 1; i <= 5; i++) {
            executor.submit(new Student("Student " + i));
        }

        Thread.sleep(3000);  // Simulating exam preparation
        System.out.println("Instructor: Exam Started!");
        latch.countDown();  // Instructor starts the exam

        executor.shutdown();
    }


    /*

   All students wait on CountDownLatch.
The instructor releases them simultaneously.
Real-world: Controlled synchronized start of tasks.

    */
}

