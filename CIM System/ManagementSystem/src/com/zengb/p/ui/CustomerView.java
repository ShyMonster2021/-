package com.zengb.p.ui;

import com.zengb.p.bean.Customer;
import com.zengb.p.service.CustomerList;
import com.zengb.p.util.CMUtility;

public class CustomerView {
	private CustomerList customerList = new CustomerList(10);
	
	public CustomerView() {
		Customer customer = new Customer("王涛", '男', 23, "13212341234", "wt12@gamil.com");
		customerList.addCustomer(customer);
	}
	/**
	 * @Description 显示《客户信息管理软件》界面的方法
	 * @author iLee
	 * @date 2022/12/27
	 */
	public void enterMainMenu() {
		
		boolean isFlag = true;
		while(isFlag){
			System.out.println("\n-----------------客户信息管理软件-----------------\n");
			System.out.println("                   1 添 加 客 户");
			System.out.println("                   2 修 改 客 户");
			System.out.println("                   3 删 除 客 户");
			System.out.println("                   4 客 户 列 表");
			System.out.println("                   5 退       出\n");
			System.out.print("                   请选择(1-5)：");

			char menu = CMUtility.readMenuSelection();
			switch (menu) {
			case '1'://此处记得用char字符类型
				addNewCustomer();//添加客户
				break;
			
			case '2':
				modifyCustomer();//修改客户信息
				break;
			
			case '3':
				deleteCustomer();//删除客户信息
				break;
			
			case '4':
				listAllCustomers();//列出客户信息
				break;
			case '5':
				//System.out.println("退出");
				System.out.println("确认是否退出(Y/N)：");
				char isExit = CMUtility.readConfirmSelection();
				if(isExit == 'Y') {
					isFlag = false;
				}
			
			}
			
		}
		
	}
	
	/**
	 * @Description 添加客户的操作
	 * @author iLee
	 * @date 2022/12/27
	 */
	private void addNewCustomer() {
		//System.out.println("添加客户的操作");
		System.out.println("----------------------添加客户------------------------------");
		System.out.println("姓名：");
		String name = CMUtility.readString(10);
		System.out.println("性别：");
		char gender = CMUtility.readChar();
		System.out.println("年龄：");
		int age = CMUtility.readInt();
		System.out.println("电话：");
		String phone = CMUtility.readString(13);
		System.out.println("邮箱：");
		String email = CMUtility.readString(30);
		
		//将上述数据分装到对象中
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		if (isSuccess) {
			System.out.println("--------------------添加完成-----------------------");
		}else {
			System.out.println("--------------------客户已满-----------------------");
		}
		
		
	}
	
	/**
	 * @Description 修改客户信息的操作
	 * @author iLee
	 * @date 2022/12/27
	 */
	private void modifyCustomer() {
		//System.out.println("修改客户信息的操作");
		System.out.println("--------------------修改客户-----------------------");
		
		Customer cust;
		int number;
		
		for (; ; ) {
			
			System.out.println("请选择待修改客户编号（-1退出）：");
			number = CMUtility.readInt();
			
			if (number == -1) {
				return;
			}
			
			cust = customerList.getCustomer(number-1);
			if (cust == null) {
				System.out.println("无法找到指定的客户！");
			}else {
				break;
			}
		}
		
		//修改客户信息
		System.out.println("姓名（"+cust.getName()+"):");
		String name = CMUtility.readString(10, cust.getName());
		
		System.out.println("性别（"+cust.getGender()+"):");
		char gender = CMUtility.readChar(cust.getGender());
		
		System.out.println("年龄（"+cust.getAge()+"):");
		int age = CMUtility.readInt(cust.getAge());
		
		System.out.println("电话（"+cust.getPhone()+"):");
		String phone = CMUtility.readString(13, cust.getPhone());
		
		System.out.println("邮箱（"+cust.getEmail()+"):");
		String email = CMUtility.readString(30, cust.getEmail());
		
		Customer newCust = new Customer(name, gender, age, phone, email);
	
		boolean isRepalaced = customerList.replaceCustomer(number-1, newCust);
		if (isRepalaced) {
			System.out.println("------------------------修改完成--------------------------");
		}else {
			
			System.out.println("------------------------修改失败--------------------------");
		}
	}
	
	/**
	 * @Description 删除客户的操作
	 * @author iLee
	 * @date 2022/12/27
	 */
	private void deleteCustomer() {
//		System.out.println("删除客户的操作");
		System.out.println("--------------------删除客户-----------------------");
		
		System.out.println("请选择删除客户编号(-1退出)：");
		int number = CMUtility.readInt(); 
	}
	
	/**
	 * @Description 显示客户列表的操作
	 * @author iLee
	 * @date 2022/12/27
	 */
	private void listAllCustomers() {
//		System.out.println("显示客户列表的操作");
		System.out.println("-------------------------------------------客户列表------------------------------------------------------");
		System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
		
		int total = customerList.getTotal();
		if (total == 0) {
			System.out.println("没有客户记录！");
		} else {
			//System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] custs = customerList.getAllCustomers();
			for (int i = 0; i < custs.length; i++) {
				Customer cust = custs[i];
				System.out.println((i+1)+"\t" 
				+ cust.getName() + "\t" 
				+ cust.getGender() + "\t" 
				+ cust.getAge() + "\t" + cust.getPhone() + "\t" + cust.getEmail() );
				
			}
		}
		System.out.println("-----------------------------------------客户列表完成------------------------------------------------------");
	}
	
	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();
	}
	
	
	
	

}
