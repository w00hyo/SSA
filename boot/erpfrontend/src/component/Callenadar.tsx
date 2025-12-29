import { useState } from "react";
import { Container, Row, Col, Table, Button } from "react-bootstrap";


type MiniCalendarProps = {
  selectedDate?: Date | null;
  onChange?: (date: Date) => void;
};

type ScheduleEvent = {
  id: number;
  date: string; // "YYYY-MM-DD"
  tag: string;  // 뱃지 텍스트 (회의, 연차 등)
  type: "meeting" | "off" | "etc";
  title: string;
};

// 예시 일정 (이미지랑 비슷하게)
const dummyEvents: ScheduleEvent[] = [
  { id: 1, date: "2025-12-01", tag: "회의", type: "meeting", title: "주간회의" },
  { id: 2, date: "2025-12-08", tag: "회의", type: "meeting", title: "주간회의" },
  { id: 3, date: "2025-12-15", tag: "회의", type: "meeting", title: "주간회의" },
  { id: 4, date: "2025-12-22", tag: "회의", type: "meeting", title: "주간회의" },
  { id: 5, date: "2025-12-16", tag: "행사", type: "etc",     title: "창업인의 밤" },
  { id: 6, date: "2025-12-19", tag: "연차", type: "off",     title: "연차_김주빈" },
  { id: 7, date: "2025-12-27", tag: "연차", type: "off",     title: "연차_최현경" },
];

const Callendar: React.FC<MiniCalendarProps> = ({ selectedDate, onChange }) => {
  const initDate = selectedDate || new Date(); // 현재 달로 보이게
  const [currentDate, setCurrentDate] = useState<Date>(initDate);

  const year = currentDate.getFullYear();
  const month = currentDate.getMonth(); // 0~11
  const today = new Date();

  // 날짜 key 포맷: YYYY-MM-DD
  const formatDateKey = (d: Date) => {
    const y = d.getFullYear();
    const m = (d.getMonth() + 1).toString().padStart(2, "0");
    const day = d.getDate().toString().padStart(2, "0");
    return `${y}-${m}-${day}`;
  };

  // 이벤트를 날짜별로 묶기
  const eventMap = new Map<string, ScheduleEvent[]>();
  dummyEvents.forEach((e) => {
    const list = eventMap.get(e.date) || [];
    list.push(e);
    eventMap.set(e.date, list);
  });

  // 달력 셀 데이터 만들기
  const firstDay = new Date(year, month, 1);
  const firstDayWeek = firstDay.getDay(); // 0(일)~6(토)
  const lastDay = new Date(year, month + 1, 0);
  const lastDate = lastDay.getDate();

  const days: (number | null)[] = [];
  for (let i = 0; i < firstDayWeek; i++) days.push(null);
  for (let d = 1; d <= lastDate; d++) days.push(d);
  while (days.length % 7 !== 0) days.push(null); // 7의 배수 맞추기

  // 7일씩 잘라서 주(week) 배열로
  const weeks: (number | null)[][] = [];
  for (let i = 0; i < days.length; i += 7) {
    weeks.push(days.slice(i, i + 7));
  }

  const isSameDate = (d1: Date, d2: Date) =>
    d1.getFullYear() === d2.getFullYear() &&
    d1.getMonth() === d2.getMonth() &&
    d1.getDate() === d2.getDate();

  const handlePrevMonth = () => {
    setCurrentDate(new Date(year, month - 1, 1));
  };

  const handleNextMonth = () => {
    setCurrentDate(new Date(year, month + 1, 1));
  };

  const handleSelectDate = (date: Date) => {
    onChange && onChange(date);
  };

  return (
    <Container className="schedule-container mt-120">
      <Row>
        <Col>
          {/* 상단 헤더 */}
          <div className="schedule-header">
            <div className="schedule-title-left">
              <Button
                variant="link"
                className="p-0 me-2 schedule-arrow"
                onClick={handlePrevMonth}
              >
                &lt;
              </Button>
              <span className="schedule-month-text">
                {year}/{(month + 1).toString().padStart(2, "0")}
              </span>
              <span className="fs-16-600-black">일정관리</span>
            </div>
            <div className="schedule-header-right">
              {/* 아이콘 자리 (추가 버튼 등 넣고 싶으면 여기에) */}
            </div>
          </div>

          {/* 달력 테이블 */}
          <Table bordered hover className="table schedule-table" responsive="sm">
            <colgroup>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            <col style={{width:"128px"}}/>
            </colgroup>
            <thead>
              <tr>
                <th>일</th>
                <th>월</th>
                <th>화</th>
                <th>수</th>
                <th>목</th>
                <th>금</th>
                <th>토</th>
              </tr>
            </thead>
            <tbody>
              {weeks.map((week, wIdx) => (
                <tr key={wIdx}>
                  {week.map((day, dIdx) => {
                    if (!day) {
                      return <td key={dIdx} className="schedule-cell empty"></td>;
                    }

                    const cellDate = new Date(year, month, day);
                    const key = formatDateKey(cellDate);
                    const events = eventMap.get(key) || [];
                    const isToday = isSameDate(cellDate, today);

                    return (
                      <td
                        key={dIdx}
                        className={`schedule-cell ${isToday ? "today" : ""}`}
                        onClick={() => handleSelectDate(cellDate)}
                      >
                        <div className="day-number">{day}</div>
                        <div className="event-list">
                          {events.map((e) => (
                            <div className="event-row" key={e.id}>
                              <span className={`event-tag ${e.type}`}>{e.tag}</span>
                              <span className="event-title">{e.title}</span>
                            </div>
                          ))}
                        </div>
                      </td>
                    );
                  })}
                </tr>
              ))}
            </tbody>
          </Table>
        </Col>
      </Row>
    </Container>
  );
};

export default Callendar;
