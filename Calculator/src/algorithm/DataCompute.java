package algorithm;


public class DataCompute {

	private static int year, month, day;
	private static int YEAR, MONTH, DAY;
	private static int[] MONTHDAYS = {31,28,31,30,31,30,31,31,30,31,30,31};

	private static boolean isLeap(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private static int alreadyPassed(int year, int month, int day) {

		int alreadyTotal = 0;
		if (isLeap(year))
			MONTHDAYS[1] = 29;
		else
			MONTHDAYS[1] = 28;
		for(int i=0;i<month-1;i++){
			alreadyTotal+=MONTHDAYS[i];
		}
		alreadyTotal += day;
		return alreadyTotal;

	}

	private static int daysOfDistance() {

		int total = 0;

		if (year < YEAR) {
			for (int i = year; i < YEAR; i++) {
				if (isLeap(i)) {
					total += 366;
				} else
					total += 365;
			}
			total = total - alreadyPassed(year, month, day) + alreadyPassed(YEAR, MONTH, DAY);
		} else if (year == YEAR) {
			total = Math.abs(alreadyPassed(year, month, day) - alreadyPassed(YEAR, MONTH, DAY));
		} else if (year > YEAR) {
			for (int i = YEAR; i < year; i++) {
				if (isLeap(i))
					total += 366;
				else
					total += 365;
			}
			total = total + alreadyPassed(year, month, day) - alreadyPassed(YEAR, MONTH, DAY);
		}

		return total;

	}

	
	public static String COMPUTE(String fromYear,String fromMonth,String fromDay,String toYear,String ToMonth,String ToDay) {
		
			year = Integer.parseInt(fromYear);
			month = Integer.parseInt(fromMonth);
			
			day = Integer.parseInt(fromDay);
			
			YEAR = Integer.parseInt(toYear);
			MONTH = Integer.parseInt(ToMonth);
			DAY	= Integer.parseInt(ToDay);
		
			int dis = daysOfDistance();
			return String.format("%d", dis);
	}
}
