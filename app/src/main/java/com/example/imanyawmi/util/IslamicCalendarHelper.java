package com.example.imanyawmi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Helper class to manage Islamic calendar dates and events
 */
public class IslamicCalendarHelper {

    // Map to store important Islamic dates for the current year
    private Map<String, String> importantIslamicDates = new HashMap<>();

    // List to store all days with events
    private List<CalendarDay> daysWithEvents = new ArrayList<>();

    /**
     * Class to represent a calendar day with Hijri date and events
     */
    public static class CalendarDay {
        private Date gregorianDate;
        private String hijriDate;
        private String event;
        private boolean isImportantDay;

        public CalendarDay(Date gregorianDate, String hijriDate, String event, boolean isImportantDay) {
            this.gregorianDate = gregorianDate;
            this.hijriDate = hijriDate;
            this.event = event;
            this.isImportantDay = isImportantDay;
        }

        public Date getGregorianDate() {
            return gregorianDate;
        }

        public String getHijriDate() {
            return hijriDate;
        }

        public String getEvent() {
            return event;
        }

        public boolean isImportantDay() {
            return isImportantDay;
        }
    }

    /**
     * Constructor initializes the important Islamic dates
     */
    public IslamicCalendarHelper() {
        initializeImportantDates();
    }

    /**
     * Initialize important Islamic dates for the current year
     * Note: These dates change each year based on lunar calendar
     * This is a simplified version - in a real app you'd use a proper Hijri calendar library
     */
    private void initializeImportantDates() {
        // Adding important dates for 1446/1447 Hijri (2025 Gregorian)
        // First day of Ramadan (approximate for 2025)
        importantIslamicDates.put("2025-03-01", "1 Ramadan - First day of Ramadan");

        // Laylat al-Qadr (Night of Power) - usually observed on the 27th night of Ramadan
        importantIslamicDates.put("2025-03-27", "27 Ramadan - Laylat al-Qadr");

        // Eid al-Fitr (approximate for 2025)
        importantIslamicDates.put("2025-03-31", "1 Shawwal - Eid al-Fitr");

        // Eid al-Adha (approximate for 2025)
        importantIslamicDates.put("2025-06-07", "10 Dhul-Hijjah - Eid al-Adha");

        // Islamic New Year (1447 Hijri - approximate for 2025)
        importantIslamicDates.put("2025-07-07", "1 Muharram - Islamic New Year");

        // Day of Ashura
        importantIslamicDates.put("2025-07-16", "10 Muharram - Day of Ashura");

        // Mawlid al-Nabi (Prophet's Birthday)
        importantIslamicDates.put("2025-09-15", "12 Rabi' al-Awwal - Mawlid al-Nabi");

        // These are approximations and should be verified or calculated properly
        // In a production app, use a dedicated Hijri calendar conversion library
    }

    /**
     * Check if a date has an important Islamic event
     * @param date the date to check in yyyy-MM-dd format
     * @return the event description or null if no event
     */
    public String getEventForDate(String date) {
        return importantIslamicDates.get(date);
    }

    /**
     * Check if a date has an important Islamic event
     * @param date the date to check
     * @return the event description or null if no event
     */
    public String getEventForDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return getEventForDate(sdf.format(date));
    }

    /**
     * Convert Gregorian date to Hijri date
     * This is a placeholder - in a real app you would use a proper conversion library
     * @param gregorianDate the Gregorian date to convert
     * @return a String representation of the Hijri date
     */
    public String convertToHijri(Date gregorianDate) {
        // This is a placeholder - you need a proper Hijri calendar implementation
        // In a real app, use a library like ICU4J or HijriCalendar

        // For demo purposes, we're returning a dummy string
        Calendar cal = Calendar.getInstance();
        cal.setTime(gregorianDate);

        // Dummy conversion (not accurate)
        // In a real app, you would use a proper Hijri calendar conversion
        return "Hijri date";
    }

    /**
     * Get all days in the given month with their Hijri date and events
     * @param year Gregorian year
     * @param month Gregorian month (0-11)
     * @return List of CalendarDay objects with Hijri dates and events
     */
    public List<CalendarDay> getDaysInMonth(int year, int month) {
        List<CalendarDay> days = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        for (int i = 1; i <= daysInMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            Date date = calendar.getTime();
            String dateString = sdf.format(date);

            String hijriDate = convertToHijri(date);
            String event = getEventForDate(dateString);
            boolean isImportant = event != null;

            days.add(new CalendarDay(date, hijriDate, event, isImportant));
        }

        return days;
    }
}