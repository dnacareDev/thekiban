package com.thekiban.RModule;

class TestCorr
{
	public static void main(String[] args) 
	{
		
		String pheno_list = args[0];
		String savePath = args[1];
		String pfilename = args[2];

		RunCorrlation runcorrlation = new RunCorrlation();
		runcorrlation.MakeCorrplot(pheno_list, savePath, pfilename);

		System.out.println("success");
	}
}
