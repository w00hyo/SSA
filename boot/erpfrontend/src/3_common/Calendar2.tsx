import { useMemo, useState } from "react";
//useState (컴포넌트 안에서 값을 저장하기 위한  react훅)
//useMemo (→ 계산이 오래 걸리는 값을 기억해두고 필요할 때만 다시 계산하도록 도와줌)
import { holidays as holidayData } from "@kyungseopk1m/holidays-kr";
//대한민국 공휴일 데이터 라이브러리
import { Wrapper, Header, Grid, DayName, CalTopMargin } from "../stylesjs/Content.styles";
//화면 디자인용 스타일 컴포넌트

interface RawHoliday {//공휴일 하나의 형태를 정의
  date: number; // YYYYMMDD
  name: string;
}

const Calendar2 = () => {//달력을 그려주는 React함수 컴포넌트
  const [currentDate] = useState(new Date());//현재 날짜 가져오기

  const year = currentDate.getFullYear();//현재 년도 
  const month = currentDate.getMonth(); // 0~11

  // 연도별 공휴일 + 성탄절 수동 추가
  const rawHolidays = useMemo<RawHoliday[]>(() => {
    //공휴일 데이터 중에서 현재 연도 것만 추려냄(불필요한 재계산을 방지)
    let holidays: RawHoliday[] = [];
    if (Array.isArray(holidayData)) {//공휴일 데이터가 배열인지 확인
      //혹시 데이터가 깨졌을 경우를 대비
      holidays = holidayData
        .filter((h: any) => String(h.date).startsWith(String(year)))
        //현재 년도 공휴일만 필터링
        .map((h: any) => ({//필요한 형태로 변환
          date: Number(h.date),
          name: String(h.name),
        }));
    }

// 12월 성탄절 강제 추가 기존 로직에서 성탄절을 추가하려 했으나 
//노출이 안됬을경우 강제로 추가한 경우 이유가 라이브러리마다 달라서 없는 경우에는 강제 추가해야 됨
if (month === 11 && !holidays.some(h => String(h.date) === `${year}1225`)) {
  holidays.push({ date: Number(`${year}1225`), name: "성탄절" });
}

    return holidays;
  }, [year, month]);

  // 현재 월 공휴일 필터 현재보고 잇는 달의 공휴일만 사용
  const holidays = useMemo(() => {
    return rawHolidays.filter(
      h => Number(String(h.date).slice(4, 6)) === month + 1
    );
  }, [rawHolidays, month]);

  const firstDay = new Date(year, month, 1).getDay();
  //이번달 1일의 요일을 계산
  const lastDate = new Date(year, month + 1, 0).getDate();
  //이번달의 마지막 날짜를 계산

  return (
    <CalTopMargin>
    <Wrapper>
      <Header>
        <h3>
          {year}년 {month + 1}월{/*0부터라서 + 1 */}
        </h3>
      </Header>

      <Grid>
        {/* 요일 */}
        {["일", "월", "화", "수", "목", "금", "토"].map(day => (
          <DayName key={day}>{day}</DayName>
        ))}
{/*요일을 한줄로 출력 */}
        {/* 빈칸 */}
        {Array.from({ length: firstDay }).map((_, idx) => (
          <div key={`empty-${idx}`} />
        ))}
        {/*빈칸 만들기 1일이 수요일이면 일월화 3칸을 비움 */}

        {/* 날짜 셀 1일부터 마지막 날짜가지 반복*/}
        {Array.from({ length: lastDate }, (_, idx) => {
          const day = idx + 1;
          const weekday = (firstDay + idx) % 7;

          // day와 비교해서 공휴일 찾기 날짜가 같은 공휴일 찾기
          const holiday = holidays.find(
            h => Number(String(h.date).slice(6, 8)) === day
          );

          const isHoliday = Boolean(holiday);//상태값들
          const isSunday = weekday === 0; // 일요일 체크
          //토요일 추가
          const isSaturday = weekday === 6;//토요일
          const isChristmas = holiday?.name === "성탄절";
//공휴일은 노란배경 일요일 빨간글씨
          return (
            <div
              key={day}
              style={{
                height: 50,
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                background: isHoliday ? "#ffefc3" : "#f4f4f4",
                borderRadius: 8,
                color: isSunday ? "red": isSaturday ? "blue" : "#333",
                margin: 2,
              }}
              title={holiday?.name || ""}
            >
              {day} {isChristmas && "🎄"}
            </div>
          );
        })}
      </Grid>
    </Wrapper>
    </CalTopMargin>
  );
};

export default Calendar2;
