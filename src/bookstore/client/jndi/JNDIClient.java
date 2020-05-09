
package bookstore.client.jndi;

import bookstore.backend.annotations.SuppressLogging;
import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.ShoppingCartService;
import bookstore.backend.dao.BookstoreDAOImpl;
import bookstore.backend.datamodel.Book;
import java.util.List;
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
        //System.out.println("-----------------------------------------");
        //update(1);
        //System.out.println(getBookById(1).toString());
        
        //add();
        
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
    
    private static void update(int idBook){
         BookstoreDAO bookstoreDAO = getBooksstoreDAO();
         Book book= bookstoreDAO.getBookById(idBook);
         book.setTitle("My Book 1 U1");
         bookstoreDAO.update(book);
    }
    
    
    private static void add(){
        BookstoreDAO bookstoreDAO = new BookstoreDAOImpl();
        ShoppingCartService shoppingCartService= getShoppingCartService();
        
        int ids[] = {1,2,3,4,5};
        for(int id: ids){
            Book book = bookstoreDAO.getBookById(id);
            shoppingCartService.add(book);
            List<Book> books = shoppingCartService.getItems();
            System.out.println("item: " + books);
            try{
                Thread.currentThread().sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    private  static ShoppingCartService getShoppingCartService(){
        ShoppingCartService shoppingCartService = null;
        
        try{
            Context ctx = new InitialContext();
            shoppingCartService= (ShoppingCartService)ctx.lookup("java:global/BookstoreBackend/ShoppingCartIService");
            //System.out.println("###### ShoppingCartService: "+shoppingCartService);
           
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return shoppingCartService;
    } 
    
}
