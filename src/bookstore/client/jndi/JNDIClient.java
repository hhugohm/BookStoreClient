
package bookstore.client.jndi;

import bookstore.backend.api.BookstoreDAO;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author hhugohm
 */
public class JNDIClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        getBooksstoreDAO();
        
    }
    
    private  static BookstoreDAO getBooksstoreDAO(){
        BookstoreDAO bookstoreDAO = null;
        
        try{
            Context ctx = new InitialContext();
            bookstoreDAO= (BookstoreDAO)ctx.lookup("java:global/BookstoreBackend/BookstoreDAO");
            System.out.println("###### bokkstoreDAO: "+bookstoreDAO);
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    } 
    
}
