
package bookstore.client.jndi;

import bookstore.backend.annotations.SuppressLogging;
import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.ShoppingCartService;
import bookstore.backend.dao.BookstoreDAOImpl;
import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.enums.Availability;
import bookstore.backend.datamodel.enums.BookFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        
        System.out.println(getBookById(12).toString());
       // System.out.println("-----------------------------------------");
       //update(12);
        //System.out.println(getBookById(12).toString());
        
        //add();
        /*Book book=buildBook();
        System.out.println(book);
        book.toString();
        insert(book);*/
        
        //delete(11);
        
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
     private static void insert(Book book){
         BookstoreDAO bookstoreDAO = getBooksstoreDAO();
        // Book book = buildBook();
         bookstoreDAO.insert(book);
    }
      private static Book buildBook(){
        Book book = new Book();
         book.setTitle("My Book 1");
         book.setDescription("This is my Book 1");
         book.setAuthor("Author 1");
         book.setPublisher("Publisher 1");
         book.setPublishedDate(new Date());
         book.setLanguage("English");
         book.setNumberOfPages(111);
         book.setFormat(BookFormat.PAPERBACK);
         book.setPrice(11);
         book.setAvailability(Availability.IN_STOCK);
         book.setStockQuantity(10);
        return book;
    }
    
    public static Book getBookById(int id) {
        BookstoreDAO bookstoreDAO =getBooksstoreDAO();
        Book book = bookstoreDAO.getBookById(id);
        
     return book;
        
    }
    
    private static void delete(int idBook){
         BookstoreDAO bookstoreDAO = getBooksstoreDAO();
         Book book= bookstoreDAO.getBookById(idBook);
         
         //boolean bandera= (Optional.ofNullable(book).isPresent())?true:false;
         
         Optional<Book> optionalBook=Optional.ofNullable(book);
         optionalBook.ifPresent(bookLamda ->{
             bookstoreDAO.delete(bookLamda);
         });
         //optionalBook.ifPresent(bookstoreDAO::delete); 
        
        /* if( Optional.ofNullable(book).isPresent()){
             bookstoreDAO.delete(book);
         }else{
               System.out.println("NO EXISTE BOOK ID= " +idBook);
         }*/
        
        
    }
    
    private static void update(int idBook){
         
         BookstoreDAO bookstoreDAO = getBooksstoreDAO();
         Book book= bookstoreDAO.getBookById(idBook);
         /*try{
                Thread.currentThread().sleep(25000);
            }catch(Exception e){
                e.printStackTrace();
            }*/
         book.setTitle("My Book 1 U3");
         try{
              bookstoreDAO.update(book);
         }catch(Exception e){
              System.out.println("ERROR: ");
             System.out.println( e.getCause());
         }
        
         
         System.out.println("### update exceuted....");
          
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
