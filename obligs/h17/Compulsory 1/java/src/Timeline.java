import java.util.Scanner;
import java.util.Stack;

public class Timeline {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        Stack<String> previous = new Stack<>();
        Stack<String> next = new Stack<>();


        for (int i = 0; i < N; i++) {
            String cmd = sc.nextLine();
            if (cmd.equals("*back*") && !previous.empty()) {
                next.push(previous.pop());
            }
            else if (cmd.equals("*back*")) {
                System.out.println("You are currently on the first website");
                continue;
            }
            else if (cmd.equals("*forward*") && !next.empty()) {
                previous.push(next.pop());
                next.clear();
            }
            else if (cmd.equals("*forward*")) {
                System.out.println("You are currently on the last website");
                continue;
            }
            else {
                previous.push(cmd);
            }
            System.out.println(previous.peek());
        }
    }

}
