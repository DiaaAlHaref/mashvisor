import java.text.DecimalFormat;

public class MortgagePayment {

    private static final int MONTH_OF_YEAR = 12;

    public static double CalculateMortgage(double PrincipalAmount, float AnnualInterestRate, int TermInYears){
        float monthlyInterestRate =  AnnualInterestRate / MONTH_OF_YEAR;
        DecimalFormat dcf = new DecimalFormat("####");
        int numberOfPayments = TermInYears * MONTH_OF_YEAR;

        double monthlyPayment = PrincipalAmount * (monthlyInterestRate * (Math.pow(1+monthlyInterestRate,numberOfPayments))/
                        ((Math.pow(1+monthlyInterestRate,numberOfPayments)) -1)
        );
        dcf.format(monthlyPayment);
        return monthlyPayment;
    }
}
