package Java_IO.Pipes.Text_Filter;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
 * Main class: Connects the producer and consumer with a pipe.
 * Simulates real-time message filtering.
 */



public class TextFilterApp
{
    public static void main(String[] args) throws Exception
    {

        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        TextProducer producer = new TextProducer(pipedOut);
        TextConsumer consumer = new TextConsumer(pipedIn);

        producer.start();
        consumer.start();


    }

}
