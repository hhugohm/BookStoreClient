
package bookstore.client.jndi;

import bookstore.backend.annotations.SuppressLogging;
import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.datamodel.Book;
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
       
        System.out.println(getBookById(1).toString());
        
    }
    
    private  static BookstoreDAO getBooksstoreDAO(){
        BookstoreDAO bookstoreDAO = null;
        
        try{
            Context ctx = new InitialContext();
            bookstoreDAO= (BookstoreDAO)ctx.lookup("java:global/BookstoreBackend/BookstoreDAO");
           // System.out.println("###### bokkstoreDAO: "+bookstoreDAO);
           
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return bookstoreDAO;
    } 
    
    
    public static Book getBookById(int id) {
        BookstoreDAO bookstoreDAO =getBooksstoreDAO();
        Book book = bookstoreDAO.getBookById(id);
        
     return book;
        
    }
    
}
