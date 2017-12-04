import java.util.*;

public class ReversePolish {

    public static void main(String[] args) {
        String line = new Scanner(System.in).nextLine();

        Stack<String> operandStack = new Stack<>();
        Set<String> validOperands = new HashSet<>(Arrays.asList("+", "*"));

        for (String o : line.split(" ")) {
            if (validOperands.contains(o)) {
                String sndNumber = operandStack.pop();
                operandStack.push("( " + operandStack.pop() + " " + o + " " + sndNumber + " )");
            }
            else {
                operandStack.push(o);
            }
        }

        String result = operandStack.pop();
        System.out.println(result.substring(2, result.length()-2));

    }

}
