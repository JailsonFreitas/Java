package Seguradora;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
	
public class VerificarEntrada {
	public boolean isNumeric (String s) {
		try {
			Long.parseLong (s);
			if(s.indexOf(",") != -1){
				  throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	private static ThreadLocal<NumberFormat> brazilianCurrencyFormat = new ThreadLocal<NumberFormat> () {
		@Override protected NumberFormat initialValue() {
			return new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
		}
	};
	public boolean isCurrency (String s) {
		s = s.trim();
		ParsePosition pos = new ParsePosition (0);
                    brazilianCurrencyFormat.get().parse(s, pos);
		return pos.getIndex() == s.length();
	}
}
