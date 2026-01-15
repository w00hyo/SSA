package port.sm.erp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	    
	    //일정구분
	    @Column(name = "CATEGORY", length = 30)
	    private String category;
	    
	    //캘린더 종류
	    @Column(name = "CALENDAR_TYPE", length = 30)
	    private String calendar;
	    
	    //장소 
	    @Column(name = "LABEL", length = 50)
	    private String label;
	    
	    //참석자 
//쇼핑몰 에서 상품을 구매할때 user(구매자[테이블{칼럼}])-> 똑같은 이름 [조인] (product[테이블{칼럼}])상품
//포린키	  
/*
ADD CONSTRAINT fk_user member

shop
FOREIGN KEY (user)
REFERENCES MEMBER_USR
*/
	    //참석자
	    @ElementCollection //별도 테이블로 뺄께요
	    @CollectionTable( //그 별도 테이블 이름과 연결키를 정한다
		name = "calendar_event_attendee", 
		joinColumns = @JoinColumn(name = "event_id") //포린키에 부분은 여기임	
		//calendar_event_attendee에 event_id ? pk fk 컬럼을 만들고 
		//이 이벤트 id는 pk를 가리키는 용도로 쓰이고 즉 논리적 FK역활을 한다
	    )
	    @Column(name = "attendee", length = 200)//테이블에 들어갈 값 컬럼을 정의
	    @Builder.Default
	    private List<String> attendees = new ArrayList<>();
	    
	    
	    //공유자
	    @ElementCollection
	    @CollectionTable(
	    name = "calendar_event_sharer",
	    joinColumns = @JoinColumn(name = "event_id")
	    )
	    @Column(name = "sharer", length = 200)
	    @Builder.Default
	    private List<String> sharers = new ArrayList<>();
	    
	    
	
	
	
	
}
