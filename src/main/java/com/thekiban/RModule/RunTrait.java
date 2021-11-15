package com.thekiban.RModule;

import java.io.*;
import java.util.*;
import java.text.*;

public class RunTrait
{
	public  void MakeTrait(String comname, String jobid,  String pheno_list)
	{
		String r_path = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/";
		String phenotype_file =  "/data/apache-tomcat-9.0.8/webapps/ROOT/"+comname+"/resultfiles/accession_phenotype_list.txt";
		String traitplotfile =  "/data/apache-tomcat-9.0.8/webapps/ROOT/"+comname+"/resultfiles/"+jobid+"/"+jobid+"_traitplot_whitespace.png";

		//myY[!is.na(myY[,c(5,6)][,1]),6]
		String option_pheno = "myY[!is.na\\(myY[,c\\(1,"+pheno_list+"\\)][,1]\\),"+pheno_list+"]";
		String cmd = "Rscript " + r_path+"pc_traitplot.R " + " " + phenotype_file + " " + option_pheno + " " + traitplotfile;
		execute(cmd);
		System.out.println(" RunTrait cmd : " + cmd);

		String convert_whitespace = "convert " +"/data/apache-tomcat-9.0.8/webapps/ROOT/"+comname+"/resultfiles/"+jobid+"/"+jobid+"_traitplot_whitespace.png" + " -trim -bordercolor White +repage " + "/data/apache-tomcat-9.0.8/webapps/ROOT/"+comname+"/resultfiles/"+jobid+"/"+jobid+"_traitplot.png";
		execute(convert_whitespace);
    }


	 public static void execute(String cmd) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); // suc string buffer
        StringBuffer errorOutput = new StringBuffer(); //  err string buffer
        BufferedReader successBufferReader = null; // suc buffer
        BufferedReader errorBufferReader = null; //  err buffer
        String msg = null; // msg
 
        List<String> cmdList = new ArrayList<String>();
 
		cmdList.add("/bin/sh");
		cmdList.add("-c");
        
        // cmd setting
        cmdList.add(cmd);
		   String[] array = cmdList.toArray(new String[cmdList.size()]);
 
		   try { 
            // cmd exec
            process = runtime.exec(array);

			successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
 
            while ((msg = successBufferReader.readLine()) != null) {
                //successOutput.append(msg + System.getProperty("line.separator"))
				System.out.println(msg);
            }
 
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
           
			while ((msg = errorBufferReader.readLine()) != null) {
                //errorOutput.append(msg + System.getProperty("line.separator"));
				System.out.println(msg);
            }

			process.waitFor();
 
            if (process.exitValue() == 0) {
                System.out.println("Success "+"\n");
                //System.out.println(successOutput.toString());
            } else {
                System.out.println("Fail "+"\n");
                //System.out.println(errorOutput.toString());
            }
  
        } catch (IOException e) {
			System.out.println(e);
            e.printStackTrace();
        } catch (InterruptedException e) {
			System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
				System.out.println(e1);
                e1.printStackTrace();
            }
        }
    }
}