package in.java.support.time;

import java.time.Duration;
import java.time.chrono.ChronoLocalDateTime;

import in.java.support.data.Range;

public class ChronoDateTimeRange<T extends ChronoLocalDateTime<?>> extends Range<T>
{
	public ChronoDateTimeRange(T minimum, T maximum)
	{
		super(minimum, maximum);
	}
	
	public Duration duration()
	{
		return Duration.between(this.Minimum(), this.Maximum());
	}
}
