
import java.io.*;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RamyGomaa
 */
public class AbsoluteLoader {

    private String pName; // da l esm eli l user hyktbo
    private String pLength;
    private String actualName; // da l esm bta3 l berngamg eli fi l file.. h3ml check eza kan da eli l user f3ln 3yzo wla l2.
    private String actualLength;
    private Boolean nameFlag = false; // da 34an a4of eza kan l esm mktob s7 wla l2
    private String addr; //da l adress eli wa5do mn l file abl l t7wel
   
    private boolean finish = false; // 34an a4of wslt le l E wla l2
    PrintWriter myPen = null;
    

    public AbsoluteLoader() {
        try {
            myPen = new PrintWriter("LOADER.TXT");
        } catch (FileNotFoundException ex) {
            System.out.println("Error in The PrintWriter");
        }
        readAssembler(); // l method eli by7sl feha kol 7aga
         myPen.close();
    }

    private void readAssembler() {
        System.out.print("Enter The Program Name : "); //bs2l 34an a3ml check 3aleh.
        Scanner input = new Scanner(System.in);
        pName = input.nextLine(); // pName feh l esm eli l user hyda5alo
        System.out.println("Enter The Program Length");
        Scanner inputL = new Scanner(System.in);
        pLength = inputL.nextLine();
        File assembler = new File("ASSEMBLER.txt"); //assembler da howa l file 
        Scanner readAssembler = null;
        try {
            readAssembler = new Scanner(assembler); // da eli mt5zn feh l file
        } catch (FileNotFoundException ex) {
            System.out.println("Error in readAssembler Scanner");
        }

        String read = null; // da eli hst3mlo 34an a2ra l file mn l readAssembler

        while (readAssembler.hasNext()) {

            read = readAssembler.next(); // read de h4of feha el 7gat eli et2rt.
            if (read.charAt(0) == 'H') { // lw awel 7rf et2ra bysawi H yb2a eli b3do howa esm l bernamg
                actualName = read.substring(1, 7); // b5li actual name = mn 1 le 7 esm l bernamg
                actualLength = read.substring(13,19);
                if (actualName.compareTo(pName) == 0&&actualLength.compareTo(pLength)==0) { // lw esm l bernamg howa l esm eli l user 3yzo yb2a tmam
                    System.out.println("Program Found");
                    nameFlag = true; // da flag 34an a4of lw ml2a4 l esm 5als
                    break; // b3ml break 34an y5rog mn l while
                }

            }
        }
        if (nameFlag == false) { // lw ml2a4 l esm
            System.out.println("Program Name Not Found");
        } else { // lw l2a l esm hykml
            System.out.println("\n\nMemory \t \t Data\n");
            myPen.println("\n\nMemory \t \t Data\n");
            // myPen.close();
            while (readAssembler.hasNext()) { // tol ma lsa fi 7aga m2renha4 fl readAssembler
                read = readAssembler.next(); // hn2raha b l read we nt3aml m3aha

                readT(read, readAssembler);// hna hnt3aml m3aha

            }

        }

    }

    private void readT(String read, Scanner readAssembler) {
        if (read.charAt(0) == 'T' && finish == false) { // lw awel 7rf mn l read howa T yb2a l gy l adress .. we b3ml check la akon d5lt fi bernamg gded
            addr = read.substring(1, 7); // ba5d l adress

            int temp = Integer.parseInt(addr, 16); // ba7awelo mn String hex le integer ex: 001000 > 4000 
            addr = Integer.toHexString(temp); // we ba7awel mn l rqm l inter le hex tani : 4000 > 1000 , 34an am4i l asfar l zayada de

            int i = 9; // h5li l i b 9 34an y3adi l length bta3 l T we yd5ol fi l instructions.
            try {
                while (true) { // tol ma howa true l mfrod lma l line bta3 l T y5ls y3ml exception fa da eli hy5leni a3rf eno 5ls
                    System.out.println(addr + "\t \t " + read.substring(i, i + 2)); // hytb3 l address we l instructions rqmen rqmen
                    myPen.println(addr + "\t \t " + read.substring(i, i + 2));
                   
                    i = i + 2; // howa dlwa2t tb3 rqmen fa hzwd rqmen 34an ytb3 l gyen.
                    temp = Integer.parseInt(addr, 16); // h7awel l rqm mn string (hex) le int 34an a3rf azawedo.
                    temp++; // hzawedo b wa7d.
                    addr = Integer.toHexString(temp); //h7awelo le rqm hex String tani ex: 4010>100A

                }
            } catch (Exception ex) { // exception m3anaha N l Line 5ls.
                read = readAssembler.next();  // h2ral str l gded
                if (read.charAt(0) != 'E') { // lw awel 7rf m4 E
                    // System.out.println(read.charAt(0));
                    readT(read, readAssembler); // 3ed l funtion mn l awel. 34an y2ra l text l gdeda
                } else {
                    finish = true; // lw awel 7rf b e y3ni l bernamg 5ls .
                }

            }

        }
    }

    public static void main(String[] args) {
        AbsoluteLoader myLoader = new AbsoluteLoader();
        
    }

}
