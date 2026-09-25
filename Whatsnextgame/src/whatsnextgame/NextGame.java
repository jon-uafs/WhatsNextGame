package whatsnextgame;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class NextGame {
	
	Scanner inFile = new Scanner(System.in);
	
    public int[] loadArray()
    {
        Random r = new Random();
        int[] numbers = new int[5];
        int index;
        
        for (index = 0; index < numbers.length; index++)
        {
            numbers[index] = r.nextInt(5) + 1;
        }

        return numbers;

    }

    public void showArray(int[] array)
    {
        int counter = 0;
        
        boolean allUnique = uniqueCheck(array);

        while (!allUnique) { 
            
            allUnique = uniqueCheck(array);
            if(allUnique)
                {
                    break;
                }
            else
                {
                    array = loadArray();
                }

            counter++;

            if(counter > 500)
                {
                    System.out.println("Failed to generate a unique array after 500 attempts.");
                    break;
                }
        }
        
        /*
        int index;
        if (allUnique) {
            for (index = 0; index < array.length; index++)
            {
                System.out.println(array[index]);
            }
        }
        */
    }

    public boolean uniqueCheck(int[] array)
    {
        boolean zeroUnique = false;
        boolean oneUnique = false;
        boolean twoUnique = false;
        boolean threeUnique = false;
        boolean fourUnique = false;
        boolean allUnique = false;
        
        if ((array[0] != array[1]) && (array[0] != array[2]) && (array[0] != array[3]) && (array[0] != array[4]))
        {
            zeroUnique = true;
        }
        if ((array[1] != array[0]) && (array[1] != array[2]) && (array[1] != array[3]) && (array[1] != array[4]))
        {
             oneUnique = true;
        }
        if ((array[2] != array[0]) && (array[2] != array[1]) && (array[2] != array[3]) && (array[2] != array[4]))
        {
            twoUnique = true;
        }
        if ((array[3] != array[0]) && (array[3] != array[2]) && (array[1] != array[3]) && (array[3] != array[4]))
        {
            threeUnique = true;
        }
        if ((array[4] != array[0]) && (array[4] != array[2]) && (array[4] != array[3]) && (array[1] != array[4]))
        {
            fourUnique = true;
        }
        if (zeroUnique && oneUnique && twoUnique && threeUnique && fourUnique)
        {
            allUnique = true;
        }

        return allUnique;
    }
    
    public boolean arrayCompare(int[] correctArray, int[] guessArray) 
    {
    	return Arrays.equals(correctArray, guessArray);
    }
    
    public boolean inputType(String userInput) 
    {
    	boolean isValid = false;
    	int index;
    	char stringChar;
    	
    	for (index = 0; index < userInput.length(); index++) 
    	{
    		stringChar = userInput.charAt(index);
    		if(!Character.isDigit(stringChar))
    		{
    			isValid = false;
    			break;
    		}
    		else {
    			isValid = true;
    		}
    	}
    	    	
    	return isValid;
    }
    
    public int ansCorrect(int[] correctArray, int[] guessArray) 
    {

    	int correctCounter = 0;
    	
    	
    	if(correctArray[0] == guessArray[0]) 
    	{
    		correctCounter++;
    	}
    	if(correctArray[1] == guessArray[1]) 
    	{
    		correctCounter++;
    	}
    	if(correctArray[2] == guessArray[2]) 
    	{
    		correctCounter++;
    	}
    	if(correctArray[3] == guessArray[3]) 
    	{
    		correctCounter++;
    	}
    	if(correctArray[4] == guessArray[4]) 
    	{
    		correctCounter++;
    	}
    	
    	return correctCounter;
    }
    
    public int[] inputToArray(String userInput) 
    {
		 String[] stringArray = userInput.split("");
	     
	     int[] guessArray = new int[stringArray.length];
	     
	     for (int i = 0; i < stringArray.length; i++) 
	     {
	     	guessArray[i] = Integer.parseInt(stringArray[i]);
	     }
	     
	     return guessArray;
    }
    
    public void gameBegin() 
    {    	
    	//int[] correctArray = loadArray(); // Load the solution
    	int[] correctArray = {1,2,3,4,5}; 
        Scanner scanner = new Scanner(System.in);
        int turnCounter = 0;
        
        System.out.print("Enter your 5 numbers (no spaces): ");
        String userInput = scanner.nextLine();
        
        if(inputType(userInput) == false) 
        {
        	System.out.println("You need to enter digits.");            	
        }
        else if (userInput.equalsIgnoreCase(""))
		{
        	System.out.println("Well, you at least have to enter something.");
		}
        else if (userInput.isEmpty() || userInput.isBlank())
		{
        	System.out.println("Well, you at least have to enter something.");
		}
        else if (Integer.parseInt(userInput) < 11111 || Integer.parseInt(userInput) > 55555)
		{
        	System.out.println("You need to enter numbers between 1 and 5.");
		}

    }
    
    
    public void run() {

        try {
        	displayIntro();
        	
        	int[] correctArray = {1,2,3,4,5}; 
            int[] guessArray = new int[5];
            Scanner scanner = new Scanner(System.in);
            boolean gameOver = false;
            
            System.out.print("Enter your 5 numbers (no spaces): ");
            String userInput = scanner.nextLine();
            
            guessArray = inputToArray(userInput);
            
            
            
            
            while(gameOver == false) 
            {
            	gameBegin();
            	gameOver = arrayCompare(correctArray, guessArray);
            	
            }
            
            if(arrayCompare(correctArray, guessArray) == true)
			{
        		System.out.println("Congrats! You win!");
		
			}
            
            System.out.print(ansCorrect(correctArray, guessArray));


        }

        //Just in case prof puts in some silly characters
        catch (InputMismatchException e) {
        	e.toString();
            System.out.println("Invalid input. Please enter numbers only.");
            run();
        }
        catch(ArrayIndexOutOfBoundsException e) 
        {
        	e.toString();
        	System.out.println("Only enter 5 numbers.");
        	run();
        }
        catch(NumberFormatException e) 
        {
        	System.out.println("Stop typing weird stuff.");
        	run();
        }
        catch(Exception e) 
        {
        	System.out.println(e.toString());
        	System.out.print("I'm not sure what you did, but it was wrong.");
        	run();
        }
    }


    public void displayIntro() {
        System.out.println("Game: What's Next?");
        System.out.println("Objective: Identify the Sequence of 5 numbers between 1 and 5 using the fewest turns. If you wish to quit guessing and give up,");
        System.out.println("Enter a ZERO for one of your guesses and the game will display the solution and quit.");
        System.out.println(" GOOD LUCK!!!");
    }
}


    
