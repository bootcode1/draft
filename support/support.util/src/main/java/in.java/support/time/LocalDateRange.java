package in.java.support.time;

import java.time.LocalDate;
import java.time.Period;

import in.java.support.data.Range;

public class LocalDateRange extends Range<LocalDate>
{
	public LocalDateRange(LocalDate minimum, LocalDate maximum)
	{
		super(minimum, maximum);
	}
	
	public Period period()
	{
		return Period.between(this.Minimum(), this.Maximum());
	}
}
