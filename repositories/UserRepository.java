package com.example.demo.repositories;

import com.example.demo.domain.CheckFile;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.soap.Text;
import java.util.Collection;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    @Query(value = "select u.emailAdress, u.cellNumber, u.sendEmail, u.sendText from User u JOIN Watchlist w where u.UserId =: w.UserId ", nativeQuery = true)
    Set<User> findAllUserNative();

}