package TableOfWorkers;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseWork {
    private static Connection connection;
    private static Statement stmt;

    static void connection () throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/TableOfWorkers/workersDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addToTableOfBranches(String branchName){
        try {
            String sql = String.format("INSERT INTO branches (branch) VALUES ('%s');",branchName);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static boolean isBranch(String branchName){
        String sql = String.format("SELECT id FROM branches where branch = '%s'",branchName);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static void showAllBranches(){
        String sql = "SELECT branch, amountworkers FROM branches;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1)+": "+rs.getString(2)+ " сотрудн.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addOneUnitWorkerToBranch(String branchName){
        String sql = String.format("UPDATE branches SET amountworkers = %s WHERE branch = '%s';",(countWorkersOfBranch(branchName)+1),branchName);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static int countWorkersOfBranch(String branchName){
        String sql = String.format("SELECT amountworkers FROM branches where branch = '%s';",branchName);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void deleteBranch(String branchName){
        if(isBranch(branchName)){
            dropTable(branchName);
            deleteBranchFromBranchesTable(branchName);

        }
    }

    private static void deleteBranchFromBranchesTable(String branchName){
        String sql = String.format("DELETE From branches WHERE branch = '%s';",branchName);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTable(String branchName){
        String sql = String.format("DROP TABLE IF EXISTS %s",branchName);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static void createBranch(String branchName){
        if(!isBranch(branchName)){
            createBranchTable(branchName);
            addToTableOfBranches(branchName);
        }else{
            System.out.println("Branch with name "+branchName +" already exists");
        }
    }

    private static void createBranchTable(String branchName){
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (passport INTEGER PRIMARY KEY UNIQUE, surname TEXT, name TEXT, post TEXT, salary INTEGER);", branchName);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static void addWorker (String branchName,int passport, String surname, String name, String post, int salary){
        if(!isBranch(branchName) ){
            System.out.println("There is't branch with name " + branchName);
        }else if(isWorkerWithPassport(passport)) {
            System.out.println("Worker with passport: "+passport+ " has already");
        }else{
            addWorkerToBranch(branchName,passport,surname,name,post,salary);
            addOneUnitWorkerToBranch(branchName);
        }
    }

    private static void addWorkerToBranch (String branchName,int passport, String surname, String name, String post, int salary){
        String sql = String.format("INSERT INTO %s (passport, surname, name, post, salary)" +
                "VALUES (%s, '%s','%s','%s',%s);",branchName,passport,surname,name,post,salary);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isWorkerWithPassport(int passport){
        try {
            String[] branchesArr = getBranches();
            for (String branch : branchesArr){
                if(isWorkerInBranch(branch,passport)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String[] getBranches() throws SQLException {
        String sql = "SELECT branch FROM branches;";
        ResultSet rs =  stmt.executeQuery(sql);
        ArrayList<String> arrayList = new ArrayList<>();
        while (rs.next()){
            arrayList.add(rs.getString("branch"));
        }
        return    arrayList.toArray(new String[0]);
    }

    static void showWorkers(String branch){
        if(isBranch(branch)) {
            String sql = String.format("SELECT * FROM %s", branch);
            try {
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(branch + " workers:");
                System.out.println("PASSPORT\t\tSURNAME\t\tNAME\t\tPOST\t\tSALARY\n");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"
                            + rs.getString(4) + "\t\t"+rs.getInt(5)+"\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(branch+" not found");
        }
    }


    static void updateWorkersData(String branchName,int passport, String surname, String name, String post, int salary){
        if(isBranch(branchName) && isWorkerInBranch(branchName,passport)) {
            String sql = String.format("UPDATE %s SET surname = '%s',name = '%s', post = '%s', salary = %s  " +
                    "WHERE passport = '%s';",branchName, surname,name,post,salary,passport);
            try {
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void updateWorkersPassport(int passport, int newPassport){
        if(isWorkerWithPassport(newPassport)){
            System.out.println("Worker with passport "+newPassport+" has already");
        } else if(isWorkerWithPassport(passport) ){
            try {
                String[] branchesArr = getBranches();
              for(String branch : branchesArr){
                    if(isWorkerInBranch(branch,passport)){
                        String sql = String.format("UPDATE %s SET passport=%s WHERE passport = %s;",branch, newPassport, passport);
                        stmt.execute(sql);
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Worker with passport: "+passport+" not found" );
        }
    }


    private static boolean isWorkerInBranch(String branchName, int  passport){
        String sql = String.format("SELECT post FROM %s where passport = %s;",branchName,passport);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    static void deleteWorker(int passport){
        if(isWorkerWithPassport(passport)) {
            try {
                String[] branchArr = getBranches();
                for (String branch : branchArr) {
                    if (isWorkerInBranch(branch, passport)) {
                        String sql = String.format("DELETE FROM %s where passport = %s", branch, passport);
                        stmt.execute(sql);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Worker with passport "+passport+ " not found!");
        }
    }


    static boolean isNotConnection() throws SQLException {
        return connection == null || connection.isClosed();
    }

    static void disconnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
