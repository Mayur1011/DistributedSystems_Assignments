import java.rmi.*;
public class ClientRequest
{
    public static void main(String[] args) throws Exception
    {
        Search obj = (Search) Naming.lookup("Add");
        int n = obj.add(5, 5);
        System.out.println("Addition is : " + n);

    }
}