package com.codegym.cms.model;

import javax.persistence.*;

@Entity // sinh ra ORM 1 entity ( vd ở đây là bảng customers sẽ được tạo vào ORM)
@Table(name = "customers") // để khai báo 1 bảng trong CSDL
// câu lệnh truy vẫn động là trong bài thực hành @id, @genericedValue
//=: truyền vào tham số (name parameter)
//=?1 là thay vào vị trí position
@NamedQueries({@NamedQuery(name = "findAllCustomers",query = "select c from Customer c"),@NamedQuery(name = "findCustomerById",query = "select c from Customer c where id=?1")})
 //phần tử của mảng
//namedqueries là để khai báo khi có nhiều namequery
//@namequery cú pháp để khai báo 1 câu lệnh truy vấn tĩnh

public class Customer {

    @Id // quy định biến đầu tiên là khóa chính, để trước biến nào thì biến đó là khóa chính( Long id là khóa chính)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}