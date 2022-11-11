package com.enikeili.jlab2;

/**
 * An additional class expression token
 * @author enikeili
 */
public class MySymbolLine
{
	private String str; //string
	
	/**
	 * Default constructor, creates symbolline with empty string 
	 */
	public MySymbolLine()
	{
		str="";
	}
	
	/**
	 * Constructor with given sting, creates symbolline with string = given symbols
	 * @param symbols
	 */
	public MySymbolLine(String symbols)
	{
		str=symbols;
	}
	
	/**
	 * Getter for str, returns string 
	 * @return string
	 */
	public String getStr()
	{
		return str;
	}
	
	/**
	 * Checks whether the string is an operator +,-,*,/,^ returns true, if string is an operator, otherwise false  
	 * @return true or false
	 */
	public boolean isOperator()
	{
		return (str.equals("+")||str.equals("-")||str.equals("*")||str.equals("/")||str.equals("^"));
	}
	
	/**
	 * Checks whether the string is ( or ), returns true, if string is ( or ), otherwise false  
	 * @return true or false
	 */
	public boolean isLRB()
	{
		return (str.equals("(")||str.equals(")"));
	}
	
	/**
	 * Checks whether the string is a number, returns true, if string is a number, otherwise false  
	 * @return true or false
	 */
	public boolean isNumber()
	{
		boolean flag=true;
		for(int i=0; i<str.length() && flag;++i)
		{
			Character c = str.charAt(i);
			if(!Character.isDigit(c)) flag=false;
		}
		return flag;
	}
	
	/**
	 * Returns operation priority according to mathematical concepts: * or / has a higher priority than + or -, ^ has a higher priority than * or /
	 * @return number from [-1,2]
	 */
	public int priority()
	{
		int priority;
		switch(str)
		{
			case "-":
			case "+":
				priority = 0; break;
			case "/":
			case "*":
				priority = 1; break;
			case "^":
				priority = 2; break;
			default: 
				priority = -1; break;
		}
		return priority;
	}
	
	/**
	 * Evaluates operation +,-,*,/,^, returns result
	 * @param operand1 first operand
	 * @param operand2 second operand
	 * @return calculation result
	 * @throws ArithmeticException called if there is a division by zero
	 * @throws IllegalArgumentException called if string isn't an operator
	 */
	public Double calculingOperation(Double operand1, Double operand2) throws ArithmeticException,IllegalArgumentException
	{
		if(isOperator())
			switch(str)
			{
				case "-":
					return operand1-operand2;
					//result = operand1-operand2;
				case "+":
					return operand1+operand2;
					//result = operand1+operand2;
				case "/":
					if(operand2!=0)
						return operand1/operand2;
					else
						throw new ArithmeticException("Division by zero!");
				case "*":
					return operand1*operand2;
				case "^":
					return Math.pow(operand1, operand2);
			}
		else
			throw new IllegalArgumentException("The expression was inkorrect!Passed not an Operator!");
		return null;
	}
}
