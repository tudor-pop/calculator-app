package tudor.com.calculatorSleek.extra;

/**
 * Created by Tudor on 21-May-15.
 */
public class PrepareString {

	public static String prepareStringForMathEval(String str) {
		str = operatorMapping(str);
		str = fixMathSymbols(str);
		str = fixInvalidEnd(str);
		str = fixUnclosedParantheses(str);
		str = fixOperatorAfterOpenParantheses(str);
		str = fixOperatorBetweenParantheses(str);


		return str;
	}


	private static String fixMathSymbols(String str){
		str = str.replaceAll("(\\d+(\\.\\d+)?!)", "($1)"); // ! factorial

		str = str.replaceAll("π", String.format("(%f)", Math.PI));
		str = str.replaceAll("e", String.format("(%f)", Math.E));
		return str;
	}

	/**
	 * Remove any operator or open parantheses from the end of the expression
	 * example: 33.11((-(/(*(
	 * @param str
	 * @return
	 */
	private static String fixInvalidEnd(String str) {
		StringBuilder builder = new StringBuilder(str);
		while (builder.length()>0
				&& (isOperator(builder.charAt(builder.length()-1))
				|| builder.charAt(builder.length()-1)=='('))// 3(, 3(( it useles to solve this like 3() or 3(())
			builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * Removing illogical possitioning of an operator right after opened parantheses in the middle of an expression
	 * example: 55.44-(-((-22.11))), -55.44*(*22.11(*2(22.11))),-55.44*(*22.11(*(22.11))), -55.44*(*22.11((*2(22.11))))
	 * @param str
	 * @return
	 */
	private static String fixOperatorAfterOpenParantheses(String str) {
		StringBuilder builder = new StringBuilder(str);
		for (int i = 1; i < builder.length(); i++) {
			char ch = builder.charAt(i);
			if (isOperator(ch) && ch != '-' && builder.charAt(i - 1)=='(') {
				builder.deleteCharAt(i);
			}
		}
		return builder.toString();
	}
	/* Under this is taking place parantheses fixing */

	/**
	 * Removes redundant paranthesis.
	 * example: (), (+), (*), (/), 55.11((()())()(()))
	 * @param str
	 * @return
	 */
	private static String fixOperatorBetweenParantheses(String str){
		str = str.replaceAll("\\(+[+*\\-/]?\\)+", "");
		str = fixInvalidEnd(str);
		return str;
	}


	/**
	 * Fixing too many closed paranthesis or too few closed parantheses
	 * @param str the expression
	 * @return the expression with the right number of closing parantheses
	 */
	private static String fixUnclosedParantheses(String str) {
		int countOpen = countOpenParantheses(str);
		int countClosed = countClosedParantheses(str);
		StringBuffer string=new StringBuffer(str);
		if (countClosed >= countOpen)
			string = new StringBuffer(str.substring(0, str.length()-(countClosed-countOpen)));
		else {
			for (int i = 0; i < Math.abs(countClosed - countOpen); i++) {
				string.append(")");
			}
		}

		return string.toString();
	}
	private static int countOpenParantheses(String str){
		int count=0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i)=='(')
				++count;
		return count;
	}
	private static int countClosedParantheses(String str){
		int count=0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i)==')')
				++count;
		return count;
	}

	/* end parantheses fixing*/

	private static boolean isOperator(char ch) {
		switch (ch) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '%':
				return true;
			default:
				return false;
		}
	}

	private static boolean isParantheses(char ch) {
		switch (ch) {
			case '(':
			case ')':
				return true;
			default:
				return false;
		}
	}

	public static String operatorMapping(String expression){
		String tmp = expression
				.replaceAll("×", "*")
				.replaceAll("÷", "/")
				.replaceAll("√(\\(*\\d+(\\.\\d+)?\\)*)", "sqrt($1)");
		return tmp;
	}


}
