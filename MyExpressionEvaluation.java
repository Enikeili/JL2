package com.enikeili.jlab2;

import java.util.StringTokenizer;
import java.util.*;
//import com.enikeili.jlab2.MyTokenizer;
import java.util.Stack;
import com.enikeili.jlab2.MySymbolLine;

/**
 * My Expression Evaluator
 * @author enikeili
 */
public class MyExpressionEvaluation
{

	/**
	 * @param args
	 */
	public String expr; //expression
	
	/**
	 * Default constructor, creates empty expression 
	 */
	MyExpressionEvaluation()
	{
		expr = "";
	}
	
	/**
	 * Constructor with given sting, creates expression = given str
	 * @param str string
	 */
	MyExpressionEvaluation(String str)
	{
		expr = str;
	}
	
	/**
	 * Evaluates expression postfix entry
	 * @return double number - evaluation result
	 */
	Double evaluatingThePostfixEntry()
	{
		String inversePolishString = converterFromInfixToInversePolish();
		StringTokenizer tokens = new StringTokenizer(inversePolishString, " ", true);
		double result = 0;
		Stack<Double> stack = new Stack<>();
		while(tokens.hasMoreTokens())
		{
			MySymbolLine current_character = new MySymbolLine(tokens.nextToken());
			if(!current_character.getStr().equals(" "))
			{
				if(current_character.isNumber())
				{
					try
					{					
						stack.push(Double.parseDouble(current_character.getStr()));
					}
					catch (NumberFormatException e)
					{  
						System.err.println("The expression was inkorrect!");  
					}
				}
				else
				{
					Double operand2 = stack.pop();
					Double operand1 = stack.pop();
					stack.push(current_character.calculingOperation(operand1, operand2));				
				}
			}
		}
		result=stack.peek();
		return result;
	}
	
	
	/**
	 * Converts expression from infix to postfix 
	 * @return string - expression postfix entry
	 * @throws IllegalArgumentException called, if in result appears ( or )
	 */
	String converterFromInfixToInversePolish() throws IllegalArgumentException
	{
		StringTokenizer tokens = new StringTokenizer(expr, "+-*/()^", true);
		String result = "";
		Stack<String> stack = new Stack<>();
		while(tokens.hasMoreTokens())
		{
			MySymbolLine current_character = new MySymbolLine(tokens.nextToken());
			if(current_character.isNumber())
			{
				result = result+current_character.getStr()+" ";
			}
			boolean flagPush=false;
			while(current_character.isOperator() && !flagPush)
			{
				if(stack.empty()|| new MySymbolLine(stack.peek()).priority() < current_character.priority())
				{
					stack.push(current_character.getStr());
					flagPush=true;
				}
				else
				{
					while(new MySymbolLine(stack.peek()).priority() >= current_character.priority())
					{
						result = result+stack.pop()+" ";
					}
				}
			}
			if(current_character.getStr().equals("("))
				stack.push(current_character.getStr());
			if(current_character.getStr().equals(")"))
			{				
				while(!stack.empty() && !stack.peek().equals("("))
				{
					result=result+stack.pop()+" ";	
				}
				try
				{
					stack.pop();
				}
				catch (EmptyStackException e)
				{
					System.err.print("The expression was inkorrect!");
				}
			}
		}
		while(!stack.empty())
		{
			if(!(new MySymbolLine(stack.peek())).isOperator()) 
				throw new IllegalArgumentException("The expression was inkorrect!");
			else result=result+stack.pop()+" ";
		}
		return result;
	}

}
