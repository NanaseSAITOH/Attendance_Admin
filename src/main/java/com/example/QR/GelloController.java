package com.example.QR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GelloController {
	
	int nowId=0;
    String message=null;
    String message2=null;
    String findWord=null;
    
    @Autowired
    EmployeeRepository empRepository; 
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @Transactional
    public String index(Model model) {
    	List<Employee> emplist = empRepository.findAll();
    	if(emplist.size()==0) {
    		message2="登録された勤怠はございません.";
    	}
    	model.addAttribute("msg",message2);
        model.addAttribute("employeelist",emplist);
        return "index";
    }   
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @Transactional
    public String search(Model model) {
        return "search";
    }
    @RequestMapping(value = "testform",method = RequestMethod.GET)
	public String text1(
			@RequestParam(name = "text1") String text1,
			@RequestParam(name = "number") String number,
			@RequestParam(name = "year") String year,
			@RequestParam(name = "month") String month,
			@RequestParam(name = "day") String day,
		Model model) {
    	String Date = year+"年"+month+"月"+day;
		Employee n = new Employee();
		
		//text1=escape.change(text1);
		
		List<Employee> emplist=empRepository.findSearch(text1,number,Date);
        model.addAttribute("employeelist", emplist);
		return "index";
	}
    @RequestMapping(value = "change",method = RequestMethod.GET)
	public String change(@RequestParam("id")Integer id,
		Model model) {
    	List<Employee> emplist=empRepository.findById1(id);
        model.addAttribute("employeelist", emplist);
        nowId=id;
		return "change";
	}
    
    
    @RequestMapping(value = "delete",method = RequestMethod.GET)
	public String delete(
			@RequestParam("id2")Integer id2,
			Model model) {
    	empRepository.delete(id2);
    	List<Employee> emplist=empRepository.findAll();
        model.addAttribute("employeelist", emplist);
		return "index";
	}
    /*
    @RequestMapping(value = "searchcomplete",method = RequestMethod.GET)
	public String searchcomplete(
			@RequestParam("colorid")Integer colorid,
			Model model) {
    	try {
    	if(empRepository.findComp(colorid).equals("blue")) {
    	empRepository.update2(0,"red",colorid);
    	}else {
    	empRepository.update2(1,"blue",colorid);
    	}
    	}catch(NullPointerException e) {
    	empRepository.update2(1,"blue",colorid);
    	}
    	
    	List<Employee> emplist=empRepository.findById2(findWord);
        model.addAttribute("employeelist", emplist);
		return "search";
	}*/
    @RequestMapping(value = "ch",method = RequestMethod.GET)
	public String ch(
			@RequestParam(name = "text1") String text1,
			@RequestParam(name = "year") String year,
			@RequestParam(name = "month") String month,
			@RequestParam(name = "day") String day,
			@RequestParam(name = "hour") String hour,
			@RequestParam(name = "munite") String munite,
			Model model) {
    	String Date = year+"年"+month+"月"+day+"日"+hour+"時"+munite+"分";
    	Escape escape = new Escape();
    	if(!(escape.error(text1).equals(""))) {
	    	model.addAttribute("msg","エラー");
	    	List<Employee> emplist=empRepository.findById1(nowId);
	        model.addAttribute("employeelist", emplist);
			return "change";
		}
    	empRepository.update(text1,Date,nowId);
    	List<Employee> emplist=empRepository.findAll(new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("employeelist", emplist);
		return "index";
	}
    /*
    @RequestMapping(value = "complete",method = RequestMethod.GET)
	public String complete(
			@RequestParam("colorid")Integer colorid,
			Model model) {
    	try {
    	if(empRepository.findComp(colorid).equals("blue")) {
    	empRepository.update2(0,"red",colorid);
    	}else {
    	empRepository.update2(1,"blue",colorid);
    	}
    	}catch(NullPointerException e) {
    	empRepository.update2(1,"blue",colorid);
    	}
    	
    	List<Employee> emplist=empRepository.findAll(new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("employeelist", emplist);
		return "index";
	}*/
    /*
    @RequestMapping(value = "se",method = RequestMethod.GET)
	public String search(@RequestParam(name = "text1") String text1,
		Model model) {
		if((text1.equals(""))) {
	    	model.addAttribute("msg","何か入力してください");
			return "search";
		}
    	findWord=text1;
    	Escape escape = new Escape();
    	text1=escape.change(text1);
    	List<Employee> emplist=empRepository.findById2(text1);
        model.addAttribute("employeelist", emplist);
        if(CollectionUtils.isEmpty(emplist)) {
        	System.out.println("empty");
        	message="対象のToDoは見つかりません.";
        }else {
        	message="ToDoが"+emplist.size()+"件見つかりました";
        }
        model.addAttribute("msg",message);
		return "search";
	}
    public void sortAll(){
    	
    }*/
    /*
     * CREATE TABLE `qr` (
  `empno` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(31) NOT NULL DEFAULT 0,
  `name` varchar(31) NOT NULL DEFAULT "TODO",
  `time` varchar(31) NOT NULL DEFAULT "2018-01-01",
  `attendance` varchar(10) NOT NULL DEFAULT "error",
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
sudo vi /etc/my.cnf
+ [mysqld]
+ character_set_server=utf8

insert into ASA.test
(todoname,untildate,complete)
values
('従業員A','10101001',0)
,('従業員B','10101001',0)
,('従業員C','10101002',0)
,('従業員D','10101003',0)
     * 
     */   
}
