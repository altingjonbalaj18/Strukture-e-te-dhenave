import javax.swing.*;

public class ArrayAverageMostLeastRepetitions{
   private static int[] numbers;
   public static void main(String[] args){
      numbers = new int[1];
      while(true){
         int temp = 0;
         String input = JOptionPane.showInputDialog("Enter a number between 1 and 20");
         if(input == null){
            break;
         }
         try{
            temp = new Integer(input).intValue();
         }catch(Exception e){
            System.out.println("Incorrect input");
            System.out.println();
            break;
         }
         if(temp < 1 || temp > 20){
            System.out.println("Number out of range");
            System.out.println();
            break;
         }
         else{
            numbers = increaseAndInsert(numbers, temp);
          }
      }
      System.out.println("Mesatarje e elementeve eshte " + calculateAverage(numbers));
      System.out.println();
      int[] repetitions = new int[20];//sepse kemi numrat nga 0 deri ne 20
      repetitions = arrayOfRepeats(repetitions);
      int max = max(repetitions);
      int min = min(repetitions);
      if(max != min){
         mostRepetitions(repetitions, max);
         leastRepetitions(repetitions, min);
      }
      else{
         mostRepetitions(repetitions, max);
      }  
   }
   public static int[] increaseAndInsert(int[] numbers, int d){
      int[] b = new int[numbers.length + 1];
      for(int i = 0; i<numbers.length; i++){
         b[i] = numbers[i];
      }
      b[numbers.length] = d;
      return b;
   }
   //Pas printimit te elementeve vetem elementi i pare kishte vleren 0, pra ne perjashtojm elementin e pare
   //dhe ja nisim te numerojme nga elementi i dyte Mesataren e llogarisimin me nje element me pak.
   public static double calculateAverage(int[] a){
      double total = 0;
      double average;
      
      for(int i = 1; i<a.length; i++){
         total += a[i];
      }
      return total / (a.length - 1);
   }
   public static int totalRepeatsOfNumberInArray(int k, int[] array){
      int count = 0;
      for(int i = 0; i < array.length; i++){
         if(array[i] == k){
            count++;
         }
      } 
      return count;
   }
   public static int[] arrayOfRepeats(int[] array){
      for(int i = 0; i<array.length; i++){
         array[i] = totalRepeatsOfNumberInArray(i + 1, numbers);
      }
      return array;
   }    
   public static int max(int[] array){
      int max = array[0];
      if(array.length > 1){
         for(int i = 1; i<array.length; i++){
            if(array[i] > max){
               max= array[i];
            }
         }
      }
      return max;
   }
   public static int min(int[] array){
      int min = max(array);
      if(array.length > 1){
         for(int i = 1; i<array.length; i++){
            if(array[i] < min && array[i] != 0){
               min = array[i];
            }
          }
       }
       return min;
   }
   public static void mostRepetitions(int[] array, int max){
      System.out.println("Most repetitions:");
      for(int i = 0; i<array.length; i++){
         if(array[i] == max && array[i] != 0){
            System.out.println("Number " + (i + 1) + " : " + max + " repeats.");
         }
      }
      System.out.println();
   }
   public static void leastRepetitions(int[] array, int min){
      System.out.println("Least repetitions:");
      for(int i = 0; i<array.length; i++){
         if(array[i] == min && array[i] != 0){
            System.out.println("Number " + (i + 1) + " : " + min + " repeats.");
         }
      }
      System.out.println();
   }
}