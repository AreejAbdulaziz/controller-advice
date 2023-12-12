package com.example.capstone2.Repository;

import com.example.capstone2.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findBookByBookId(Integer id);
    @Query("select b from Book b where b.publishDate between ?1 and ?2")
    List<Book> findBookBetweenDate(LocalDate date1, LocalDate date2);
    @Query("select b from Book b where b.category =?1 and b.rating >=?2")
    List<Book> findByCategoryAndRate(String category,Integer rating);
    @Query("select b from Book b where b.isAvailable=true and b.category=?1")
    List<Book> findBooksByIsAvailableAndCategoryPref(String catPref);
    List<Book> findBookByLifeStage(String lifeStage);
    @Query("select b from Book b where b.rating=5")
    List<Book> findBookByBestRating();

    @Query("select b from Book b where b.quantity>=200 and b.rentedTimes<=5")
    List<Book> findBookByQuantityAndRentedTimes();
    @Query("select b from Book b where b.onDiscount=true")
    List<Book> findBooksOnDiscount();

    List<Book> findBookByLanguage(String language);

    @Query("select b from Book b where b.quantity=0")
    List<Book> findBookByQuantity();

    @Query("select b from Book b where b.title=?1")
    Book findBookByTitle(String title);
    @Query("select b from Book b where b.rating<=1")
    List<Book> findBookByRating();
    List<Book> findBookByPubId(Integer pubId);
    List<Book> findBookByCategory(String category);
    List<Book> findBookByIsAvailableTrue();
    @Query("select b from Book b where b.publishDate<?1")
    List<Book> findBookPublishedBeforeDate(LocalDate date);
    @Query("select b from Book b where b.publishDate>?1")
    List<Book> findBookPublishedAfterDate(LocalDate date);

    List<Book>findBookByIsAvailableFalse();

}
