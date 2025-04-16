import java.sql.*;
import  java.util.*;
public class Main {

    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username="root";    
    private static final String password="mysql@123";

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


//        try{
//            Connection connection= DriverManager.getConnection(url,username,password);
//            Statement statement=connection.createStatement();
//            //retrive
//            String query="select * from students";             //query
//            ResultSet resultSet=statement.executeQuery(query); //table


////            ResultSet is interface
//            //retrive excutequery

//            //insert update delete excuteupdate

//            while(resultSet.next()) { //row wise
//                //java local variables
//
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID: " + id);
//                System.out.println("NAME: " + name);
//                System.out.println("AGE: " + age);
//                System.out.println("MARKS: " + marks);
//
//            }

//            //insert
//            String query=String.format("INSERT INTO students(name,age,marks) VALUES('%s',%o,%f)","Karan",28,88.9);
//            int rowAffected=statement.executeUpdate(query);
//            if(rowAffected>0){
//                System.out.println("Data Inserted Successfully ");
//            }
//            else{
//                System.out.println("Data Not Inserted ");
//            }
//                    } catch (SQLException e) {
//            System.out.println(e.getMessage());

//            //update
//            String query=String.format("UPDATE students SET marks = %f WHERE id=%d",98.6,2);
//            int rowAffected=statement.executeUpdate(query);
//            if(rowAffected>0){
//                System.out.println("Data Updated Successfully ");
//            }
//            else{
//                System.out.println("Data Not Updated ");
//            }

//            //delete
//            String query=String.format("DELETE from students WHERE id=1");
//            int rowAffected=statement.executeUpdate(query);
//            if(rowAffected>0){
//                System.out.println("Data DELETED Successfully ");
//            }
//            else{
//                System.out.println("Data Not DELETED ");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }


        //Batch proceesing -At a time multiple processing or multiple queires ko run kar sakte hai
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            String query="INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner sc=new Scanner(System.in);
            while(true){
                System.out.println("Enter Name : ");
                String name=sc.nextLine();

                System.out.println("Enter Age ");
                int age=sc.nextInt();

                System.out.println("Enter Marks ");
                double marks=sc.nextDouble();

                System.out.println("Enter more date(Y/N): ");
                String choice=sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2, String.valueOf(age));
                preparedStatement.setString(3, String.valueOf(marks));

                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("N")) break;

            }

            int[] arr=preparedStatement.executeBatch();
            for(int i=0;i<arr.length;i++) {
                if (arr[i] == 0) {
                    System.out.println("Query: " + i + " " + " not excuted Successfully! ");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
