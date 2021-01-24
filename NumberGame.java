import java.util.*;

public class NumberGame
{
	
	static int startNum;
	static int answer;
	static int currentVal;
	static int maxNumber = 150;
	static int maxChoice = 100;
	static String[] operators = {"*", "/", "+", "-"};
	static Scanner input = new Scanner(System.in);
	static Boolean isNumber = false;
	static Boolean done = false;
	static ArrayList<Integer> oldNums = new ArrayList<Integer>();
	
	public static void main(String[] args)
	{		
		Start();
	}
	
	static void Start()
	{
		Random rand = new Random();
		answer = rand.nextInt(maxNumber);
		Print("\n\nWhat is your favorite number between 1 and " + maxChoice + "??");
		
		int val = 0;
		do
		{
			try
			{
				startNum = input.nextInt();
				if(startNum > maxChoice)
				{
					startNum = maxChoice;
				}
				else if(startNum < 1)
				{
					startNum = 1;
				}
				isNumber = true;
			}
			catch(InputMismatchException e)
			{
				String charVal = input.nextLine();
				Println("Enter an actual number that is between 1 and 10");
			}
		}
		while(!isNumber);
		Println("\n" + "Did you know that " + startNum + " is equal to " + answer);
		Print("Wanna see how in a couple simple steps?? Y/N \t");
		
		done = false;
		oldNums = new ArrayList<Integer>();
		currentVal = startNum;
		isNumber = false;
		
		if(input.next().toLowerCase().charAt(0) == 'y' )
		{
			ShowMe();
		}
	}
	
	static void ShowMe()
	{
		Random rand = new Random();
		
		for(int i = 0; i < rand.nextInt(5) + 5; i ++)
		{
			currentVal = Arithmetic(operators[rand.nextInt(operators.length)]);

		}
		
		currentVal = Finish();
		
		PlayAgain();
	}
	
	static int Arithmetic(String choice)
	{
		Random rand = new Random();
		int val = currentVal;
		int num = rand.nextInt(maxNumber);
		switch(choice)
		{
			case "*":
				if(num > 0 && num * val <= maxNumber && num * val != answer)
				{
					val *= num;
					Println(currentVal + choice + num + "=" + val);
				}
				else
				{
					val = Arithmetic(operators[rand.nextInt(operators.length)]);
				}
				break;
				
			case "/":
				if(num == 0 || val % num != 0 || val / num == answer)
				{		
					val = Arithmetic(operators[rand.nextInt(operators.length)]);
					
				}
				else
				{
					val /= num;
					Println(currentVal + choice + num + "=" + val);
				}
				break;
				
			case "+":
				if(val + num != answer && val + num <= maxNumber)
				{
					val += num;
					Println(currentVal + choice + num + "=" + val);
				}
				else
				{
					val = Arithmetic(operators[rand.nextInt(operators.length)]);
				}

				break;
				
			case "-":
				if(num <= val && val - num != answer)
				{
					val -= num;
					Println(currentVal + choice + num + "=" + val);
				}
				else
				{
					val = Arithmetic(operators[rand.nextInt(operators.length)]);
				}
				
				break;

		}
		
		
		return val;
	}
	
	static int Finish()
	{
		
		
		String choice = "";
		Random rand = new Random();
		int num = rand.nextInt(Math.max(currentVal,answer)) + 1;
		int val = currentVal;
		if(!done)
		{	
			if(!oldNums.contains(num))
			{
				if(num * val == answer)
				{
					choice = "*";
					val *= num;
					Println(currentVal + "" + choice + "" + num + "=" + val);
				}
				else if(num != 0 && val / num == answer && val % num == 0 )
				{
					choice = "/";
					val /= num;
					Println(currentVal + "" + choice + "" + num + "=" + val);
				}
				else if(num + val == answer)
				{
					choice = "+";
					val += num;
					Println(currentVal + "" + choice + "" + num + "=" + val);
				}
				else if(val - num == answer)
				{
					choice = "-";
					val -= num;
					Println(currentVal + "" + choice + "" + num + "=" + val);
				}
				else
				{
					val = Finish();
					oldNums.add(num);
				}
			}
			else
			{
				oldNums.add(num);
				
			}
			done = true;
			

		}
		

		return val;

	}
	
	static void PlayAgain()
	{
				
		Println("Now do you see it?");
		Print("Go again?? Y/ N \t");
		
		if(input.next().toLowerCase().charAt(0) == 'y' )
		{
			Start();
		}
	}
	
	static void Print(String arg)
	{
		System.out.print(arg);
	}
	
	static void Println(String arg)
	{
		System.out.println(arg);
	}

}