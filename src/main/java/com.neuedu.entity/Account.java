package com.neuedu.entity;

/**�˺�*/
public class  Account{
	
	public  int  accountId;
	public  String  username;
	public  String  password;
	public  String  ip;
	public  String  sex;
	public  int Stock; 
	public  String token;
	
	
	
    public Account(int accountId, String username, String password, String ip, String sex, int stock, String token) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.sex = sex;
		Stock = stock;
		this.token = token;
	}
	public Account(int accountId, String username, String password, String ip, String sex) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.sex = sex;
	}
	/**�����ض����˺Ŷ���*/
	public  Account(){}
	public  Account(int  accountId,String username,String password){
		this.accountId=accountId;
		this.username=username;
		this.password=password;
	}
	
	public  int  getAccountId(){
		return  accountId;
	}
	public  void  setAccountId(int accountId){
		this.accountId=accountId;
	}
	
	
	public  String  getUsername(){
		return username;
	}
	public  void  setUsername(String username){
		this.username=username;
	}
	
	public  String  getPassword(){
		return password;
	}
	public  void  setPassword(String password){
		this.password=password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}