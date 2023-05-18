import java.rmi.server.*;
public class SearchQuery extends UnicastRemoteObject implements Search
{
    SearchQuery() throws Exception
    {
        super();
    }

    // Implementation of the query interface
    public int add(int a, int b) throws Exception
    {
        return a + b;
    }
}