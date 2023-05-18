import java.util.Scanner;

public class Bully {
    static class Process {
        int id;
        boolean isRunning;

        Process(int id) {
            this.id = id;
            isRunning = true;
        }
    }

    int totalProcess;
    Scanner inputObj = new Scanner(System.in);
    Ring.Process[] pArr;

    public void initializeProcesses() {
        System.out.print("Enter number of processes : ");
        totalProcess = inputObj.nextInt();
        System.out.println(totalProcess + " processes are running.");
        pArr = new Ring.Process[totalProcess];
        int i = 0;
        int processId = 0;
        System.out.println("Enter the process id(s) : ");
        while (i < pArr.length) {
            processId = inputObj.nextInt();
            pArr[i] = new Ring.Process(processId);
            i++;
        }
        for (i = 0; i < pArr.length; i++) {
            for (int j = i + 1; j < pArr.length; j++) {
                if (pArr[i].id > pArr[j].id) {
                    int temp = pArr[i].id;
                    pArr[i].id = pArr[j].id;
                    pArr[j].id = temp;
                }
            }
        }
        for (i = 0; i < pArr.length; i++) {
            System.out.println("Process : [" + i + "]" + ", Process id : [" + pArr[i].id + "]");
        }

        System.out.println("Your current coordinator is Process [" + (totalProcess - 1) + "]");
    }

    public void Election() {
        int flag = 1;
        do {
            System.out.print("\nEnter the process which need to be failed from [0 to " + (totalProcess - 1) + "]: ");
            int failedProcess = inputObj.nextInt();
            pArr[failedProcess].isRunning = false;
            if (totalProcess == 1) {
                System.out.print("\nNo process running!");
                return;
            }
            if (totalProcess == 2) {
                System.out.print("\nYour current coordinator is Process [" + (failedProcess + 1) % totalProcess + "]");
                return;
            }
            System.out.print("\nEnter the process which initialized the election from [0 to " + (totalProcess - 1) + "]: ");
            int electionProcess = inputObj.nextInt();
            int newCoordinator = electionProcess;
            for(int i = electionProcess; i < pArr.length - 1; i++) {
                if(pArr[i].isRunning){
                    newCoordinator = Math.max(newCoordinator, currentElection(i));
                    System.out.println();
                }
            }
            System.out.print("\nYour current coordinator is Process [" + newCoordinator + "]");
            for(int i = newCoordinator - 1; i >= 0; i--) {
                System.out.print("\nProcess " + newCoordinator + " sends Coordinator message to Process " + i);
            }
            System.out.print("\nDo you want to send again? enter 1 for Yes and 0 for No : ");
            flag = inputObj.nextInt();
        } while (flag == 1);
    }
    public int currentElection(int currNode) {
        for (int i = currNode; i < pArr.length - 1; i++) {
            System.out.print("\nProcess " + currNode + " sends Election message to Process : " + (i + 1));
        }
        int newCoordinator = currNode;
        for (int i = currNode + 1; i < pArr.length; i++) {
            if (pArr[i].isRunning) {
                System.out.print("\nProcess " + i + " sends OK (" + i + ") message to Process " + currNode);
                newCoordinator = Math.max(newCoordinator, i);
            }
        }
        return newCoordinator;
    }
    public static void main(String[] args) {
        Bully bObj = new Bully();
        bObj.initializeProcesses();
        bObj.Election();
    }
}
