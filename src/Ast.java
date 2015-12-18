

import java.io.PrintWriter;

abstract class AST {
}

class Program extends AST {
    public Program(ExprList L) {
        myExprList = L;
    }
    private ExprList myExprList;
    public void interpret(PrintWriter pw)
    {
    	myExprList.interpret(pw);
    	pw.println(".");
    }
}

abstract class ExprList extends AST
{
	abstract void interpret(PrintWriter pw);
}

class OneExprList extends ExprList
{
	public OneExprList(Expr ex)
	{
		myEx = ex;
	}
	private Expr myEx;
	public void interpret(PrintWriter pw)
	{
		pw.print(myEx.interpret());
	}
}


class MoreExprList extends ExprList
{
	public MoreExprList(Expr ex, ExprList el)
	{
		myEx = ex;
		myEl = el;
	}
	
	private Expr myEx;
	private ExprList myEl;
	
	public void interpret(PrintWriter pw)
	{
		myEl.interpret(pw);
		pw.print(";");
		pw.print(myEx.interpret());
	}
}

abstract class Expr extends AST
{
	abstract int interpret();
}

class PlusExpr extends Expr
{
	public PlusExpr(Expr ex, Term t)
	{
		myEx = ex;
		myTerm = t;
	}
	
	private Expr myEx;
	private Term myTerm;
	
	public int interpret()
	{
		int a = myEx.interpret();
		int b = myTerm.interpret();
		return (a + b);
	}
	
}

class MinusExpr extends Expr
{
	public MinusExpr(Expr ex, Term t)
	{
		myEx = ex;
		myTerm = t;
	}
	
	private Expr myEx;
	private Term myTerm;
	
	public int interpret()
	{
		int a = myEx.interpret();
		int b = myTerm.interpret();
		return (a - b);
	}
	
}

class OneTerm extends Expr
{
	public OneTerm(Term t)
	{
		myTerm = t;
	}
	
	
	private Term myTerm;
	
	public int interpret()
	{
		return myTerm.interpret();
	}
}

abstract class Term extends AST
{
	abstract int interpret();
}

class TimesTerm extends Term
{
	public TimesTerm(Term t, Factor f)
	{
		myTerm = t;
		myFactor = f;
	}
	
	private Term myTerm;
	private Factor myFactor;
	
	public int interpret()
	{
		int a = myTerm.interpret();
		int b = myFactor.interpret();
		return (a * b);
	}
}

class DivideTerm extends Term
{
	public DivideTerm(Term t, Factor f)
	{
		myTerm = t;
		myFactor = f;
	}
	
	private Term myTerm;
	private Factor myFactor;
	
	public int interpret()
	{
		int a = myTerm.interpret();
		int b = myFactor.interpret();
		return (a / b);
	}
}

class OneFactor extends Term
{
	public OneFactor(Factor f)
	{
		myFactor = f;
	}
	
	
	private Factor myFactor;
	
	public int interpret()
	{
		return myFactor.interpret();
	}
}

abstract class Factor extends AST
{
	abstract int interpret();
}

class NumFactor extends Factor
{
	public NumFactor(int num)
	{
		myNum = num;
	}
	
	private int myNum;
	
	public int interpret()
	{
		return myNum;
	}
}

class ParenFactor extends Factor
{
	public ParenFactor(Expr ex)
	{
		myEx = ex;
	}
	
	private Expr myEx;
	
	public int interpret()
	{
		return myEx.interpret();
	}
}

class AbsFactor extends Factor
{
	public AbsFactor(Expr ex)
	{
		myEx = ex;
	}
	
	private Expr myEx;
	
	public int interpret()
	{
		return Math.abs(myEx.interpret());
	}
}

class MinusFactor extends Factor
{
	public MinusFactor(Factor f)
	{
		myFactor = f;
	}
	
	
	private Factor myFactor;
	
	public int interpret()
	{
		return -myFactor.interpret();
	}
}









