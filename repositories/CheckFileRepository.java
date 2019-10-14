package com.example.demo.repositories;

import com.example.demo.domain.CheckFile;
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
public interface CheckFileRepository extends CrudRepository<CheckFile, Long> {

    CheckFile findByfilename(String filename);

    Set<CheckFile> findAll();

    @Transactional
    @Modifying
    @Query(value = "update check_file c set hash =:newhash where c.id =:userid", nativeQuery = true)
    void updateCheckFile(@Param("newhash") String newhash, @Param("userid") int userid);

    @Query(value = "select * from check_file", nativeQuery = true)
    Set<CheckFile> findAllCheckFileNative();
}
