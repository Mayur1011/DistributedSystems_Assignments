import java.rmi.*;
public class SearchServer
{
    public static void main(String[] args) throws Exception
    {
        SearchQuery obj = new SearchQuery();
        Naming.rebind("Add",obj);
        System.out.println("Server started");
    }
}