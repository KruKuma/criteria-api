package com.example.eclipselink.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.eclipselink.entity.Employee;

public class CriteriaAPI {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("EclipseLink_API");
        EntityManager entityManger = emFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManger.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Employee> from = criteriaQuery.from(Employee.class);
        
        // Select all records
        System.out.println("Selct all records");
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManger.createQuery(select);
        List<Object> resultList = typedQuery.getResultList();
        
        for(Object o : resultList) {
            Employee e = (Employee)o;
            System.out.println("EID: " + e.getEid() + " Ename: " + e.getEname());
        }
        
        // Ordering the records
        System.out.println("Select all records by follow ordering");
        CriteriaQuery<Object> select1 = criteriaQuery.select(from);
        select1.orderBy(criteriaBuilder.asc(from.get("ename")));
        TypedQuery<Object> typedQuery1 = entityManger.createQuery(select);
        List<Object> resultList1 = typedQuery1.getResultList();
        
        for(Object o : resultList1) {
            Employee e = (Employee)o;
            System.out.println("EID: " + e.getEid() + " Ename " + e.getEname());
        }
        
        entityManger.close();
        emFactory.close(); 
        
        
    }
}
