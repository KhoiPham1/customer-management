package com.codegym.cms.repository.impl;
// c chỉ làm việc với cở sở dữ liệu . nó là lớp persistence Layer
// presentation là lớp gần với người dùng
// bussines layer lớp nghiệp vụ thực thi.

import com.codegym.cms.model.Customer;
import com.codegym.cms.repository.CustomerRepository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("ALL")
@Transactional // hỗ trợ xử lý giao dịch trên jpa(thêm , sửa xóa )
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    // trong .net là datacontext, trong Spring MVC , java là persistenceContext và chính là ORM
    // xây dựng quan hệ giữa các object , quản lí các entity trong thời gian chạy.
    private EntityManager em; // 1 thể hiện của persistenceContext, 2 dòng luôn đi kèm nhau để khai báo 1 CSDL.
//@PersistenceContext và private EntityManager em luôn đi kèm với nhau trong file impl (PersistenceLayer) đẻ khai báo 1 CSDL)
    @Override
    public List<Customer> findAll() {
        //TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        //em.createquery là câu lệnh truy vấn động
        //TypedQuery : kiểu dữ liệu trả về <>
        Query query = em.createNamedQuery("findAllCustomers");//câu lệnh truy vấn tĩnh
        return query.getResultList();
    }
// học cú pháp JPA, JPA là 1 interface
    //hibernate là 1 implement của JPA
    @Override
    public Customer findById(Long id) {
        // =: để truyền vào tham số
        //query.setParameter truyền vào là đối số
       // TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
        //typedquery là cú pháp của JPA(hibernate)
        TypedQuery<Customer> query = (TypedQuery<Customer>) em.createNamedQuery("findCustomerById");

        query.setParameter(1, id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Customer model) {
        if(model.getId() != null){
            em.merge(model);// update , merger là phần để truy xuất dữ liệu qua entity manager
        } else {
            em.persist(model); // thêm mới , persist là phần để truy xuất dữ liệu qua entity manager
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if(customer != null){
            em.remove(customer);
        }
    }
}