package com.merge;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileMerge {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	   if((args == null) || (args.length<2) ) {
		   System.out.println("Add valid arguments: inputFolder outputfile");
		   System.exit(0);
	   }
	   File folder = new File(args[0]);
	   //System.out.println("Absolute path:"+folder.getAbsolutePath());
	   String outputFile =args[1];
	   
	   FilenameFilter txtFilter = new FilenameFilter()
       {
           @Override
           public boolean accept(File dir, String name)
           {
               if(name.endsWith(".txt"))
               {
                   return true;
               }
               else
               {
                   return false;
               }
           }
       };
       File[] files = folder.listFiles(txtFilter);
       File outputTemp = new File("outputFile");
       outputTemp.createNewFile();
       
       for(int i=0; i<files.length;i++) {
    	   if(Files.lines(outputTemp.toPath()).count()==0) {
    		   if(!checkFileSorted(files[0])) {
    			   System.out.println(files[0].getName()+ " is not sorted");
    			   System.exit(0);
    		   }
    		   if(!checkFileSorted(files[1])) {
    			   System.out.println(files[1].getName()+ " is not sorted");
    			   System.exit(0);
    		   }   
    		   outputTemp = mergeFiles(files[0],files[1],outputTemp);
    		   i++;
    	   }else {
    		   if(!checkFileSorted(files[i])) {
    			   System.out.println(files[i].getName()+ " is not sorted");
    			   System.exit(0);
    		   } 
    		   outputTemp = mergeFiles(outputTemp,files[i],outputTemp);
    	   }
       }
       //File output = new File("outputFile");

	}
	//This function will check whether file is sorted
	static boolean checkFileSorted(File file) throws IOException {
		List<String> list = Files.readAllLines(file.toPath());
		boolean  sorted = list.stream().sorted().collect(Collectors.toList()).equals(list);
		return sorted;	
	}
    
	//This function will merge 2 files
	static File mergeFiles(File left, File right, File outputFile) throws Exception {
		// Initial indexes of first and second files 
        int i = 0, j = 0; 
        
        List<String> list1 = Files.readAllLines(left.toPath());
        List<String> list2 = Files.readAllLines(right.toPath());
        List<String> listOut = new ArrayList<String>();
        while (i < list1.size() && j < list2.size()) {
        	if(list1.get(i).equals("")){
        		//i++;
        	} else if(list2.get(j).equals("")){
        		//j++;
        	} else if(list1.get(i).equals("") && list1.get(j).equals("")){
        		//i++;
        		//j++;
        	} else
        	
        	{
        		if(list1.get(i).compareTo(list2.get(j))>0){
        			if(!listOut.contains(list2.get(j))) {
        				listOut.add(list2.get(j));
        			}
			        j++;
			     }else {
			    	 if(list1.get(i).compareTo(list2.get(j))==0){
			    	 if(!listOut.contains(list2.get(i))) {
				        listOut.add(list2.get(i));
			    	 }
				        j++;
				        i++;
				     }
				     else
				     {
				    	 if(!listOut.contains(list1.get(i))) {
				    		 listOut.add(list1.get(i));
				    	 }
				        i++;
				     }
			     }
		        	
	        }
        }
        while (i < list1.size()) {
        	if(!listOut.contains(list1.get(i))) {
        		listOut.add(list1.get(i));
        	}
        	i++;
        }
        while (j < list2.size()) {
        	if(!listOut.contains(list2.get(j))) {
        		listOut.add(list2.get(j));
        	}
        	j++;
        }
        if(!listOut.isEmpty())
        	Files.write(outputFile.toPath(),listOut,Charset.defaultCharset());
        return outputFile;
	}
}
