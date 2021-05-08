package br.ufrn.ct.hefesto.service.pessoa;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date zerarHoraData(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(10, 0);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	public static boolean isBefore(Date aux, Date auy) {
		return zerarHoraData(aux).before(zerarHoraData(auy));
	}

	public static boolean isAfter(Date aux, Date auy) {
		return zerarHoraData(aux).after(zerarHoraData(auy));
	}

	public static boolean isEquals(Date aux, Date auy) {
		return zerarHoraData(aux).equals(zerarHoraData(auy));
	}

}
