import java.util.*;
import java.lang.*;
import java.io.*;


class main
{
	public static void main (String[] args){
		var s1= new Student("A",50);
		var s2= new Student("B",51);
		
		System.out.println(s1.name + s1.roll + " " + s1.enrollmentNo + " " + s1.feeCalc());
System.out.println(s2.name + s2.roll + " " + s2.enrollmentNo + " " + s2.feeCalc());
	}
}

class Student
{
    String name;
    int roll;
    int enrollmentNo;
    static int seq = 0;
    private int fees = 0;
    
    public Student(String n, int r)
    {
        seq += 1;
        this.name = n;
        this.roll = r;
        this.enrollmentNo = seq;
    }
    public int feeCalc()
    {
        if(name == "A") this.fees = 100;
        else this.fees = 200;
        return this.fees;
    }
}
