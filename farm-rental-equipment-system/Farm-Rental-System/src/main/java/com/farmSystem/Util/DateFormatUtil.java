package com.farmSystem.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateFormatUtil {
	
	public String formatLocalDateTimeTo(LocalDateTime date) {
		LocalDateTime dateTime = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),0,0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedDate = dateTime.format(formatter);
        
        return formattedDate;
	}

}
