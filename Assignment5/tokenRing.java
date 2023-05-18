import java.util.*;

class tokenRing {
    public static void main(String[] args) throws Throwable {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the num of processes : ");
        int n = scan.nextInt();
        int token = 0;
        int ch = 0, flag = 0;
        System.out.print("\nYour token ring is : ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.print(0);
        do {
            System.out.print("\nEnter sender : ");
            int s = scan.nextInt();
            System.out.print("Enter receiver : ");
            int r = scan.nextInt();
            System.out.print("Enter Data : ");
            int data;
            data = scan.nextInt();
            System.out.println("Token passing : ");
            for (int i = token; i != s; i = (i + 1) % n) {
                System.out.print(i + " -> ");
            }
            System.out.print(s);
            System.out.print("\nSender " + s + " sending data to Process " + (s + 1) % n);
            int i = (s + 1) % n;
            for (; i != r; i = (i + 1) % n) {
                System.out.print("\nData forwarded by Process " + i + " to Process " + (i + 1) % n);
            }
            System.out.print("\nReceiver " + r + " received the data. \n");
            token = s;
            do {
                if (flag == 1) System.out.print("Invalid Input!!...");
                System.out.print("\nDo you want to send again? enter 1 for Yes and 0 for No : ");
                ch = scan.nextInt();
                flag = ((ch != 1 && ch != 0) ? 1 : 0);
            } while (ch != 1 && ch != 0);
        } while (ch == 1);
    }
}