package com.example.QR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="qr")
public class Employee {
	@Id
    @Column(name="empno")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String time;
    private String attendance;
    private String number;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getTime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }
    public String getAttendance() {
        return attendance;
    }
    public void setattendance(String attendance) {
        this.attendance = attendance;
    }
    public String getNumber() {
    	return number;
    }
    public void setnumber(String number) {
        this.number = number;
    }

}
