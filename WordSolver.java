import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class WordSolver {
        
    public static HashMap possibleSolutions(String letters)     {  
        
        //Initialize dictionary
        HashMap<Integer,HashSet<String>> wordCombinations = new HashMap();        
        for(int i=3;i<=letters.length();i++){
            wordCombinations.put(i, new HashSet<>());        
        }        
        
        //get permutations for all strings of length 3 onwards
        for(int i=0;i<letters.length()-1;i++){
            String temp = letters.substring(i,i+2);
            for(int j=i+2;j<letters.length();j++){
                temp = temp+letters.charAt(j);
                permutations("",temp,wordCombinations);
            }        
        }
        
        //get permutations for last 2 characters + first char
            String temp = letters.charAt(0)+letters.substring(letters.length()-2);
            permutations("",temp,wordCombinations);
         
            return wordCombinations;
    }
    
    private static void permutations(String prefix, String suffix, HashMap possibleCombinations){
        //base case
        if(suffix.length()==0){ //no more characters to add to prefix
           HashSet<String> letterCombinations = (HashSet<String>) possibleCombinations.get(prefix.length());
           letterCombinations.add(prefix);
           possibleCombinations.put(prefix.length(),letterCombinations);
           
    }else{
            for(int i=0; i<suffix.length(); i++){                
                //update suffix and prefix values for next iterations
                String updatedPrefix = prefix+suffix.charAt(i);
                String updatedSuffix = suffix.substring(0,i)+suffix.substring(i+1,suffix.length());
                permutations(updatedPrefix,updatedSuffix,possibleCombinations);            
            }
            
        }
    }
    
    
    public static void main(String args[]){
        
        System.out.println("Welcome to the word race solver.");
        System.out.println("Type in the scrambled letter and press Enter:"); 
        
            Scanner input = new Scanner(System.in);
            String letters = input.nextLine();
            
            while (true){
        
                if(letters.length()<3 || letters.length()>7 ){
                    System.out.println("Please input 3-7 letters:");
                    letters = input.nextLine(); 
                }                         
        
                else if (!letters.matches("^[a-zA-Z]*$")){
                    System.out.println("Please input alphabet characters only:");
                    letters = input.nextLine(); 
                }
                                
                else{
                    break;
                }
        }
        
        HashMap<Integer,HashSet<String>>  result = possibleSolutions(letters);
        for(Integer key: result.keySet()){
            List<String> solutions = new ArrayList<>(result.get(key));
            Collections.sort(solutions);

             System.out.println(key+ " letter words:\n"+ solutions+ "\n");
            
        }
}
    
}
