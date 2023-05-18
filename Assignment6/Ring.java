import java.util.Scanner;

public class Ring {

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
    Process[] pArr;

    public Ring() {}

    public void initializeProcesses() {
        System.out.print("\nEnter number of processes : ");
        totalProcess = inputObj.nextInt();
        System.out.println(totalProcess + " processes are running.");
        pArr = new Process[totalProcess];
        int i = 0;
        int processId = 0;
        System.out.print("\nEnter the process id(s) : ");
        while (i < pArr.length) {
            processId = inputObj.nextInt();
            pArr[i] = new Process(processId);
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
            System.out.print("\nProcess : [" + i + "]" + ", Process id : [" + pArr[i].id + "]");
        }

        System.out.print("\nYour current coordinator is Process [" + (totalProcess - 1) + "]");
    }

    public void Election() {
        int flag = 1;
        do {
            System.out.print("\n\nEnter the process which need to be failed from [0 to " + (totalProcess - 1) + "]: ");
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
            int maxProcessId = pArr[electionProcess].id;
            int curr = getNextProcess(electionProcess);
            while (curr != electionProcess) {
                if (pArr[curr].isRunning && pArr[curr].id > maxProcessId) {
                    newCoordinator = curr;
                    maxProcessId = Math.max(maxProcessId, pArr[curr].id);
                }
                curr = getNextProcess(curr);
            }
            System.out.print("\nYour current coordinator is Process [" + newCoordinator + "]");
            System.out.print("\n enter 1 for re-election and 0 to exit : ");
            flag = inputObj.nextInt();
        } while (flag == 1);
    }

    public int getNextProcess(int currId) {
        return (pArr[(currId + 1) % totalProcess].isRunning ? (currId + 1) % totalProcess : (currId + 2) % totalProcess);
    }

    public static void main(String[] args) {
        Ring rObg = new Ring();
        rObg.initializeProcesses();
        rObg.Election();
    }
}
