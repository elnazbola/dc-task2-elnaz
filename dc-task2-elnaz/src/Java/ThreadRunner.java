package Java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.File; 

/**
 * Processor of HTTP request.
 */

public class ThreadRunner
        extends Thread
{
    private final ThreadSafeQueue queue;

    public ThreadRunner(ThreadSafeQueue queue) {
        this.queue = queue;
    }

    public void StartProcessor(Input input) throws IOException {
        Processor proc = new Processor(input.socket, input.request);
        proc.process();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Wait for new element.
                Input element = queue.pop();

                // Stop consuming if null is received.
                if (element == null) {
                    return;
                }

                // Process element.
                StartProcessor(element);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}