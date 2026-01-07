package port.sm.erp.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="calendar_event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALENDAR_SEQ")
	@SequenceGenerator(name = "CALENDAR_SEQ",sequenceName = "CALENDAR_EVENT_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name = "EVENT_DATE", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	
	@Column(nullable=false)
	private Long userId;
	
	/*날짜 필수
	
	private LocalDate eventDate;*/
	
	@Column(nullable = false, length = 100)
	  private String title;

	  @Column(length = 1000)
	  private String memo;

	  //private LocalTime startTime;
	  //private LocalTime endTime;
	    // ✅ Oracle 안전하게 String으로
	    @Column(name = "START_TIME", length = 5)
	    private String startTime; // "HH:mm"

	    @Column(name = "END_TIME", length = 5)
	    private String endTime;   // "HH:mm"
	
	
	
	
}
