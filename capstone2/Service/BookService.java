package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Book;
import com.example.capstone2.Model.Customer;
import com.example.capstone2.Model.Publisher;
import com.example.capstone2.Repository.BookRepository;
import com.example.capstone2.Repository.CustomerRepository;
import com.example.capstone2.Repository.OrdersRepository;
import com.example.capstone2.Repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final OrdersRepository ordersRepository;
    private final OrdersService ordersService;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public void addBook(Book book){
        Publisher publisher=publisherRepository.findPublisherByPubId(book.getPubId());
        if(publisher==null){
            throw new ApiException("wrong publisher id");
        }
        bookRepository.save(book);
    }
    public void updateBook(Integer id,Book book){
        Book oldBook=bookRepository.findBookByBookId(id);
        if(oldBook==null){
            throw new ApiException("wrong book id");
        }
        Publisher publisher=publisherRepository.findPublisherByPubId(book.getPubId());
        if(publisher==null){
            throw new ApiException("wrong publisher id");
        }
        oldBook.setPubId(book.getPubId());
        oldBook.setTitle(book.getTitle());
        oldBook.setPriceForOneDay(book.getPriceForOneDay());
        oldBook.setQuantity(book.getQuantity());
        oldBook.setIsAvailable(book.getIsAvailable());
        oldBook.setCategory(book.getCategory());
        oldBook.setLifeStage(book.getLifeStage());
        oldBook.setLanguage(book.getLanguage());
        oldBook.setPublishDate(book.getPublishDate());

        bookRepository.save(oldBook);
    }
    public void deleteBook(Integer id){
        Book book=bookRepository.findBookByBookId(id);
        if(book==null){
            throw new ApiException("wrong book id");
        }
        bookRepository.delete(book);
    }
    //1
    public List<Book> findBookBetweenDate(LocalDate date1,LocalDate date2){
        List<Book>books=bookRepository.findBookBetweenDate(date1, date2);
        if(books.isEmpty()){
            throw new ApiException("there is no books in this range");
        }
       return books;
    }

    //2
    public List<Book>findBooksByIsAvailableAndCategoryPref(Integer customerId){
        Customer customer=customerRepository.findCustomerByCustomerId(customerId);
        if(customer==null){
            throw new ApiException("wrong customer id");
        }
        List<Book>booksAvailableAndCatPref=bookRepository.findBooksByIsAvailableAndCategoryPref(customer.getCatPref());
        if (booksAvailableAndCatPref.isEmpty()){
            throw new ApiException("sorry there is no available books for this category");
        }
        return booksAvailableAndCatPref;
    }
    //3
    public List<Book>findBooksByAge(Integer customerId){
        Customer customer=customerRepository.findCustomerByCustomerId(customerId);
        if(customer==null){
            throw new ApiException("wrong customer id");
        }
        if(customer.getAge()<=12){
            List<Book>books1= bookRepository.findBookByLifeStage("Childhood");
            if(books1.isEmpty()){
                throw new ApiException("sorry there is no books");
            }
            return  books1;
        }
        else if(customer.getAge()>12&&customer.getAge()<=18){
            List<Book>books2=bookRepository.findBookByLifeStage("Adolescence");
            if(books2.isEmpty()){
                throw new ApiException("sorry there is no books");
            }
            return  books2;
        }
        else {
            List<Book>books3=bookRepository.findBookByLifeStage("Adulthood");
            if(books3.isEmpty()){
                throw new ApiException("sorry there is no books");
            }
            return  books3;
        }
    }
    //4
    public List<Book> findBookByBest(){
        List<Book>books=bookRepository.findBookByBestRating();
        if(books.isEmpty()){
            throw new ApiException("there is no books");
        }
        return books;
    }
    //5
    public void discountBook(){
        List<Book> book=bookRepository.findBookByQuantityAndRentedTimes();
        if(book.isEmpty()){
            throw new ApiException("there is no books");
        }
        for(int i=0;i<book.size();i++){
            book.get(i).setPriceForOneDay(book.get(i).getPriceForOneDay()/2);
            book.get(i).setOnDiscount(true);
            bookRepository.save(book.get(i));
        }
    }
    //6
    public List<Book> booksOnDiscount(){
        List<Book>books=bookRepository.findBooksOnDiscount();
        if(books.isEmpty()){
            throw new ApiException("there is no books");
        }
        return books;
    }
    //7
    public List<Book> displayByLanguage(String language){
        List<Book>books=bookRepository.findBookByLanguage(language);
        if(books.isEmpty()){
            throw new ApiException("there is no books");
        }
        return books;
    }
    //8
    public void reStockOneBook(Integer bookId,Integer quantity){
        Book book=bookRepository.findBookByBookId(bookId);
        if(book==null){
            throw new ApiException("wrong id");
        }
        book.setQuantity(book.getQuantity()+quantity);
        bookRepository.save(book);
    }
    //9
    public Book findByTitle(String title){
        Book book=bookRepository.findBookByTitle( title);
        if(book==null){
           throw  new ApiException("book not found");
        }
        return book;
    }
    //10
    public void deleteBooksWithOneRating(){
        List<Book> books=bookRepository.findBookByRating();
        if(books.isEmpty()){
            throw new ApiException("there is no books");
        }
        for(int i=0;i<books.size();i++){
            deleteBook(books.get(i).getBookId());
        }
    }
    //11
    public List<Book> findBookByPub(Integer pubId){
        Publisher publisher=publisherRepository.findPublisherByPubId(pubId);
        if(publisher==null){
            throw new ApiException("wrong publisher id!");
        }
        List<Book>books=bookRepository.findBookByPubId(pubId);
        if(books.isEmpty()){
            throw new ApiException("there is no books for this publisher");
        }
        return books;
    }
    //12
    public List<Book> findBookByCategory(String category){
        List<Book>books=bookRepository.findBookByCategory(category);
        if(books.isEmpty()){
            throw new ApiException("sorry there is no books for this category");
        }
        return books;
    }
    //13
    public List<Book> findBookByIsAvailable(){
        List<Book>books=bookRepository.findBookByIsAvailableTrue();
        if(books.isEmpty()){
            throw new ApiException("sorry there is no available books");
        }
        return books;
    }
    //14
    public List<Book> booksPubBeforeDate(LocalDate date){
        List<Book>books=bookRepository.findBookPublishedBeforeDate(date);
        if(books.isEmpty()){
            throw new ApiException("sorry there is no books before this date");
        }
        return books;
    }
    //15
    public List<Book> booksPubAfterDate(LocalDate date){
        List<Book>books=bookRepository.findBookPublishedAfterDate(date);
        if(books.isEmpty()){
            throw new ApiException("sorry there is no books after this date");
        }
        return books;
    }
    //16
    public Book findMostRented(){
        List<Book>books=bookRepository.findAll();
        if(books.isEmpty()){
            throw new ApiException("no books");
        }
        Book b=new Book();
        for(int i=0;i<books.size();i++){
            if(books.get(i).getRentedTimes()>b.getRentedTimes()){
                b=books.get(i);
                bookRepository.save(b);
            }
        }
        return b;
    }
    //17
    public List<Book> findBookNotAvailable(){
        List<Book>books=bookRepository.findBookByIsAvailableFalse();
        if(books.isEmpty()){
            throw new ApiException("there is no books");
        }
        return books;
    }
}
