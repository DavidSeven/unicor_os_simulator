package controllers;

public class Global 
{
	public boolean isNumeric (String chain) 
	{
		return (chain.matches ("[+-]?\\d*(\\.\\d+)?"));
	}
}
