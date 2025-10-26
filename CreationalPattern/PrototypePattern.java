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
        var s3=s1.clone2();
        System.out.println(s3.name + s3.roll + " " + s3.enrollmentNo + " " + s3.feeCalc());
	}
}

class Student implements Cloneable
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
    public Student clone2() {
        try {
            Student copy = (Student) super.clone();
            seq += 1;
            copy.enrollmentNo = seq; // Assign new enrollment number
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    
@Override
    public Student clone() {
        return new Student(name, roll);
    }
}
