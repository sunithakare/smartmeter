package com.envision.common.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.common.database.objects.FileUploadTable;

@Repository
public interface FileUploadDAO extends JpaRepository<FileUploadTable, Long>  { 

}