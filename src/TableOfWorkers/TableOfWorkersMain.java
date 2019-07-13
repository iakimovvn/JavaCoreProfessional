package TableOfWorkers;

import java.sql.SQLException;

public class TableOfWorkersMain {
    public static void main(String[] args) throws SQLException {
        if(DatabaseWork.isNotConnection()){
            DatabaseWork.connection();
        }

        DatabaseWork.createBranch("filialone");
        DatabaseWork.createBranch("filialtwo");
        DatabaseWork.createBranch("filialthree");

        DatabaseWork.addWorker("filialone",1231344,"Yakimov", "Vladimir", "Director",100000000);
        DatabaseWork.addWorker("filialone",4331134,"Yurtaeva","Irina","Comertical Director",12243134);
        DatabaseWork.addWorker("filialone",4334521,"Ivanov","Segei","Driver",20000);
        DatabaseWork.addWorker("filialone",4432531,"Semonova","Olga","Cook",25000);

        DatabaseWork.addWorker("filialtwo",13231344,"Lavrov", "Konstantin", "Director",100000000);
        DatabaseWork.addWorker("filialtwo",23333134,"Rohina","Irina","Comertical Director",12243134);
        DatabaseWork.addWorker("filialtwo",23334521,"Susanin","Segei","Driver",20000);
        DatabaseWork.addWorker("filialtwo",4232531,"Smirnova","Olga","Cook",25000);

        DatabaseWork.showAllBranches();

        DatabaseWork.showWorkers("filialone");
        DatabaseWork.showWorkers("filialtwo");


        DatabaseWork.updateWorkersData("filialone",4334521,"Petrov","Alecsandr","Driver",20000);
        DatabaseWork.showWorkers("filialone");
        DatabaseWork.updateWorkersPassport(4232531,1234567890);
        DatabaseWork.deleteWorker(23334521);
        DatabaseWork.deleteWorker(2354521);


        DatabaseWork.showWorkers("filialtwo");




        DatabaseWork.deleteBranch("filialone");
        DatabaseWork.deleteBranch("filialtwo");


        if(!DatabaseWork.isNotConnection()){
            DatabaseWork.disconnection();
        }



    }
}
