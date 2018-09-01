package nl.kennemersoft;

public class Pojo {

	public String name;
	public int age;
	public double weight;
	public Pojo nextPojo;
	
	public Pojo() {};
	
	public Pojo(String name, int age, double weight, Pojo nextPojo) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.nextPojo = nextPojo;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Pojo getNextPojo() {
		return nextPojo;
	}
	public void setNextPojo(Pojo nextPojo) {
		this.nextPojo = nextPojo;
	}
	
	
	
	
}
