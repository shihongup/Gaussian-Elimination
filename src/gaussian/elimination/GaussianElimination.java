package gaussian.elimination;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Shifeng Yuan
 * GWID G32115270
 */
public class GaussianElimination {

    //Input the Matrix
    public static void main(String[] args) {
        int a ,b;
        a = Integer.parseInt(JOptionPane.showInputDialog("Amount of Rows:"));
        b = Integer.parseInt(JOptionPane.showInputDialog("Amount of Columns:"));
        double num[][] = new double[a][b];
        
        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                num[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Num"+i+","+j));

            }
        }
        double firstnum[][] = new double[a][b];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                firstnum[i][j] = num[i][j];
            }
        }

        //Print the Matrix
        System.out.println("This Is The Matrix You Typed In:");
            for(int i =0; i<a;i++){
                for(int j = 0;j<b-1;j++){
                    System.out.print(num[i][j]+" ");
                    
                }
                System.out.print("|"+num[i][b-1]+" ");
                System.out.print("\n");
            }

        if(zeroRowExRight(num,a,b)){
            //In case of a row of all zeros except the right hand side
            System.out.println("There Is No Answer To This Equitition!");     
        }else if(allZeroRow(num,a,b)){
            paramatricForm(firstnum,a,b);
        }
        else{
        
        for(int t = 0; t<a; t++){
            //In case of a row of all zeros except the right hand side
            if(zeroRowExRight(num,a,b)){
            System.out.println("There Is No Answer To This Equitition!");
            break;
            }else if(allZeroRow(num,a,b)){
                //In case of a row of all zeros
                paramatricForm(firstnum,a,b);
                break;
            }
            else{
            //start pivot
            num = pivotProcess(num,t,a,b);
            }
        }
        if((!zeroRowExRight(num,a,b))&&(!allZeroRow(num,a,b))){
        double finalnum[][] = new double[a][b];
        for(int i = 0;i<a;i++){
            for(int j = 0;j<b;j++){
                finalnum[i][j] = num[i][j]/num[i][i];               
            }
        }
        //change -0 to 0
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                if(finalnum[i][j] == -0){
                    finalnum[i][j] = 0;
                }
            }
        }
        //Print The Finalmatrix
        System.out.println("This Is The Final Matrix:");
            for(int i =0; i<a;i++){
                for(int j = 0;j<b-1;j++){
                    System.out.print(finalnum[i][j]+" ");
                    
                }
                System.out.print("|"+finalnum[i][b-1]+" ");
                System.out.print("\n");
            }
            System.out.print("The Final Answer Is: ");
            System.out.print("{(");
            for(int i =0; i<a;i++){
                System.out.print(finalnum[i][b-1]);
                if(i<a-1){
                    System.out.print(",");
                }
            }
            System.out.print(")}"+"\n");
        }
    
    }
    }
    
    //Identify the row of all zeroes except the right hand side
    public static boolean zeroRowExRight(double num[][],int a,int b){
        int countzero = 0;
        int nrow;
        for(nrow = 0; nrow<a;nrow++){
        
            for(int j = 0; j<b-1; j++){
                if(num[nrow][j] == 0){
                    countzero++;
                }
            }
            if(countzero == b-1){
                break;
            }
            else{
                countzero = 0;
            }
        }
        if((countzero == b-1)&&(num[nrow][b-1] != 0)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    //Identify the row of all zeroes
    public static boolean allZeroRow(double num[][],int a,int b){
        int countzero = 0;
        int nrow=0;
        for(nrow = 0; nrow<a;nrow++){
        
            for(int j = 0; j<b; j++){
                if(num[nrow][j] == 0){
                    countzero++;
                }
            }
            if(countzero == b){
                break;
            }
            else{
                countzero = 0;
            }
        }
        if(countzero == b){
            return true;
        }
        else{
            return false;
        }        
    }
    
    //Outcome a paramatric form for more than one solutions
    public static void paramatricForm(double firstnum[][],int a,int b){
        double xnum=0;
        double ynum=0;
        double znum=0;
        double constantnum=0;
        
        for(int i=0;i<a-1;i++){
            xnum = firstnum[i][0]+firstnum[i+1][0];
            ynum = firstnum[i][1]+firstnum[i+1][1];
            znum = firstnum[i][2]+firstnum[i+1][2];
            constantnum = firstnum[i][3]+firstnum[i+1][3];
        }
        System.out.println("There are many solutions to this equition,");
        System.out.println("This is the paramatric form:");
        System.out.println("("+xnum+")"+"x +"+"("+ynum+")"+"y+ "+"("+znum+")"+ "z "+"= "+constantnum);
        
         
    }
    
    //pivot
    public static double[][] pivotProcess(double num[][], int t, int a, int b){
                
                double pivot = num[t][t];
                int swaptime = 0;
                while(num[t][t] == 0){
                    num = swapRows(num,t,a,b);
                    //renew pivot
                    pivot = num[t][t];
                    if(swaptime < a-1){
                        swaptime++;
                    }
                    else{
                        System.out.println("There Is No Answer To This Equitition!");
                        break;
                    }
                }       
                
                for(int k = 0; k<a; k++){
                    for(int l = 0; l<b; l++){
                        if((k != t)&&(l != t)){
                            num[k][l] = num[k][l]*pivot - num[k][t]*num[t][l];   
                        }
                    }  
                }
               for(int m = 0; m<a; m++){
                    if(m != t){
                        num[m][t] = 0;
                    }
                }
        return num;
    }
    
    //Get the matrix ready to be pivot
    public static double[][] swapRows(double num[][],int m, int a, int b){
        double bridgenum[][] = new double[a][b];
        for(int i = m; i <=a-2; i++){

            for(int j = 0; j<b; j++){
                bridgenum[i+1][j] = num[i][j];
            }
        }
        for(int j =0;j<b; j++){
            bridgenum[m][j] = num[a-1][j];
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<b;j++){
                bridgenum[i][j] = num[i][j];
            }
        }

        double newnum[][] = new double[a][b];
        for(int i =0; i<a; i++){
            for(int j = 0; j<b; j++){
                newnum[i][j] = bridgenum[i][j];
            }
        }
        
        return newnum;
    }

}
