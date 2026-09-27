public class StackQueueTest {
    private static int checks = 0;

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError("Failed: " + message);
    }

    public static void main(String[] args) {
        ActionStack stack = new ActionStack();
        check(stack.isEmpty(), "stack starts empty");
        check(stack.pop() == null, "empty stack pop returns null");
        stack.push("first");
        stack.push("second");
        check("second".equals(stack.pop()), "stack is LIFO");
        check("first".equals(stack.pop()), "older item pops second");
        check(stack.isEmpty(), "stack empty after pops");

        ServiceQueue queue = new ServiceQueue();
        check(queue.isEmpty(), "queue starts empty");
        check(queue.dequeue() == null, "empty queue returns null");
        queue.enqueue("first");
        queue.enqueue("second");
        check("first".equals(queue.dequeue()), "queue is FIFO");
        check("second".equals(queue.dequeue()), "second item dequeues second");
        check(queue.isEmpty(), "queue empty after dequeues");
        queue.enqueue("after-empty");
        check("after-empty".equals(queue.dequeue()), "queue works after empty");

        System.out.println("PASS: " + checks + " stack/queue checks.");
    }
}